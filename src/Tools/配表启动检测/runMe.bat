@echo off
title=配表启动检测
set _lib=
set _path=lib
set excelPath=D:\feitu\策划配表\
setlocal enabledelayedexpansion
for %%i in (%_path%/*.jar) do (
    set _lib=!_lib!%_path%/%%i;
)
echo %_lib%
java -Dfile.encoding=UTF-8 -cp %_lib% Main %excelPath%
pause