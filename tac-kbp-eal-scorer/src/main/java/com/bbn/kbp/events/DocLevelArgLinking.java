package com.bbn.kbp.events;

import com.bbn.bue.common.TextGroupImmutable;
import com.bbn.bue.common.symbols.Symbol;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import java.util.Iterator;
import org.immutables.value.Value;

@Value.Immutable
@TextGroupImmutable
public abstract class DocLevelArgLinking implements Iterable<ScoringEventFrame> {

  @Value.Parameter
  public abstract Symbol docID();

  @Value.Parameter
  public abstract ImmutableSet<ScoringEventFrame> eventFrames();

  @Override
  public Iterator<ScoringEventFrame> iterator() {
    return eventFrames().iterator();
  }

  public final DocLevelArgLinking filterArguments(
      Predicate<? super DocLevelEventArg> argPredicate) {
    final DocLevelArgLinking.Builder ret = new DocLevelArgLinking.Builder()
        .docID(docID());
    for (final ScoringEventFrame eventFrame : eventFrames()) {
      final ImmutableSet<DocLevelEventArg> filteredFrame =
          FluentIterable.from(eventFrame).filter(argPredicate).toSet();
      if (!filteredFrame.isEmpty()) {
        ret.addEventFrames(ScoringEventFrame.of(filteredFrame));
      }
    }
    return ret.build();
  }

  /**
   * May collapse DocLevelEventArgs as an affect of the transformer
   */
  public final DocLevelArgLinking transformArguments(
      final Function<? super DocLevelEventArg, DocLevelEventArg> transformer) {
    final DocLevelArgLinking.Builder ret = new DocLevelArgLinking.Builder();
    ret.docID(docID());
    for (final ScoringEventFrame eventFrame : eventFrames()) {
      final ImmutableSet<DocLevelEventArg> transformedFrame =
          FluentIterable.from(eventFrame.arguments()).transform(transformer).toSet();
      ret.addEventFrames(ScoringEventFrame.of(transformedFrame));
    }
    return ret.build();
  }

  public final ImmutableSet<DocLevelEventArg> allArguments() {
    return ImmutableSet.copyOf(Iterables.concat(eventFrames()));
  }

  public static class Builder extends ImmutableDocLevelArgLinking.Builder {

  }
}
