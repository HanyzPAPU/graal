#!/bin/bash

# Expects env. variables RUNTIME, CPUS and MAX_MEMORY to be set

# TODO: longer timeout

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
CORPUSDIR="./corpus"

EXPERIMENT_NAME=$1
EXPERIMENT_MAIN_LOG=$LOG_DIR/$EXPERIMENT_NAME.log

# Prepare the initial corpus

if [ -z "$PRESERVE_CORPUS" ]; then
    rm -f corpus/*
    ./populate-corpus.sh
fi

# Read from output log files and redirect them to experiment named files with timestamps
mkdir -p $LOG_DIR
for I in $(seq 0 $(($CPUS-1)))
do
    JAZZER_LOG_FILE=fuzz-$I.log
    EXPERIMENT_LOG_FILE=$LOG_DIR/$EXPERIMENT_NAME-$JAZZER_LOG_FILE
    # clear out jazzer log file
    echo "" > $JAZZER_LOG_FILE
    # only append to experiment logs
    touch $EXPERIMENT_LOG_FILE
    tail --pid=$$ -f $JAZZER_LOG_FILE | while IFS= read -r line; do printf '[%s] %s\n' "$(date '+%Y-%m-%d %H:%M:%S')" "$line" >> $EXPERIMENT_LOG_FILE; done &
done

# Run the fuzzing

if [ -z "$NO_MUTATORS" ]; then
    export LD_PRELOAD="$PWD/src/build/libmutator.so"
fi

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
    -max_len=8192 -timeout=180 -max_total_time=$RUNTIME -jobs=$CPUS -workers=$CPUS -reload=10 -print_final_stats=1 \
    $CORPUSDIR 2>&1 | while IFS= read -r line; do printf '[%s] %s\n' "$(date '+%Y-%m-%d %H:%M:%S')" "$line" >> $EXPERIMENT_MAIN_LOG ; done

tail $EXPERIMENT_MAIN_LOG
