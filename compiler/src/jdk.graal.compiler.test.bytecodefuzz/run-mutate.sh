#!/bin/bash

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"

mx vm -cp $CLASSPATH jdk.graal.compiler.test.bytecodefuzz.MutateAndPrint
