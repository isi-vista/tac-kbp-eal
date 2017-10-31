All the counts in the files here (except one) are over scorable arguments.
 The key words here are "scorable" and "arguments".

* arguments: if a Conflict.Attack event occurs with three arguments, it will contribute a count
of three to the counts by event type, not a count of one.
* scorable: an argument is not "scorable" if for whatever reason it is ignored for purposes of scoring.

The counts provided are:
  * by event type
  * by event type and argument role
  * by genre
  * by argument filler mention type (e.g. name, nominal, pronoun, time, filler=title, crime, etc.)

The exception to the above is "eventTypeCountsByEventFrame.txt" which counts how many scorable
 ( i.e. not generic ) event frames there are of each type in the gold standard.

 If the genre of any files could not be determined the doc ids will be
 written to "undetermined_genre.txt"