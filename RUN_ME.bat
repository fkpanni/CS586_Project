@echo off
title Gas Pump MDA System - CS 586 Final Project
color 0A
mode con: cols=80 lines=40

echo.
echo ╔══════════════════════════════════════════════════════╗
echo ║      CS 586 - GAS PUMP MDA SYSTEM - FINAL PROJECT    ║
echo ║                 FALL 2025 SUBMISSION                 ║
echo ╚══════════════════════════════════════════════════════╝
echo.

echo Checking system requirements...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% equ 0 (
    echo ✓ Java is installed
    for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
        echo   Java Version: %%g
    )
) else (
    echo ✗ Java NOT found!
    echo.
    echo This program requires Java to run.
    echo.
    echo Please install Java from:
    echo   https://www.oracle.com/java/technologies/javase-jre8-downloads.html
    echo.
    echo After installing Java, restart your computer and try again.
    echo.
    pause
    exit /b 1
)

echo.
echo Starting Gas Pump System...
timeout /t 1 /nobreak >nul
echo.

REM Get current directory (project root)
set "CURRENT_DIR=%CD%"
echo Project Root: %CURRENT_DIR%
echo.

REM Option 1: Run from JAR if it exists
if exist "exec\GasPumpSystem.jar" (
    echo Running from JAR file...
    echo ====================================
    java -jar exec\GasPumpSystem.jar
    goto :end
)

REM Option 2: Check if already compiled
if exist "src\Main.class" (
    echo Already compiled, running from class files...
    echo =============================================
    java -cp src Main
    goto :end
)

REM Option 3: Compile and run
echo Compiling source code...
echo ========================

REM Check if src directory exists
if not exist "src" (
    echo ✗ Error: 'src' directory not found!
    echo.
    echo Current directory contents:
    dir
    echo.
    pause
    exit /b 1
)

cd src

echo Compiling Java files...
javac *.java

if %errorlevel% neq 0 (
    echo.
    echo ✗ Compilation failed!
    echo Please check for errors in the Java files.
    cd ..
    pause
    exit /b 1
)

echo ✓ Compilation successful!
echo.
echo Running Gas Pump MDA System...
echo ==============================
java Main

cd ..

:end
echo.
echo ====================================
echo Program execution completed.
echo.
echo To run again, you can:
echo 1. Double-click RUN_ME.bat again
echo 2. Or open Command Prompt and type: java -cp src Main
echo.
pause