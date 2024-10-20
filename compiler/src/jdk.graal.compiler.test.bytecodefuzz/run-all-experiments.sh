#!/bin/bash

# Runtime of one experiment in seconds
RUNTIME=240
# Runtime of the experiment where we first prefuzz the corpus with jumps only, the two times should sum up to RUNTIME
JUMP_PREFUZZ_PRETIME=60
JUMP_PREFUZZ_RUNTIME=180

# Expects that mx is on path and that JAVA_HOME is set to a JVMCI compiler

echo "Building..."

Build in a subshell because of the cd
(
    mx build
    cd src
    mkdir -p build
    cd build
    cmake ..
    make
)

echo "Build Succeded!"

echo "Running experiments..."
echo ""

echo "DEFAULT = All mutators" 
./run-experiment.sh DEFAULT $RUNTIME

echo "NO-MUTATORS = Default libFuzzer/Jazzer mutators"

echo "CONSERVATIVE = Preserves that the program halts"

./run-experiment.sh CONSERVATIVE $RUNTIME -DfuzzMutConservative=true

echo "CONSERVATIVE-SANS-JUMP = Same as CONSERVATIVE but without jump insertion"

./run-experiment.sh CONSERVATIVE-SANS-JUMP $RUNTIME -DfuzzMutConservative=true -DfuzzMutHasJump=false

echo "DEFAULT-SANS-JUMP = Same as DEFAULT but without jump insertion"

./run-experiment.sh DEFAULT-SANS-JUMP $RUNTIME -DfuzzMutHasJump=false

echo "JUMP-INSERTION-PRE-FUZZ = First fuzzes by only inserting jumps, then swithes to the DEAFAULT configuration"

./run-experiment.sh JUMP-INSERTION-PRE-FUZZ-TRAIN $JUMP_PREFUZZ_PRETIME -DfuzzMutJumpOnly=true
PRESERVE_CORPUS=1 ./run-experiment.sh JUMP-INSERTION-PRE-FUZZ-RUN $JUMP_PREFUZZ_RUNTIME

## TODO: run this on default configuration?

echo ""
echo "Jump Insertion parameter - which distribution to choose"
echo ""

echo "JUMP-PROB-LOC = Probability that each location will be selected is the same (Same as default - skipping)"

# ./run-experiment.sh JUMP-PROB-LOC $RUNTIME -DfuzzJumpProb=loc

echo "JUMP-PROB-PAIR = Probability that each pair of location will be selected is the same"

./run-experiment.sh JUMP-PROB-PAIR $RUNTIME -DfuzzJumpProb=pair

echo "JUMP-PROB-SQRT = Probability is somewhere in the middle"

./run-experiment.sh JUMP-PROB-SQRT $RUNTIME -DfuzzJumpProb=sqrt

echo ""
echo "Derefing probability parameter - Value push insertion and Write insertion can deref values."
echo ""

echo "DEREF-FREQ-3 = Deref probability = 1/3 (Same as default - skipping)"

# ./run-experiment.sh DEREF-FREQ-3 $RUNTIME -DfuzzDerefInverseFreq=3

echo "DEREF-FREQ-2 = Deref probability = 1/2"

./run-experiment.sh DEREF-FREQ-2 $RUNTIME -DfuzzDerefInverseFreq=2

echo "DEREF-FREQ-4 = Deref probability = 1/4"

./run-experiment.sh DEREF-FREQ-4 $RUNTIME -DfuzzDerefInverseFreq=4
