#!/bin/bash

#TODO: do this in a sane way that is not dependent on absolute paths and perhaps dont input the cache of MX directly (with the sha512 digests)

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
CLASSPATH="$CLASSPATH:../../mxbuild/dists/graal.jar"
CLASSPATH="$CLASSPATH:../../../sdk/mxbuild/dists/collections.jar"
# These packages have been explicitely excluded from the distribution, but we need them to run the fuzzing

CORPUSDIR="./corpus"

export LD_PRELOAD=/home/honza/graal/compiler/src/jdk.graal.compiler.test.bytecodefuzz/src/build/libmutator.so

# @export-hack exports the needed packages to all unknown modules, Jazzer being one of those unnamed modules
# -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading - recommended command line options
# -XX:-UseJVMCICompiler - disables Graal as the top tier JIT compiler => don't corrupt instrumentation by other compilations
# instrumentation includes only compiler code, not the test or reference interpreter

# TODO: still instruments unwanted classes with custom hooks

mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading -XX:-UseJVMCICompiler \
    -Djava.library.path=/home/honza/graal/compiler/src/jdk.graal.compiler.test.bytecodefuzz/src/build/ \
    -cp $CLASSPATH \
    com.code_intelligence.jazzer.Jazzer \
    --instrumentation_excludes=jdk.graal.compiler.core.test.**:jdk.graal.compiler.test.**:org.objectweb.asm.** \
    --instrumentation_includes=jdk.graal.compiler.** \
    --target_class=jdk.graal.compiler.test.bytecodefuzz.FuzzSandbox \
    --reproducer_path=./reproducers/ \
    $CORPUSDIR $@
