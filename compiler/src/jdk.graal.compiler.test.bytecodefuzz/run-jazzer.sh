#!/bin/bash

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
CORPUSDIR="./corpus"

# LD_PRELOAD prefers the use of absolute paths, this works if you run it from the directory this script is located in
export LD_PRELOAD="$PWD/src/build/libmutator.so"

# @export-hack exports the needed packages to all unknown modules, Jazzer being one of those unnamed modules
# -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading - recommended command line options
# -XX:-UseJVMCICompiler - disables Graal as the top tier JIT compiler => don't corrupt instrumentation by other compilations
# instrumentation includes only compiler code, not the test or reference interpreter
# -Dgraal.MaxDuplicationFactor=1000.0 to make Graal compile even very convoluted irreducible code

# TODO: still instruments unwanted classes with custom hooks
# TODO: make timeout longer of real test? also ignore_timeouts should probably be True for real run
# TODO: should the real run ignore crashes?
# TODO: fork mode

mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading -XX:-UseJVMCICompiler \
    -Xmx2g \
    -Djava.library.path="$PWD/src/build/" \
    -Djdk.graal.MaxDuplicationFactor=1000.0 \
    -cp $CLASSPATH \
    com.code_intelligence.jazzer.Jazzer \
    --instrumentation_excludes=jdk.graal.compiler.core.test.**:jdk.graal.compiler.test.**:org.objectweb.asm.** \
    --instrumentation_includes=jdk.graal.compiler.** \
    --target_class=jdk.graal.compiler.test.bytecodefuzz.FuzzTarget \
    --reproducer_path=./reproducers/ \
    -max_len=8192 -timeout=60 -ignore_timeouts=1 -ignore_crashes=0 -fork=4 \
    $CORPUSDIR $@
