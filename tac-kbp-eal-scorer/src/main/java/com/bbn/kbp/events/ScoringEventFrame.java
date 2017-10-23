package com.bbn.kbp.events;

import static com.google.common.base.Preconditions.checkArgument;

import com.bbn.bue.common.TextGroupImmutable;
import com.google.common.collect.ImmutableSet;
import java.util.AbstractSet;
import java.util.Iterator;
import org.immutables.func.Functional;
import org.immutables.value.Value;

@TextGroupImmutable
@Functional
@Value.Immutable
public abstract class ScoringEventFrame extends AbstractSet<DocLevelEventArg> {

  @Value.Parameter
  public abstract ImmutableSet<DocLevelEventArg> arguments();

  @Override
  public final Iterator<DocLevelEventArg> iterator() {
    return arguments().iterator();
  }

  @Override
  public int size() {
    return arguments().size();
  }

  public static ScoringEventFrame of(Iterable<? extends DocLevelEventArg> args) {
    return new ScoringEventFrame.Builder().arguments(args).build();
  }

  @Value.Check
  protected void check() {
    checkArgument(!arguments().isEmpty(), "Cannot create an event frame with no arguments");
  }

  public static class Builder extends ImmutableScoringEventFrame.Builder {

  }
}

