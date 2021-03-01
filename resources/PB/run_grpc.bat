@echo off

protoc --plugin=protoc-gen-grpc-java=protoc-gen-grpc-java.exe  --grpc-java_out="." --proto_path="." "./helloworld.proto"

rem copy /y D:\PracticeCode\resources\PB\NIO\PB\HelloWorld\GreeterGrpc.java D:\PracticeCode\src\NIO\PB\HelloWorld\