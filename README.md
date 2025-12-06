# **CS 586 - Gas Pump MDA System - Final Project Submission**

## **📋 Quick Summary**
**Project:** Gas Pump MDA System with Three Design Patterns  
**Course:** CS 586 - Software Design  
**Semester:** Fall 2025  
**Due Date:** December 5, 2025  
**Student:** Fahd Khan
**Institution:** Illinois Institute Of Technology

-----------------------------------------------------------------

## THREE WAYS TO RUN THE PROGRAM**

### **Option 1: Script Execution in Bash (RECOMMENDED)**
    # 1. Make script executable
    chmod +x run.sh

    # 2. Run the program
    ./run.sh

    # That's it! The script will:
    # - Check Java installation
    # - Clean old files
    # - Compile source code
    # - Launch the program

### **Option 2: Manual Compilation**
    # 1. Navigate to source directory
    cd src

    # 2. Compile all Java files
    javac *.java

    # 3. Run the program
    java Main

    # Expected output: Program starts with main menu

### **Option 3: GitHub Codespace (Cloud)**
    # 1. Open project in GitHub Codespace
    # 2. Terminal opens automatically
    # 3. Use Option 1 or Option 2 above
    # No installation needed - Java is pre-installed
    
-----------------------------------------------------------------

## **10 TEST CASES FROM SPECIFICATION**

### **GasPump-1 Tests (1-5):**
    1. **Test #1:** Activate(4.1), Start(), PayCash(5.2), StartPump(), PumpLiter(), PumpLiter()
    - **Expected:** 2 liters pumped, Receipt total: $8.2
    2. **Test #2:** Activate(4.1), Start(), PayCash(5.2), Activate(7.2), StartPump(), PumpLiter(), PumpLiter()
    - **Expected:** 2 liters pumped, Receipt total: $8.2 (NOT $14.4 - robustness test)
    3. **Test #3:** Activate(4.1), Start(), PayCash(5.2), Cancel(), Start(), PayCredit(), Approved(), StartPump(), PumpLiter(), PumpLiter(), StopPump()
    - **Expected:** 2 liters pumped, Receipt total: $8.2
    4. **Test #4:** Activate(4.1), Start(), PayCredit(), Approved(), PayCash(5.2), StartPump(), PumpLiter(), PumpLiter(), StopPump()
    - **Expected:** 2 liters pumped, Receipt total: $8.2
    5. **Test #5:** Activate(4.1), Start(), PayCredit(), Reject(), Start(), PayCash(9), StartPump(), PumpLiter(), PumpLiter(), StopPump()
    - **Expected:** 2 liters pumped, Receipt total: $8.2

### **GasPump-2 Tests (6-10):**
    6. **Test #6:** Activate(4, 7), Start(), PayDebit(123), Pin(124), Pin(123), Regular(), StartPump(), PumpGallon(), FullTank()
    - **Expected:** Wrong PIN message, 1 gallon pumped, Receipt total: $4
    7. **Test #7:** Activate(4, 7), Start(), PayDebit(123), Pin(123), Regular(), StartPump(), Activate(7, 3), PumpGallon(), FullTank()
    - **Expected:** 1 gallon pumped, Receipt total: $4 (NOT $7 - robustness test)
    8. **Test #8:** Activate(4, 7), Start(), PayDebit(123), Pin(123), Regular(), StartPump(), Diesel(), PumpGallon(), FullTank()
    - **Expected:** 1 gallon pumped, Receipt total: $4 (NOT $7 - gas selected before pump)
    9. **Test #9:** Activate(4, 7), Start(), PayDebit(123), Pin(124), Pin(124), Pin(124), Start(), PayCredit(), Approved(), Diesel(), StartPump(), PumpGallon(), PumpGallon(), StopPump()
    - **Expected:** Wrong PIN messages, Too many attempts, 2 gallons pumped, Receipt total: $14
    10. **Test #10:** Activate(4, 7), Start(), PayDebit(123), Pin(123), Regular(), Diesel(), StartPump(), PumpGallon(), FullTank()
        - **Expected:** 1 gallon pumped, Receipt total: $7 (Diesel overrides Regular)

-----------------------------------------------------------------

## **HOW TO RUN TESTS**

    ### **Run ALL 10 Tests (Easiest):**
        ./run.sh
        # Then select: 3. Run ALL 10 Test Cases

    ### **Run Individual Test:**
        ./run.sh
        # Then select: 4. Run Individual Test Case (1-10)
        # Enter test number when prompted

    ### **Interactive Testing:**
        ./run.sh
        # Then select:
        # 1. Run GasPump-1 (Cash/Credit, Liters)
        # OR
        # 2. Run GasPump-2 (Credit/Debit, Gallons)
        # Follow on-screen menu options

-----------------------------------------------------------------

## **Project Structure**

```text
CS586_Project/
├── README.md                     # This file
├── run.sh                        # Execution script
└── src/                          # ALL 18 SOURCE FILES
    ├── Main.java                 # Driver program with menu system
    ├── AbstractFactory.java      # Abstract Factory Pattern interface
    ├── GP1Factory.java           # Factory for GasPump-1 components
    ├── GP2Factory.java           # Factory for GasPump-2 components
    ├── State.java                # State Pattern interface
    ├── S0.java                   # State 0: Initial/Activate
    ├── S1.java                   # State 1: Start
    ├── S2.java                   # State 2: Payment selection
    ├── S3.java                   # State 3: Credit approval
    ├── S4.java                   # State 4: Gas selection
    ├── S5.java                   # State 5: Pumping
    ├── S6.java                   # State 6: PIN verification
    ├── MDA_EFSM.java             # MDA-EFSM context
    ├── OP.java                   # Strategy Pattern interface
    ├── OP1.java                  # Strategy for GasPump-1 output
    ├── OP2.java                  # Strategy for GasPump-2 output
    ├── DataStore.java            # Abstract DataStore
    ├── DS1.java                  # DataStore for GasPump-1
    ├── DS2.java                  # DataStore for GasPump-2
    ├── GasPump_1.java            # Input Processor for GasPump-1
    └── GasPump_2.java            # Input Processor for GasPump-2
```

-----------------------------------------------------------------

## **DESIGN PATTERNS IMPLEMENTATION**

    ### **1. STATE PATTERN**
    **Purpose:** Manage MDA-EFSM state transitions  
    **Files:** `State.java`, `S0.java` through `S6.java`, `MDA_EFSM.java`  
    **Key Feature:** Each state handles events differently. Invalid operations show "Operation X called in state Y - Nothing happens"

    ### **2. STRATEGY PATTERN**
    **Purpose:** Different algorithms for GP1 and GP2 output operations  
    **Files:** `OP.java` (interface), `OP1.java` (GP1 strategy), `OP2.java` (GP2 strategy)  
    **Key Feature:** Swappable output processors with different implementations

    ### **3. ABSTRACT FACTORY PATTERN**
    **Purpose:** Create families of related objects  
    **Files:** `AbstractFactory.java`, `GP1Factory.java`, `GP2Factory.java`  
    **Key Feature:** Factories ensure compatible component sets for each gas pump