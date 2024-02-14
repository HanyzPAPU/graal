cmake_minimum_required(VERSION 3.25.1)

project("mutator")
add_library(mutator SHARED mutator.cpp)
target_compile_options(mutator PRIVATE -g -fPIC)
target_link_options(mutator PRIVATE -g)
