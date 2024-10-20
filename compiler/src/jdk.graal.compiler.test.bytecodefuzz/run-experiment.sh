#!/bin/bash

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
CORPUSDIR="./corpus"
CPUS=4

# TODO: increase max memory from 2 gigs
# TODO: increase the number of CPUs
# TODO: update max runtime to more than 4 minutes

EXPERIMENT_NAME=$1

# Prepare the initial corpus
rm -f corpus/*
./populate-corpus.sh

# Read from output log files and redirect them to experiment named files with timestamps
for I in $(seq 0 $(($CPUS-1)))
do
    JAZZER_LOG_FILE=fuzz-$I.log
    EXPERIMENT_LOG_FILE=$EXPERIMENT_NAME-$JAZZER_LOG_FILE
    # clear out jazzer log file
    echo "" > $JAZZER_LOG_FILE
    # only append to experiment logs
    touch $EXPERIMENT_LOG_FILE
    tail --pid=$$ -f $JAZZER_LOG_FILE | while IFS= read -r line; do printf '[%s] %s\n' "$(date '+%Y-%m-%d %H:%M:%S')" "$line" >> $EXPERIMENT_LOG_FILE; done &
done

# Run the fuzzing
export LD_PRELOAD="$PWD/src/build/libmutator.so"
mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading -XX:-UseJVMCICompiler \
    -Xmx2g \
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
    -max_len=8192 -timeout=60 -max_total_time=240 -jobs=$CPUS -workers=$CPUS -reload=10 -print_final_stats=1 \
    $CORPUSDIR  
