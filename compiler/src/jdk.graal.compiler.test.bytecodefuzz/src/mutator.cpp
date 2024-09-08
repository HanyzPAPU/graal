#include <cstdint>
#include <cstdio>
#include <algorithm>
#include <limits>

#include <jni.h>

#include "jdk_graal_compiler_test_bytecodefuzz_MutatorHarness.h"

static JNIEnv* gEnv = nullptr;
static JavaVM *gJavaVm = nullptr;
static jclass gHarness;
static jmethodID gMutate;

extern "C" size_t LLVMFuzzerCustomMutator(uint8_t *Data, size_t Size, size_t MaxSize, unsigned int Seed) {    
    // TODO: perhaps attach to a jvm, because this could be called from a different thread?
    // TODO: check that this even makes sense and that it does not have races

    jint jSize = std::min(Size, static_cast<size_t>(std::numeric_limits<jint>::max()));
    jint jmaxSize = std::min(MaxSize, static_cast<size_t>(std::numeric_limits<jint>::max()));
    jint jseed = static_cast<jint>(Seed);

    jbyteArray input = gEnv->NewByteArray(Size);

    static_assert(sizeof(jbyte) == sizeof(uint8_t));

    // Note: Memory copying occurs here
    gEnv->SetByteArrayRegion(input, 0, Size, (jbyte*)Data);

    jbyteArray output = (jbyteArray)gEnv->CallStaticObjectMethod(gHarness, gMutate, input, jmaxSize, jseed);

    if (gEnv->ExceptionCheck()) {
        gEnv->ExceptionDescribe();
        _Exit(1);
    }

    if (gEnv->IsSameObject(output, NULL)) {
        gEnv->DeleteLocalRef(input);
        return -1;
    }

    jsize newSize = gEnv->GetArrayLength(output);

    if (newSize > jmaxSize) {
        _Exit(1);
    }

    // Note: Memory copying occurs here
    gEnv->GetByteArrayRegion(output, 0, newSize, (jbyte*)Data);

    // Free local refs, to not leak memory
    gEnv->DeleteLocalRef(output);
    gEnv->DeleteLocalRef(input);
    
    return newSize;
}

JNIEXPORT void JNICALL Java_jdk_graal_compiler_test_bytecodefuzz_MutatorHarness_initMutator(JNIEnv *env, jclass harness){
    gEnv = env;
    env->GetJavaVM(&gJavaVm);
    gHarness = reinterpret_cast<jclass>(env->NewGlobalRef(harness));
    gMutate = env->GetStaticMethodID(harness, "mutate", "([BII)[B");
}
