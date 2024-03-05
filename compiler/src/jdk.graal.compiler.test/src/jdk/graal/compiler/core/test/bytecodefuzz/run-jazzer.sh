#!/bin/bash

#TODO: do this in a sane way that is not dependent on absolute paths and perhaps dont input the cache of MX directly (with the sha512 digests)

CLASSPATH="/home/honza/graal/compiler/mxbuild/dists/graal-test.jar"
CLASSPATH="$CLASSPATH:/home/honza/graal/compiler/mxbuild/dists/graal.jar"
CLASSPATH="$CLASSPATH:/home/honza/graal/sdk/mxbuild/dists/collections.jar"
# These packages have been explicitely excluded from the distribution, but we need them to run the fuzzing
CLASSPATH="$CLASSPATH:/home/honza/.mx/cache/JAZZER_5b5a19b89060020424e9e1e0ed3e6e8fecc82eb924656e1472058dc1e4c377f825057c53940068cef59c701f9eb86f970a2144fcc02c80b388c7c2e3e75a4621/jazzer.jar"
CLASSPATH="$CLASSPATH:/home/honza/.mx/cache/JAZZER_API_65349c41255c90400992b5d79173fd68c67597f05d70d1b748cd5b79a4c2a036c952f1c93c71a7fe4554a4e89e1838923acae1e26da3048fe3797ff58bfe8a0b/jazzer-api.jar"
CLASSPATH="$CLASSPATH:/home/honza/.mx/cache/JAVA_ALLOCATION_INSTRUMENTER_25fe57cd6d3ecabb52f411c884f801109ece37570a2dd19fa1e5b83cc2039ed02a90787600eb9303eaa730aabf0dc70b506fb9fe40ca6c3417428bb89c2c8940/java-allocation-instrumenter.jar"
CLASSPATH="$CLASSPATH:/home/honza/.mx/cache/JUNIT_5974670c3d178a12da5929ba5dd9b4f5ff461bdc1b92618c2c36d53e88650df7adbf3c1684017bb082b477cb8f40f15dcf7526f06f06183f93118ba9ebeaccce/junit.jar"
CLASSPATH="$CLASSPATH:/home/honza/.mx/cache/HAMCREST_e237ae735aac4fa5a7253ec693191f42ef7ddce384c11d29fbf605981c0be077d086757409acad53cb5b9e53d86a07cc428d459ff0f5b00d32a8cbbca390be49/hamcrest.jar"

CORPUSDIR=/home/honza/graal/compiler/src/jdk.graal.compiler.test/src/jdk/graal/compiler/core/test/bytecodefuzz/corpus

#export LD_PRELOAD=/home/honza/graal/compiler/src/jdk.graal.compiler.test/src/jdk/graal/compiler/core/test/bytecodefuzz/build/libmutator.so
# export LD_DEBUG=libs

# @export-hack exports the needed packages to all unknown modules, Jazzer being one of those unnamed modules
# -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading - recommended command line options
# instrumentation includes only compiler code, not the test or reference interpreter

mx vm @export-hack \
    -XX:+UseParallelGC -XX:+EnableDynamicAgentLoading \
    -cp $CLASSPATH \
    com.code_intelligence.jazzer.Jazzer \
    --instrumentation_includes=jdk.graal.compiler.** \
    --instrumentation_excludes=jdk.graal.compiler.core.test**:jdk.graal.compiler.test** \
    --target_class=jdk.graal.compiler.core.test.bytecodefuzz.FuzzSandbox \
    --reproducer_path=./reproducers/ \
    $CORPUSDIR $@