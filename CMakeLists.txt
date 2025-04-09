cmake_minimum_required(VERSION 3.29)
project(game_logic)


set(CMAKE_CXX_STANDARD 20)


add_library(game_logic SHARED
        game_Field.cpp
        game_Field.h)


target_include_directories(game_logic PRIVATE "C:/Program Files/Java/jdk-23/include")
target_include_directories(game_logic PRIVATE "C:/Program Files/Java/jdk-23/include/win32")