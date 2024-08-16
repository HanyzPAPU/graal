#!/bin/bash

CLASSPATH="../../mxbuild/dists/graal-test-bytecodefuzz.jar"

cp ./reproducers/$1.java ./src/$1.java
mx build

mx vm @export-hack  -XX:-UseJVMCICompiler -Djdk.graal.Dump=:2 -Djdk.graal.PrintGraph=Network -Djava.library.path="$PWD/src/build/" -cp ../../mxbuild/dists/graal-test-bytecodefuzz.jar $1

echo "Remember to remove src/$1.java"
