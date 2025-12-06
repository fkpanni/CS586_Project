@echo off
echo Gas Pump MDA System - Simple Launcher
echo =====================================
echo.

REM Check if in src directory
if exist "Main.class" (
    echo Running from current directory...
    java Main
) else if exist "src\Main.class" (
    echo Running from src directory...
    cd src
    java Main
) else if exist "src\Main.java" (
    echo Compiling and running...
    cd src
    javac *.java
    if %errorlevel% equ 0 (
        java Main
    ) else (
        echo Compilation failed!
        pause
    )
) else (
    echo Error: Cannot find source files!
    echo Make sure you're in the project folder.
    pause
)