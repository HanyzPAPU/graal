#!/bin/bash

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"
# Mock LibFuzzer Mutator, so we do not have to start it
export JAZZER_MOCK_LIBFUZZER_MUTATOR="true"

mx vm @export-hack -cp $CLASSPATH jdk.graal.compiler.test.bytecodefuzz.MutateAndPrint -- $@
