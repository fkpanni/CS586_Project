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

REM Check Java
where java >nul 2>&1
if %errorlevel% equ 0 (
    echo ✓ Java is installed
    java -version 2>&1 | find "version"
) else (
    echo ✗ Java NOT found!
    echo.
    echo This program requires Java to run.
    echo.
    echo OPTION 1: Install Java from:
    echo   https://www.oracle.com/java/technologies/javase-jre8-downloads.html
    echo.
    echo OPTION 2: Run VIA Github Codespace 
    echo.
    pause
    exit /b 1
)

echo.
echo Starting Gas Pump System...
timeout /t 2 /nobreak >nul

REM Run the JAR if it exists
if exist "GasPumpSystem.jar" (
    echo Running from JAR file...
    java -jar GasPumpSystem.jar
) else (
    echo JAR not found, running from source...
    if exist "src\Main.class" (
        cd SourceCode
        java Main
    ) else (
        echo Compiling source code...
        cd SourceCode
        javac *.java
        if %errorlevel% equ 0 (
            echo Compilation successful!
            java Main
        ) else (
            echo Compilation failed!
            pause
        )
    )
)

echo.
echo Program execution completed.
echo.
pause