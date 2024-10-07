#!/bin/bash

CORPUS_SEEDS="Duff FevenSum Automaton" # Interpreter - Too Many Loops

for SEED in $CORPUS_SEEDS; do
    cp precorpus/$SEED.class corpus/
done

cp ../../mxbuild/jdk.graal.compiler.test.bytecodefuzz/bin/jdk/graal/compiler/test/bytecodefuzz/gen/corpus/* corpus/
