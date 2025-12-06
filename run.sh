#!/bin/bash

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

clear
echo -e "${GREEN}======================================================${NC}"
echo -e "${GREEN}     CS 586 - GAS PUMP MDA SYSTEM - FINAL PROJECT     ${NC}"
echo -e "${GREEN}                FALL 2025 SUBMISSION                  ${NC}"
echo -e "${GREEN}======================================================${NC}"
echo

echo "Checking system requirements..."
echo

# Check if Java is installed
if command -v java &> /dev/null; then
    echo -e "${GREEN}✓ Java is installed${NC}"
    java -version
else
    echo -e "${RED}✗ Java NOT found!${NC}"
    echo
    echo "This program requires Java to run."
    echo
    echo "In Codespace, install Java with:"
    echo "  sudo apt update && sudo apt install default-jdk"
    echo
    exit 1
fi

echo
echo "Starting Gas Pump System..."
sleep 2

# Check if we're in the right directory structure
if [ -d "src" ] && [ -d "exec" ]; then
    echo "Found project structure: src/ and exec/"
    
    # Option 1: Run from JAR in exec directory
    if [ -f "exec/GasPumpSystem.jar" ]; then
        echo -e "${GREEN}Running from JAR file...${NC}"
        java -jar exec/GasPumpSystem.jar
    # Option 2: Run from source
    elif [ -f "src/Main.java" ]; then
        echo -e "${YELLOW}JAR not found, running from source...${NC}"
        
        # Check if already compiled
        if [ -f "src/Main.class" ]; then
            echo "Already compiled, running..."
            cd src
            java Main
            cd ..
        else
            echo "Compiling source code..."
            cd src
            javac *.java
            
            if [ $? -eq 0 ]; then
                echo -e "${GREEN}Compilation successful!${NC}"
                java Main
            else
                echo -e "${RED}Compilation failed!${NC}"
                exit 1
            fi
            cd ..
        fi
    else
        echo -e "${RED}Error: Could not find Main.java in src/ directory${NC}"
        exit 1
    fi
else
    echo -e "${RED}Error: Expected directory structure not found${NC}"
    echo "Expected:"
    echo "  ./src/   - Source code"
    echo "  ./exec/  - Executables"
    echo
    echo "Current directory: $(pwd)"
    ls -la
    exit 1
fi

echo
echo -e "${BLUE}Program execution completed.${NC}"
echo