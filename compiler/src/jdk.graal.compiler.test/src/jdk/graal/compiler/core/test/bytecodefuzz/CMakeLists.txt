cmake_minimum_required(VERSION 3.25.1)

# find_package(JNI REQUIRED)

project("mutator")

add_library(mutator SHARED mutator.cpp)
target_compile_options(mutator PRIVATE -g -fPIC)
#target_include_directories(mutator PUBLIC ${JNI_INCLUDE_DIRS})
#target_include_directories(mutator PUBLIC ${JAVA_INCLUDE_PATH2})
target_link_options(mutator PRIVATE -g)
