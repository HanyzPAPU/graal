#!/bin/bash

CORPUS_SEEDS="Duff FevenSum Automaton"

for SEED in $CORPUS_SEEDS; do
    cp precorpus/$SEED.class corpus/
done
