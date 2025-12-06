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
    java -version 2>&1 | head -n 3
else
    echo -e "${RED}✗ Java NOT found!${NC}"
    echo
    echo "This program requires Java to run."
    echo
    echo "On Linux/Mac, install with:"
    echo "  sudo apt update && sudo apt install default-jdk"
    echo "  or"
    echo "  brew install openjdk"
    echo
    echo "On Windows, download from:"
    echo "  https://www.oracle.com/java/technologies/javase-jdk8-downloads.html"
    echo
    exit 1
fi

echo

# Check if Java compiler is installed
if command -v javac &> /dev/null; then
    echo -e "${GREEN}✓ Java compiler (javac) is installed${NC}"
    javac -version 2>&1
else
    echo -e "${RED}✗ Java compiler (javac) NOT found!${NC}"
    echo
    echo "You need Java Development Kit (JDK), not just Java Runtime (JRE)."
    echo
    echo "Install JDK 8 or higher from:"
    echo "  https://www.oracle.com/java/technologies/javase-jdk8-downloads.html"
    echo
    exit 1
fi

echo
echo "Starting Gas Pump System..."
sleep 1
echo

# Check if we're in the right directory structure
if [ -d "src" ]; then
    echo -e "${GREEN}✓ Found src/ directory${NC}"
    
    # Always clean and recompile to avoid Java version issues
    echo "Cleaning old compiled files..."
    if [ -f "src/Main.class" ]; then
        rm -f src/*.class
        echo -e "  ${GREEN}✓ Removed old .class files${NC}"
    else
        echo -e "  ${YELLOW}No old .class files found${NC}"
    fi
    
    echo
    echo "Compiling source code..."
    echo "========================"
    
    cd src
    
    # Try to compile with Java 8 compatibility if possible
    echo "Compiling Java files..."
    if javac -target 1.8 -source 1.8 *.java 2>/dev/null; then
        echo -e "${GREEN}✓ Compilation successful ${NC}"
    else
        # Fall back to regular compilation
        echo "Trying regular compilation..."
        if javac *.java; then
            echo -e "${GREEN}✓ Compilation successful${NC}"
        else
            echo -e "${RED}✗ Compilation failed!${NC}"
            echo
            echo "Possible issues:"
            echo "1. Missing .java files"
            echo "2. Syntax errors in code"
            echo "3. Java version mismatch"
            echo
            cd ..
            exit 1
        fi
    fi
    
    echo
    echo -e "${BLUE}Running Gas Pump MDA System...${NC}"
    echo -e "${BLUE}==============================${NC}"
    echo
    
    java Main
    
    cd ..
    
else
    echo -e "${RED}Error: 'src' directory not found!${NC}"
    echo
    echo "Expected structure:"
    echo "  ./src/   - Contains all .java files"
    echo "  ./exec/  - Optional for JAR files"
    echo
    echo "Current directory: $(pwd)"
    echo
    echo "Files found:"
    ls -la
    echo
    exit 1
fi

echo
echo -e "${GREEN}======================================================${NC}"
echo -e "${GREEN}        Program execution completed successfully       ${NC}"
echo -e "${GREEN}======================================================${NC}"
echo