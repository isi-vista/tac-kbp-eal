This file explains the structure of the scoring directory.

For those just after the official eval scores, these can be found as:

* the only score in withRealis/argScores/ArgScore.bootstrapped.medians.csv
* the F1 score in withRealis/linkScores/linkScores.bootstrapped.medians.csv

Bootstrapping was done with 10,000 samples over the corpus.

At the highest level each scoring directory has:
   * withRealis/ - directory with scores requiring correct ACTUAL, GENERIC, OTHER determination
                        for alignment between system and reference (the official way)
   * noRealis/ - directory with scorings ignoring realis *not* requiring correct ACTUAL, GENERIC,
                        OTHER determination for alignment between system and reference (diagnostic)
   * alignmentFailures/ - directory with information on arguments which failed to
                             align to the CoreNLP parse used for lenient scoring.
                             This is purely diagnostic.

Each of the first two directories has the following structure:
    argScores/ - bootstrapped argument scores (official argument metric)
    linkScores/ - bootstrapped linking scores (official linking metric)
    typeF/- bootstrapped argument F scores broken down by event type (diagnostic; note type here
                includes both RichERE type and sub-type)
    typeRoleF/- bootstrapped argument F scores broken down by event type and role (diagnostic)
    mentionTypeF/ - bootstrapped argument F scores broken down by
                       argument mention type (diagnostic)
    perDocument/ - contains argument and linking scores for each document
                       as well as a log of all errors encountered in that document.
    nonBootstrapped/ - contains argument and linking scores over the eval
                           corpus without bootstrapping. Also includes argument precision,
                            recall, and F1 scores to supplemental the official argument scoring
                            metric. *These are not the official eval scores* (see above)
    goldCounts/ - the counts for various scoreable arguments in the unbootstrapped gold standard

Each of the bootstrapped directories has the following files:
   * X.bootstrapped.txt - human readable files with 0.5%, 2.5%, 5.0%, 25.0%,
                            50.0%, 75.0%, 95.0%, 97.5%, and 99.5%
                            percentiles of bootstrap sampling.
   * X.bootstrapped.median.csv - the median (50%) scores only
   * X.bootstrapped.csv - same information as X.bootstrapped.txt but in
                            a more machine friendly format
   * bootstrapData/      - the scores from all 10,000 samples
