# Bytecodelevel Fuzzing of Graal

## Build

Build with `mx`, run `mx build` in this directory.
Note that you will need a `jvmci` compatible JVM installed for this (you can get it by running `mx fetch-jdk`).

You also need to compile all of the custom sources in the `precorpus` directory.
Compile the `.jasm` files with [jasm](https://github.com/roscopeco/jasm) and java sources with `javac`.

## Running

To populate the corpus with the initial data, run `./populate-corpus`, to run the fuzzer itself run `./run-jazzer.sh`.
