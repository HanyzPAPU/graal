#include <cstdint>
#include <cstdio>
//#include <stdlib.h>
//#include <dlfcn.h>

//#include <jni.h>

//#include "jdk_graal_compiler_core_test_bytecodefuzz_MutatorHarness.h"

extern "C" size_t LLVMFuzzerCustomMutator(uint8_t *Data, size_t Size, size_t MaxSize, unsigned int Seed) {    
    printf("Hello from mutator!\n");
    return Size;
}

// JNIEXPORT void JNICALL Java_jdk_graal_compiler_core_test_bytecodefuzz_MutatorHarness_initMutator (JNIEnv *, jclass){
//     printf("Init Mutator Called!");
// }