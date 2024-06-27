#!/bin/bash

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
CORPUSDIR="./corpus"

# LD_PRELOAD prefers the use of absolute paths, this works if you run it from the directory this script is located in
export LD_PRELOAD="$PWD/src/build/libmutator.so"

# @export-hack exports the needed packages to all unknown modules, Jazzer being one of those unnamed modules
# -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading - recommended command line options
# -XX:-UseJVMCICompiler - disables Graal as the top tier JIT compiler => don't corrupt instrumentation by other compilations
# instrumentation includes only compiler code, not the test or reference interpreter

# TODO: still instruments unwanted classes with custom hooks

mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading -XX:-UseJVMCICompiler \
    -Djava.library.path="$PWD/src/build/" \
    -Dgraal.MaxDuplicationFactor=4.0 \
    -cp $CLASSPATH \
    com.code_intelligence.jazzer.Jazzer \
    --instrumentation_excludes=jdk.graal.compiler.core.test.**:jdk.graal.compiler.test.**:org.objectweb.asm.** \
    --instrumentation_includes=jdk.graal.compiler.** \
    --target_class=jdk.graal.compiler.test.bytecodefuzz.FuzzTarget \
    --reproducer_path=./reproducers/ \
    $CORPUSDIR $@
