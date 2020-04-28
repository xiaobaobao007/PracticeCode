@echo off

protoc.exe helloworld.proto --java_out="."

copy /y D:\PracticeCode\resources\PB\NIO\PB\HelloWorld\Helloworld.java D:\PracticeCode\src\NIO\PB\HelloWorld\