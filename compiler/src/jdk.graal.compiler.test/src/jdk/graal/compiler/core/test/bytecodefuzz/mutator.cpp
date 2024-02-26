#include <cstdint>
#include <cstdio>
#include <algorithm>
#include <limits>

#include <jni.h>

#include "jdk_graal_compiler_core_test_bytecodefuzz_MutatorHarness.h"

static JNIEnv* gEnv = nullptr;
static JavaVM *gJavaVm = nullptr;
static jclass gHarness;
static jmethodID gMutate;

extern "C" size_t LLVMFuzzerCustomMutator(uint8_t *Data, size_t Size, size_t MaxSize, unsigned int Seed) {    
    // TODO: perhaps attach to a jvm, because this could be called from a different thread?
    // TODO: check that this even makes sense and that it does not have races

    jint jsize = std::min(Size, static_cast<size_t>(std::numeric_limits<jint>::max()));
    jint jmaxSize = std::min(MaxSize, static_cast<size_t>(std::numeric_limits<jint>::max()));
    jint jseed = static_cast<jint>(Seed);

    jint newSize = gEnv->CallStaticLongMethod(gHarness, gMutate, Data, jsize, jmaxSize, jseed);
    
    if (gEnv->ExceptionCheck()) {
      gEnv->ExceptionDescribe();
      _Exit(1);
    }
    return static_cast<uint32_t>(newSize);
}

JNIEXPORT void JNICALL Java_jdk_graal_compiler_core_test_bytecodefuzz_MutatorHarness_initMutator (JNIEnv *env, jclass harness){
    gEnv = env;
    env->GetJavaVM(&gJavaVm);
    gHarness = reinterpret_cast<jclass>(env->NewGlobalRef(harness));
    gMutate = env->GetStaticMethodID(harness, "mutate", "(JIII)I");
}