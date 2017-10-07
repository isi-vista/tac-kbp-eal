#!/bin/bash

# For Chinese and Spanish CoreNLP does not handle parentehese properly and happily writes
# parses we can't load.  Run these this script from the CoreNLP output directory to
# fix them up.

perl -i -pe 's/\(PU \)/\(PU -RRB-/g' *.xml
perl -i -pe 's/\(i \)/\(i -RRB-/g' *.xml
perl -i -pe 's/\(fca \)/\(fca -RRB-/g' *.xml
perl -i -pe 's/\(fat \)/\(fat -RRB-/g' *.xml
perl -i -pe 's/\(fit \)/\(fit -RRB-/g' *.xml
perl -i -pe 's/\(fct \)/\(fct -RRB-/g' *.xml
perl -i -pe 's/\(fz \)/\(fz -RRB-/g' *.xml
perl -i -pe 's/\(fs \)/\(fs -RRB-/g' *.xml
perl -i -pe 's/\(XX \)/\(XX -RRB-/g' *.xml
perl -i -pe 's/\(nc0p000 \)/\(nc0p000 -RRB-/g' *.xml
perl -i -pe 's/\(np00000 \)/\(np00000 -RRB-/g' *.xml
perl -i -pe 's/\dn00000 \)/\(dn00000 -RRB-/g' *.xml
perl -i -pe 's/\(aq0000 \)/\(aq0000 -RRB-/g' *.xml
perl -i -pe 's/\(vmg0000 \)/\(vmg0000 -RRB-/g' *.xml
perl -i -pe 's/\(vmm0000 \)/\(vmm0000 -RRB-/g' *.xml
perl -i -pe 's/\(vmn0000 \)/\(vmn0000 -RRB-/g' *.xml
perl -i -pe 's/\(vsmp000 \)/\(vsmp000 -RRB-/g' *.xml
perl -i -pe 's/\(vmip000 \)/\(vmip000 -RRB-/g' *.xml
perl -i -pe 's/\(vmsp000 \)/\(vmsp000 -RRB-/g' *.xml
perl -i -pe 's/\(vmii000 \)/\(vmii000 -RRB-/g' *.xml
perl -i -pe 's/\(vmis000 \)/\(vmis000 -RRB-/g' *.xml
perl -i -pe 's/\(vmsi000 \)/\(vmsi000 -RRB-/g' *.xml
perl -i -pe 's/\(f0 \)/\(f0 -RRB-/g' *.xml
perl -i -pe 's/\(PU \(/\(PU -LRB-/g' *.xml
perl -i -pe 's/\(i \(/\(i -LRB-/g' *.xml
perl -i -pe 's/\(fca \(/\(fca -LRB-/g' *.xml
perl -i -pe 's/\(fat \(/\(fat -LRB-/g' *.xml
perl -i -pe 's/\(fit \(/\(fit -LRB-/g' *.xml
perl -i -pe 's/\(fct \(/\(fct -LRB-/g' *.xml
perl -i -pe 's/\(fz \(/\(fz -LRB-/g' *.xml
perl -i -pe 's/\(fs \(/\(fs -LRB-/g' *.xml
perl -i -pe 's/\(XX \(/\(XX -LRB-/g' *.xml
perl -i -pe 's/\(nc0p000 \(/\(nc0p000 -LRB-/g' *.xml
perl -i -pe 's/\(np00000 \(/\(np00000 -LRB-/g' *.xml
perl -i -pe 's/\(aq0000 \(/\(aq0000 -LRB-/g' *.xml
perl -i -pe 's/\(dn0000 \(/\(dn0000 -LRB-/g' *.xml
perl -i -pe 's/\(vmg0000 \(/\(vmg0000 -LRB-/g' *.xml
perl -i -pe 's/\(vmm \(/\(vmm -LRB-/g' *.xml
perl -i -pe 's/\(vmn0000 \(/\(vmn0000 -LRB-/g' *.xml
perl -i -pe 's/\(vsmp000 \(/\(vsmp000 -LRB-/g' *.xml
perl -i -pe 's/\(vmip000 \(/\(vmip000 -LRB-/g' *.xml
perl -i -pe 's/\(vmsp000 \(/\(vmsp000 -LRB-/g' *.xml
perl -i -pe 's/\(vmii000 \(/\(vmii000 -LRB-/g' *.xml
perl -i -pe 's/\(vmis000 \(/\(vmis000 -LRB-/g' *.xml
perl -i -pe 's/\(vmsi000 \(/\(vmsi000 -LRB-/g' *.xml
perl -i -pe 's/\(f0 \(/\(f0 -LRB-/g' *.xml
perl -i -pe 's/<word>\(<\/word>/<word>-LRB-<\/word>/g' *.xml
perl -i -pe 's/<word>\)<\/word>/<word>-RRB-<\/word>/g' *.xml

