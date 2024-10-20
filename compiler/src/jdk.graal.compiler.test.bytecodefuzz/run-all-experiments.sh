#!/bin/bash

# Expects that mx is on path and that JAVA_HOME is set to a JVMCI compiler

echo "Building..."

# mx build

(
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
./run-experiment.sh DEFAULT

echo "NO-MUTATORS = Default libFuzzer/Jazzer mutators"

echo "CONSERVATIVE = Preserves that the program halts"

./run-experiment.sh CONSERVATIVE -DfuzzMutConservative=true

echo "CONSERVATIVE-SANS-JUMP = Same as CONSERVATIVE but without jump insertion"

./run-experiment.sh CONSERVATIVE-SANS-JUMP -DfuzzMutConservative=true -DfuzzMutHasJump=false

echo "DEFAULT-SANS-JUMP = Same as DEFAULT but without jump insertion"

./run-experiment.sh DEFAULT-SANS-JUMP -DfuzzMutHasJump=false

#TODO: echo "JUMP-INSERTION-PRE-FUZZ = First fuzzes by only inserting jumps, then swithes to the DEAFAULT configuration"

# TODO: run jump insertion only for like an hour?
# TODO: run default for one hour less than the rest


## TODO: run this on default configuration?

echo ""
echo "Jump Insertion parameter - which distribution to choose"
echo ""

echo "JUMP-PROB-LOC = Probability that each location will be selected is the same (Same as default - skipping)"

# ./run-experiment.sh JUMP-PROB-LOC -DfuzzJumpProb=loc

echo "JUMP-PROB-PAIR = Probability that each pair of location will be selected is the same"

./run-experiment.sh JUMP-PROB-PAIR -DfuzzJumpProb=pair

echo "JUMP-PROB-SQRT = Probability is somewhere in the middle"

./run-experiment.sh JUMP-PROB-SQRT -DfuzzJumpProb=sqrt

echo ""
echo "Derefing probability parameter - Value push insertion and Write insertion can deref values."
echo ""

echo "DEREF-FREQ-3 = Deref probability = 1/3 (Same as default - skipping)"

# ./run-experiment.sh DEREF-FREQ-3 -DfuzzDerefInverseFreq=3

echo "DEREF-FREQ-2 = Deref probability = 1/2"

./run-experiment.sh DEREF-FREQ-2 -DfuzzDerefInverseFreq=2

echo "DEREF-FREQ-4 = Deref probability = 1/4"

./run-experiment.sh DEREF-FREQ-4 -DfuzzDerefInverseFreq=4
