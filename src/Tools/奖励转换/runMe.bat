@echo off
set _lib=
set _path=resources
setlocal enabledelayedexpansion
for %%i in (%_path%/*.jar) do (
    set _lib=!_lib!%_path%/%%i;
)
echo %_lib%
@start javaw -cp %_lib% JFrameTools