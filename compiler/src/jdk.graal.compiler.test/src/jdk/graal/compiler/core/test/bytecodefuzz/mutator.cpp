#include <cstdint>
#include <cstdio>

extern "C" size_t LLVMFuzzerCustomMutator(uint8_t *Data, size_t Size, size_t MaxSize, unsigned int Seed) {
    static bool printed = false;
    if(!printed){
        printed = true;
        printf("Hello from mutator!\n");
    }
    return Size;
}
