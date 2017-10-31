package com.bbn.kbp.linking;

import static com.google.common.base.Preconditions.checkNotNull;

import com.bbn.bue.common.symbols.Symbol;
import com.bbn.kbp.events2014.scorer.LinkingScore;

public final class LinkResult {

  private final LinkingScore linkingScore;

  LinkResult(final LinkingScore linkingScore) {
    this.linkingScore = checkNotNull(linkingScore);
  }

  public Symbol docID() {
    return linkingScore.docID();
  }

  public double linkingNormalizer() {
    return linkingScore.referenceLinkingSize();
  }

  public LinkingScore linkingScore() {
    return linkingScore;
  }

  public double unscaledLinkingScore() {
    return linkingScore.F1() * linkingNormalizer();
  }

  public double unscaledLinkingPrecision() {
    return linkingScore.precision() * linkingNormalizer();
  }

  public double unscaledLinkingRecall() {
    return linkingScore.recall() * linkingNormalizer();
  }

  public double scaledLinkingScore() {
    return linkingScore.F1();
  }

  public double scaledLinkingPrecision() {
    return linkingScore.precision();
  }

  public double scaledLinkingRecall() {
    return linkingScore.recall();
  }
}
