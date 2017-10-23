package com.bbn.kbp.events2014;

import static com.google.common.base.Preconditions.checkNotNull;

import com.bbn.bue.common.Inspector;
import com.bbn.bue.common.evaluation.EvalPair;
import com.bbn.kbp.events.DocLevelArgLinking;
import com.bbn.kbp.events.ScoringEventFrame;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Iterables;
import java.io.File;
import java.io.IOException;

public class GoldLinkingCountsInspector implements
    Inspector<EvalPair<DocLevelArgLinking, DocLevelArgLinking>> {

  private final File outputDirectory;
  private final ImmutableMultiset.Builder<String> eventTypes = ImmutableMultiset.builder();

  private GoldLinkingCountsInspector(File outputDirectory) {
    this.outputDirectory = checkNotNull(outputDirectory);
  }

  public static GoldLinkingCountsInspector createOutputtingTo(File outputDirectory) {
    return new GoldLinkingCountsInspector(outputDirectory);
  }

  @Override
  public void inspect(
      EvalPair<DocLevelArgLinking, DocLevelArgLinking> evalPair) {
    for (final ScoringEventFrame eventFrame : evalPair.key().eventFrames()) {
      if (!eventFrame.arguments().isEmpty()) {
        eventTypes.add(Iterables.getFirst(eventFrame.arguments(), null).eventType().asString());
      }
    }
  }

  @Override
  public void finish() throws IOException {
    outputDirectory.mkdirs();
    // accessing this is a hack until we shift it to bue-common-open
    GoldArgumentCountsInspector
        .writeTsv(eventTypes.build(), new File(outputDirectory, "eventTypeCountsByEventFrame.txt"));
  }
}
