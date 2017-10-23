package com.bbn.kbp.events2014;

import static com.google.common.base.Preconditions.checkNotNull;

import com.bbn.bue.common.Inspector;
import com.bbn.bue.common.evaluation.EvalPair;
import com.bbn.bue.common.symbols.Symbol;
import com.bbn.kbp.events.DocLevelEventArg;
import com.bbn.kbp.events.GenreExtractor;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Multisets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aggregates the counts of various things in the gold standard ERE (as transformed for scoring) to
 * provide reports for the task overview paper.
 */
public final class GoldArgumentCountsInspector implements
    Inspector<EvalPair<ImmutableSet<DocLevelEventArg>, ImmutableSet<DocLevelEventArg>>> {

  private static final Logger log = LoggerFactory.getLogger(GoldArgumentCountsInspector.class);

  private final File outputDirectory;

  // mutable accumulator fields
  private final ImmutableMultiset.Builder<String> eventTypeCounts = ImmutableMultiset.builder();
  private final ImmutableMultiset.Builder<String> eventArgumentCounts = ImmutableMultiset.builder();
  private final ImmutableMultiset.Builder<String> genreCounts = ImmutableMultiset.builder();
  private final ImmutableMultiset.Builder<String> argumentMentionTypeCounts = ImmutableMultiset
      .builder();
  private final GenreExtractor genreExtractor = GenreExtractor.create();

  private static final ImmutableSet<String> KNOWN_ARG_MENTION_TYPES =
      ImmutableSet.of("Name", "Nominal", "Pronoun", "Filler", "Time");

  private GoldArgumentCountsInspector(File outputDirectory) {
    this.outputDirectory = checkNotNull(outputDirectory);
  }

  public static GoldArgumentCountsInspector createOutputtingTo(File outputDirectory) {
    return new GoldArgumentCountsInspector(outputDirectory);
  }

  @Override
  public void inspect(
      EvalPair<ImmutableSet<DocLevelEventArg>, ImmutableSet<DocLevelEventArg>> evalPair) {
    final ImmutableSet<DocLevelEventArg> goldArgs = evalPair.key();

    for (DocLevelEventArg goldArg : goldArgs) {
      countArgMentionTypes(goldArg);
      eventTypeCounts.add(goldArg.eventType().asString());
      eventArgumentCounts.add(goldArg.eventType() + "/" + goldArg.eventArgumentType());
      genreCounts.add(genreExtractor.apply(goldArg));
    }
  }

  @Override
  public void finish() throws IOException {
    outputDirectory.mkdirs();
    log.info("Writing gold counts to {}", outputDirectory);
    Files.asCharSink(new File(outputDirectory, "README.goldCounts.txt"), Charsets.UTF_8).write(
        Resources.asCharSource(
            Resources.getResource(GoldArgumentCountsInspector.class, "/README.scorer.txt"),
            Charsets.UTF_8)
            .read());
    writeTsv(eventTypeCounts.build(), new File(outputDirectory, "eventTypeCounts.tsv"));
    writeTsv(eventArgumentCounts.build(), new File(outputDirectory, "eventArgumentTypeCounts.tsv"));
    writeTsv(genreCounts.build(), new File(outputDirectory, "genreCounts.tsv"));
    writeTsv(argumentMentionTypeCounts.build(), new File(outputDirectory, "mentionTypeCounts.tsv"));
    genreExtractor.logDocIdsWithUndeterminedGenre(
        Files.asCharSink(new File(outputDirectory, "undetermined_genre.txt"), Charsets.UTF_8));
  }

  private Optional<String> extractGenre(Symbol docId) {
    if (docId.asString().startsWith("DF")) {
      return Optional.of("discussion_forum");
    } else if (docId.asString().startsWith("NYT_")) {
      return Optional.of("newswire");
    } else {
      return Optional.absent();
    }
  }

  private void countArgMentionTypes(DocLevelEventArg goldArg) {
    // ugh - this is ugly. Really the ScoringEntityType should have been carried all the
    // way through
    for (String knownArgMentionType : KNOWN_ARG_MENTION_TYPES) {
      if (goldArg.corefID().startsWith(knownArgMentionType)) {
        argumentMentionTypeCounts.add(knownArgMentionType);
        return;
      }
    }

    throw new IllegalStateException("No known mention type for " + goldArg.corefID());
  }

  static void writeTsv(ImmutableMultiset<String> data, File file) throws IOException {
    try (Writer out = Files.asCharSink(file, Charsets.UTF_8).openBufferedStream()) {
      for (Entry<String> e : Multisets.copyHighestCountFirst(data).entrySet()) {
        out.write(e.getElement() + "\t" + e.getCount() + "\n");
      }
    }
  }
}
