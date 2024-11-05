#!/bin/bash

# Parameters

# Runtime of one experiment in seconds
export RUNTIME=7200 # 2 hours
# Runtime of the experiment where we first prefuzz the corpus with jumps only, the two times should sum up to RUNTIME
JUMP_PREFUZZ_PRETIME=900 # 15 minutes
JUMP_PREFUZZ_RUNTIME=6300 # 1:45 hours

# Number of available CPUs
export CPUS=8
# -Xmx JVM option
export MAX_MEMORY=2g

export LOG_DIR=logs

rm -f logs.zip logs/*

# Expects that mx is on path and that JAVA_HOME is set to a JVMCI compiler

echo "Building..."

# Build in a subshell because of the cd
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
./run-experiment.sh DEFAULT

echo "JUMP-INSERTION-PRE-FUZZ-SHORT = First fuzzes by only inserting jumps, then swithes to the DEAFAULT configuration (training shortened to 15 minutes)"

RUNTIME=$JUMP_PREFUZZ_PRETIME ./run-experiment.sh JUMP-INSERTION-PRE-FUZZ-SHORT-TRAIN -DfuzzMutJumpOnly=true
RUNTIME=$JUMP_PREFUZZ_RUNTIME PRESERVE_CORPUS=1 ./run-experiment.sh JUMP-INSERTION-PRE-FUZZ-SHORT-RUN


# zip up all logs to one artefact
zip -r $LOG_DIR.zip $LOG_DIR
