#include <cstdint>
#include <cstdio>

#include <jni.h>

#include "jdk_graal_compiler_core_test_bytecodefuzz_MutatorHarness.h"

static JNIEnv* gEnv = nullptr;

extern "C" size_t LLVMFuzzerCustomMutator(uint8_t *Data, size_t Size, size_t MaxSize, unsigned int Seed) {    
    printf("Hello from mutator! Env: %p\n", gEnv);
    return Size;
}

JNIEXPORT void JNICALL Java_jdk_graal_compiler_core_test_bytecodefuzz_MutatorHarness_initMutator (JNIEnv *env, jclass){
    printf("Init Mutator Called! Env: %p\n", env);
    gEnv = env;
}