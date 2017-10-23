package com.bbn.kbp.events;

import com.bbn.bue.common.HasDocID;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.io.CharSink;
import java.io.IOException;

/**
 * Utility class to attempt to determine the genre of a document based on its document ID. If it
 * does not know how to determine this, it will return the string "undetermined". You can log the
 * list of such doc IDs using {@link #logDocIdsWithUndeterminedGenre(CharSink)}.
 */
public final class GenreExtractor implements Function<HasDocID, String> {

  private final ImmutableSet.Builder<String> docIdsWithUndeterminedGenre = ImmutableSet.builder();

  private GenreExtractor() {
  }

  public static GenreExtractor create() {
    return new GenreExtractor();
  }

  @Override
  public String apply(HasDocID input) {
    if (input.docID().asString().startsWith("DF")) {
      return "discussion_forum";
    } else if (input.docID().asString().startsWith("NYT_")) {
      return "newswire";
    } else {
      docIdsWithUndeterminedGenre.add(input.docID().asString());
      return "undetermined";
    }
  }

  public void logDocIdsWithUndeterminedGenre(CharSink sink) throws IOException {
    sink.write(Joiner.on("\n").join(docIdsWithUndeterminedGenre.build()));
  }
}
