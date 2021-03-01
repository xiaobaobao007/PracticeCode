@echo off
title=配表启动检测
set _lib=
set _path=lib
setlocal enabledelayedexpansion
for %%i in (%_path%/*.jar) do (
    set _lib=!_lib!%_path%/%%i;
)
echo %_lib%
java -Dfile.encoding=UTF-8 -cp %_lib% BinaryBat2Excel
pause