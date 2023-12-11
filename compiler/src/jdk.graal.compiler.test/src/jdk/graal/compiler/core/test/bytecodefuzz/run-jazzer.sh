#!/bin/bash

#TODO: do this in a sane way that is not dependent on absolute paths
CLASSPATH=~/.mx/cache/JAZZER_5b5a19b89060020424e9e1e0ed3e6e8fecc82eb924656e1472058dc1e4c377f825057c53940068cef59c701f9eb86f970a2144fcc02c80b388c7c2e3e75a4621/jazzer.jar
CLASSPATH="$CLASSPATH:~/.mx/cache/JAZZER_API_65349c41255c90400992b5d79173fd68c67597f05d70d1b748cd5b79a4c2a036c952f1c93c71a7fe4554a4e89e1838923acae1e26da3048fe3797ff58bfe8a0b"
CLASSPATH="$CLASSPATH:~/graal/compiler/mxbuild/dists/graal-test.jar"
CLASSPATH="$CLASSPATH:~/graal/compiler/mxbuild/dists/graal.jar"
CLASSPATH="$CLASSPATH:~/graal/sdk/mxbuild/dists/collections.jar"
CLASSPATH="$CLASSPATH:~/.mx/cache/JAVA_ALLOCATION_INSTRUMENTER_25fe57cd6d3ecabb52f411c884f801109ece37570a2dd19fa1e5b83cc2039ed02a90787600eb9303eaa730aabf0dc70b506fb9fe40ca6c3417428bb89c2c8940/java-allocation-instrumenter.jar"

CORPUSDIR=~/graal/compiler/src/jdk.graal.compiler.test/src/jdk/graal/compiler/core/test/bytecodefuzz/corpus

mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading \
    -cp $CLASSPATH \
    com.code_intelligence.jazzer.Jazzer \
    --instrumentation_includes=jdk.graal.compiler.** \
    --instrumentation_excludes=jdk.graal.compiler.core.test**:jdk.graal.compiler.test** \
    --target_class=jdk.graal.compiler.core.test.bytecodefuzz.FuzzSandbox \
    $CORPUSDIR $@