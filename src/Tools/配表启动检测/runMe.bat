@echo off
title=����������
set _lib=
set _path=lib
set excelPath=D:\feitu\�߻����\
setlocal enabledelayedexpansion
for %%i in (%_path%/*.jar) do (
    set _lib=!_lib!%_path%/%%i;
)
echo %_lib%
java -Dfile.encoding=UTF-8 -cp %_lib% Main %excelPath%
pause