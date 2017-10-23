package com.bbn.kbp.events;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.concat;

import com.bbn.bue.common.TextGroupImmutable;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import org.immutables.func.Functional;
import org.immutables.value.Value;

@Value.Immutable
@Functional
@TextGroupImmutable
public abstract class ResponsesAndLinking {

  @Value.Parameter
  public abstract ImmutableSet<DocLevelEventArg> args();

  @Value.Parameter
  public abstract DocLevelArgLinking linking();

  public static ResponsesAndLinking of(Iterable<? extends DocLevelEventArg> args,
      DocLevelArgLinking linking) {
    return new Builder().args(args).linking(linking).build();
  }

  @Value.Check
  protected void check() {
    checkArgument(args().containsAll(ImmutableSet.copyOf(concat(linking()))));
  }

  public final ResponsesAndLinking filter(Predicate<? super DocLevelEventArg> predicate) {
    return ResponsesAndLinking.of(
        Iterables.filter(args(), predicate),
        linking().filterArguments(predicate));
  }

  public final ResponsesAndLinking transform(
      final Function<? super DocLevelEventArg, DocLevelEventArg> transformer) {
    return ResponsesAndLinking
        .of(Iterables.transform(args(), transformer), linking().transformArguments(transformer));
  }

  static Function<ResponsesAndLinking, ResponsesAndLinking> filterFunction(
      final Predicate<? super DocLevelEventArg> predicate) {
    return new Function<ResponsesAndLinking, ResponsesAndLinking>() {
      @Override
      public ResponsesAndLinking apply(final ResponsesAndLinking input) {
        return input.filter(predicate);
      }
    };
  }

  public static class Builder extends ImmutableResponsesAndLinking.Builder {

  }
}
