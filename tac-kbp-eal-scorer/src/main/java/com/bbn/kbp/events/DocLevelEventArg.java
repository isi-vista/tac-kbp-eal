package com.bbn.kbp.events;

import com.bbn.bue.common.HasDocID;
import com.bbn.bue.common.TextGroupImmutable;
import com.bbn.bue.common.symbols.Symbol;
import com.bbn.nlp.events.HasEventArgType;
import com.bbn.nlp.events.HasEventType;

import com.google.common.base.Function;

import org.immutables.func.Functional;
import org.immutables.value.Value;

@Value.Immutable
@Functional
@TextGroupImmutable
public abstract class DocLevelEventArg implements HasDocID, HasEventType, HasEventArgType,
    WithDocLevelEventArg {

  public abstract Symbol docID();

  public abstract Symbol eventArgumentType();

  public abstract Symbol eventType();

  public abstract Symbol realis();

  public abstract String corefID();

  @Override
  public String toString() {
    return docID().asString() + "/" + eventType().asString()
        + "-" + eventArgumentType().asString() + "/" + realis().asString()
        + "/" + corefID();
  }

  public enum TypeRoleFunction implements Function<DocLevelEventArg, String> {
    INSTANCE;

    @Override
    public String apply(final DocLevelEventArg input) {
      return input.eventType().asString() + "-" + input.eventArgumentType().asString();
    }
  }

  public static class Builder extends ImmutableDocLevelEventArg.Builder {

  }
}
