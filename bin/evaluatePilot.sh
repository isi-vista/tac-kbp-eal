#1/bin/bash

set -x
set -e

: ${KBPOPENREPO:?"Need to set KBPOPENREPO to path to working copy of kbp-2014-event-arguments"}
: ${PARTICIPANTS:?"Need to set PARTICIPANTS to path of a copy of KBP2014_event-argument-pilot_runs_20140421.tgz"}
: ${ASSESSMENTS:?"Need to set $ASSESSMENTS to path of a copy of LDC2014E40_TAC_2014_KBP_Event_Argument_Extraction_Pilot_Assessment_Results.tgz"}

EVALDIR=${KBPOPENREPO}/output/pilotEval
LOG=$EVALDIR/log/evaluation.log 

echo "Using working copy $KBPOPENREPO"
echo "Writing log to $EVALDIR/log"

# clear previous run, if any
echo "Output will be written to $EVALDIR"
echo "Clearing previous output, if any"
rm -rf $EVALDIR

echo "Creating output directory"
mkdir -p $EVALDIR

# uncompress participant submissions
PARTICIPANTCOPY=$EVALDIR/output/pilotEval/participantSubmissions
mkdir -p $PARTICIPANTCOPY
echo "Uncompressing participant submissions from $PARTICIPANTS to $PARTICIPANTCOPY"
tar xzf $PARTICIPANTS -C $PARTICIPANTCOPY  --strip-components=1

cd $PARTICIPANTCOPY
echo "Uncompressing .zip submissions"
for f in *.zip; do
    strippedName=${f%.zip}
    echo "Unzipping $strippedName"
    unzip -q $f -d $strippedName 
done

echo "Uncompressing .tar.gz submissions"
for f in *.tar.gz; do
    strippedName=${f%.tar.gz}
    mkdir $strippedName
    echo "Unzipping $strippedName"
    tar xzf $f -C $strippedName
done


echo "Uncompressing .tgz submissions"
for f in *.tgz; do
    strippedName=${f%.tgz}
    mkdir $strippedName
    echo "Unzipping $strippedName"
    tar xzf $f -C $strippedName
done


# copy LDC assessments
#LDCCOPY=$EVALDIR/output/pilotEval/ldcAssessment
#echo "copying LDC assessments from $LDCASSESSMENTS to $LDCCOPY"
#cp -r $LDCASSESSMENTS/data/LDC_assessments/  $LDCCOPY

# remove .out from LDC assessments
#echo "Removing .out suffix from LDC assessment files"
#pushd $LDCCOPY
#rename .out "" *.out
#popd

# repair LDC assessments

# apply realis expansion to LDC assessments

# quote filter participant submissions

# score
