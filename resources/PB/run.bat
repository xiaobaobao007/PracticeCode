@echo off

protoc.exe helloworld.proto --java_out="."
protoc.exe any.proto --java_out="."

copy /y D:\PracticeCode\resources\PB\NIO\PB\HelloWorld\*.java D:\PracticeCode\src\NIO\PB\HelloWorld\