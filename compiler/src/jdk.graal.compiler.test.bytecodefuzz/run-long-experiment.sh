#!/bin/bash

# Parameters

# Runtime of one experiment in seconds
RUNTIME=86400 # 24 hours

# Number of available CPUs
export CPUS=8
# -Xmx JVM option
export MAX_MEMORY=2g

LOG_DIR=logs
EXPERIMENT_NAME=LONG
EXPERIMENT_MAIN_LOG=$LOG_DIR/$EXPERIMENT_NAME.log
JOBS=128

rm -f logs.zip logs/*

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

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
CORPUSDIR="./corpus"

# Prepare the initial corpus

rm -f corpus/*
./populate-corpus.sh

# Read from output log files and redirect them to experiment named files with timestamps
mkdir -p $LOG_DIR
for I in $(seq 0 $(($JOBS-1)))
do
    JAZZER_LOG_FILE=fuzz-$I.log
    EXPERIMENT_LOG_FILE=$LOG_DIR/$EXPERIMENT_NAME-$JAZZER_LOG_FILE
    # clear out jazzer log file
    # only append to experiment logs
    rm -f $JAZZER_LOG_FILE
    touch $JAZZER_LOG_FILE
    tail --pid=$$ -F $JAZZER_LOG_FILE | while IFS= read -r line; do printf '[%s] %s\n' "$(date '+%Y-%m-%d %H:%M:%S')" "$line" >> $EXPERIMENT_LOG_FILE; done &
done

# Run the fuzzing

export LD_PRELOAD="$PWD/src/build/libmutator.so"
mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading -XX:-UseJVMCICompiler \
    -Xmx$MAX_MEMORY \
    "${@:2}" \
    -Djava.library.path="$PWD/src/build/" \
    -Djdk.graal.MaxDuplicationFactor=1000.0 \
    -Djdk.graal.CompilationFailureAction=Print \
    -cp $CLASSPATH \
    com.code_intelligence.jazzer.Jazzer \
    --instrumentation_excludes=jdk.graal.compiler.core.test.**:jdk.graal.compiler.test.**:org.objectweb.asm.** \
    --instrumentation_includes=jdk.graal.compiler.** \
    --target_class=jdk.graal.compiler.test.bytecodefuzz.FuzzTarget \
    --reproducer_path=./reproducers/ \
    -max_len=8192 -timeout=240 -max_total_time=$RUNTIME -jobs=$JOBS -workers=$CPUS -reload=30 -print_final_stats=1 \
    $CORPUSDIR 2>&1 | while IFS= read -r line; do printf '[%s] %s\n' "$(date '+%Y-%m-%d %H:%M:%S')" "$line" >> $EXPERIMENT_MAIN_LOG ; done

tail $EXPERIMENT_MAIN_LOG


# zip up all logs and other interesing thingies to one artefact
zip -r logs.zip $LOG_DIR corpus/ timeout-* crash-* reproducers/ hs_err_pid* graal_dumps/
