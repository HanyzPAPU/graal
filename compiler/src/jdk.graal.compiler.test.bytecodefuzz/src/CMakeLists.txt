cmake_minimum_required(VERSION 3.25.1)

find_package(JNI REQUIRED)

project("mutator")

# We need to compile with clang to hook into libFuzzer
set(CMAKE_C_COMPILER "/usr/bin/clang")
set(CMAKE_CXX_COMPILER "/usr/bin/clang++")

add_library(mutator SHARED mutator.cpp)
target_compile_options(mutator PRIVATE -g -fPIC)
target_include_directories(mutator PUBLIC ${JNI_INCLUDE_DIRS})
target_include_directories(mutator PUBLIC ${JAVA_INCLUDE_PATH2})
target_link_options(mutator PRIVATE -g)
