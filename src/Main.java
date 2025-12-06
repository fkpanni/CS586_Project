import java.util.Scanner;
/**
 * Gas Pump System - Main Driver Program
 *  */
public class Main {
    private static final String VERSION = "2.0";
    private static final String PROJECT = "CS 586 - Gas Pump MDA System";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        displayBanner();
        
        boolean exit = false;
        
        while (!exit) {
            displayMainMenu();
            int choice = getIntInput(sc, "Enter choice: ");
            
            switch (choice) {
                case 1:
                    runGasPump1(sc);
                    break;
                case 2:
                    runGasPump2(sc);
                    break;
                case 3:
                    runAllTestCases();
                    break;
                case 4:
                    runIndividualTest(sc);
                    break;
                case 5:
                    showDesignPatterns();
                    break;
                case 6:
                    System.out.println("\nExiting Gas Pump System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
    
    private static void displayBanner() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║               GAS PUMP MDA SYSTEM v2.0               ║");
        System.out.println("║           CS 586 - Software Design                   ║");
        System.out.println("║           Fall 2025 - Final Project                  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println("Design Patterns: State, Strategy, Abstract Factory");
        System.out.println("========================================================\n");
    }
    
    private static void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("─────────");
        System.out.println("1. Run GasPump-1 (Cash/Credit, Liters)");
        System.out.println("2. Run GasPump-2 (Credit/Debit, Gallons)");
        System.out.println("3. Run ALL 10 Test Cases");
        System.out.println("4. Run Individual Test Case (1-10)");
        System.out.println("5. Show Design Patterns Implementation");
        System.out.println("6. Exit");
        System.out.println("─────────");
    }
    
    private static void runAllTestCases() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("RUNNING ALL 10 TEST CASES FROM PROJECT SPECIFICATION");
        System.out.println("═".repeat(70));
        
        System.out.println("\n" + "─".repeat(70));
        System.out.println("GAS PUMP #1 - TEST CASES 1-5");
        System.out.println("─".repeat(70));
        
        // Test Cases 1-5 for GasPump-1
        for (int i = 1; i <= 5; i++) {
            runGasPump1TestCase(i);
            System.out.println("\n" + ".".repeat(70) + "\n");
        }
        
        System.out.println("\n" + "─".repeat(70));
        System.out.println("GAS PUMP #2 - TEST CASES 6-10");
        System.out.println("─".repeat(70));
        
        // Test Cases 6-10 for GasPump-2
        for (int i = 6; i <= 10; i++) {
            runGasPump2TestCase(i);
            System.out.println("\n" + ".".repeat(70) + "\n");
        }
        
        System.out.println("✓ ALL 10 TEST CASES COMPLETED SUCCESSFULLY!");
        System.out.println("═".repeat(70));
    }
    
    private static void runIndividualTest(Scanner sc) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("INDIVIDUAL TEST CASE SELECTION");
        System.out.println("═".repeat(60));
        
        System.out.println("\nAvailable Test Cases:");
        System.out.println("1-5: GasPump-1 Tests");
        System.out.println("6-10: GasPump-2 Tests");
        
        int testNum = getIntInput(sc, "Enter test case number (1-10): ");
        
        if (testNum >= 1 && testNum <= 5) {
            runGasPump1TestCase(testNum);
        } else if (testNum >= 6 && testNum <= 10) {
            runGasPump2TestCase(testNum);
        } else {
            System.out.println("Invalid test case number. Please enter 1-10.");
        }
    }
    
    private static void runGasPump1TestCase(int testNum) {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("TEST CASE #" + testNum + ": GAS PUMP-1");
        System.out.println("─".repeat(60));
        
        AbstractFactory factory = new GP1Factory();
        GasPump_1 gp1 = new GasPump_1(factory);
        
        switch (testNum) {
            case 1:
                System.out.println("Test #1: Activate(4.1), Start(), PayCash(5.2), StartPump(), PumpLiter(), PumpLiter()");
                System.out.println("Expected: 2 liters pumped; Receipt total: $8.2");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4.1)");
                gp1.Activate(4.1f);
                
                System.out.println("\n[2] Start()");
                gp1.Start();
                
                System.out.println("\n[3] PayCash(5.2)");
                gp1.PayCash(5.2f);
                
                System.out.println("\n[4] StartPump()");
                gp1.StartPump();
                
                System.out.println("\n[5] PumpLiter() - First time");
                gp1.PumpLiter();
                
                System.out.println("\n[6] PumpLiter() - Second time");
                gp1.PumpLiter();
                
                System.out.println("\n[7] StopPump()");
                gp1.StopPump();
                break;
                
            case 2:
                System.out.println("Test #2: Activate(4.1), Start(), PayCash(5.2), Activate(7.2), StartPump(), PumpLiter(), PumpLiter()");
                System.out.println("Expected: 2 liters pumped; Receipt total: $8.2 (NOT $14.4)");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4.1)");
                gp1.Activate(4.1f);
                
                System.out.println("\n[2] Start()");
                gp1.Start();
                
                System.out.println("\n[3] PayCash(5.2)");
                gp1.PayCash(5.2f);
                
                System.out.println("\n[4] Activate(7.2) - Should do nothing (robustness test)");
                gp1.Activate(7.2f);
                
                System.out.println("\n[5] StartPump()");
                gp1.StartPump();
                
                System.out.println("\n[6] PumpLiter() - First time");
                gp1.PumpLiter();
                
                System.out.println("\n[7] PumpLiter() - Second time");
                gp1.PumpLiter();
                
                System.out.println("\n[8] StopPump()");
                gp1.StopPump();
                break;
                
            case 3:
                System.out.println("Test #3: Activate(4.1), Start(), PayCash(5.2), Cancel(), Start(), PayCredit(), Approved(), StartPump(), PumpLiter(), PumpLiter(), StopPump()");
                System.out.println("Expected: 2 liters pumped; Receipt total: $8.2");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4.1)");
                gp1.Activate(4.1f);
                
                System.out.println("\n[2] Start()");
                gp1.Start();
                
                System.out.println("\n[3] PayCash(5.2)");
                gp1.PayCash(5.2f);
                
                System.out.println("\n[4] Cancel() - Cancel cash payment");
                gp1.Cancel();
                
                System.out.println("\n[5] Start() - Start over");
                gp1.Start();
                
                System.out.println("\n[6] PayCredit()");
                gp1.PayCredit();
                
                System.out.println("\n[7] Approved()");
                gp1.Approved();
                
                System.out.println("\n[8] StartPump()");
                gp1.StartPump();
                
                System.out.println("\n[9] PumpLiter() - First time");
                gp1.PumpLiter();
                
                System.out.println("\n[10] PumpLiter() - Second time");
                gp1.PumpLiter();
                
                System.out.println("\n[11] StopPump()");
                gp1.StopPump();
                break;
                
            case 4:
                System.out.println("Test #4: Activate(4.1), Start(), PayCredit(), Approved(), PayCash(5.2), StartPump(), PumpLiter(), PumpLiter(), StopPump()");
                System.out.println("Expected: 2 liters pumped; Receipt total: $8.2");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4.1)");
                gp1.Activate(4.1f);
                
                System.out.println("\n[2] Start()");
                gp1.Start();
                
                System.out.println("\n[3] PayCredit()");
                gp1.PayCredit();
                
                System.out.println("\n[4] Approved()");
                gp1.Approved();
                
                System.out.println("\n[5] PayCash(5.2) - Should do nothing (already in S4)");
                gp1.PayCash(5.2f);
                
                System.out.println("\n[6] StartPump()");
                gp1.StartPump();
                
                System.out.println("\n[7] PumpLiter() - First time");
                gp1.PumpLiter();
                
                System.out.println("\n[8] PumpLiter() - Second time");
                gp1.PumpLiter();
                
                System.out.println("\n[9] StopPump()");
                gp1.StopPump();
                break;
                
            case 5:
                System.out.println("Test #5: Activate(4.1), Start(), PayCredit(), Reject(), Start(), PayCash(9), StartPump(), PumpLiter(), PumpLiter(), StopPump()");
                System.out.println("Expected: 2 liters pumped; Receipt total: $8.2");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4.1)");
                gp1.Activate(4.1f);
                
                System.out.println("\n[2] Start()");
                gp1.Start();
                
                System.out.println("\n[3] PayCredit()");
                gp1.PayCredit();
                
                System.out.println("\n[4] Reject() - Credit card rejected");
                gp1.Reject();
                
                System.out.println("\n[5] Start() - Start over");
                gp1.Start();
                
                System.out.println("\n[6] PayCash(9)");
                gp1.PayCash(9.0f);
                
                System.out.println("\n[7] StartPump()");
                gp1.StartPump();
                
                System.out.println("\n[8] PumpLiter() - First time");
                gp1.PumpLiter();
                
                System.out.println("\n[9] PumpLiter() - Second time");
                gp1.PumpLiter();
                
                System.out.println("\n[10] StopPump()");
                gp1.StopPump();
                break;
        }
        
        System.out.println("\n✓ Test Case #" + testNum + " completed!");
    }
    
    private static void runGasPump2TestCase(int testNum) {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("TEST CASE #" + testNum + ": GAS PUMP-2");
        System.out.println("─".repeat(60));
        
        AbstractFactory factory = new GP2Factory();
        GasPump_2 gp2 = new GasPump_2(factory);
        
        switch (testNum) {
            case 6:
                System.out.println("Test #6: Activate(4, 7), Start(), PayDebit(123), Pin(124), Pin(123), Regular(), StartPump(), PumpGallon(), FullTank()");
                System.out.println("Expected: wrong pin msg, 1 gallon pumped; Receipt total: $4");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4, 7)");
                gp2.Activate(4, 7);
                
                System.out.println("\n[2] Start()");
                gp2.Start();
                
                System.out.println("\n[3] PayDebit(123)");
                gp2.PayDebit(123);
                
                System.out.println("\n[4] Pin(124) - Wrong PIN");
                gp2.Pin(124);
                
                System.out.println("\n[5] Pin(123) - Correct PIN");
                gp2.Pin(123);
                
                System.out.println("\n[6] Regular()");
                gp2.Regular();
                
                System.out.println("\n[7] StartPump()");
                gp2.StartPump();
                
                System.out.println("\n[8] PumpGallon()");
                gp2.PumpGallon();
                
                System.out.println("\n[9] FullTank()");
                gp2.FullTank();
                break;
                
            case 7:
                System.out.println("Test #7: Activate(4, 7), Start(), PayDebit(123), Pin(123), Regular(), StartPump(), Activate(7, 3), PumpGallon(), FullTank()");
                System.out.println("Expected: 1 gallon pumped; Receipt total: $4 (NOT $7)");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4, 7)");
                gp2.Activate(4, 7);
                
                System.out.println("\n[2] Start()");
                gp2.Start();
                
                System.out.println("\n[3] PayDebit(123)");
                gp2.PayDebit(123);
                
                System.out.println("\n[4] Pin(123) - Correct PIN");
                gp2.Pin(123);
                
                System.out.println("\n[5] Regular()");
                gp2.Regular();
                
                System.out.println("\n[6] StartPump()");
                gp2.StartPump();
                
                System.out.println("\n[7] Activate(7, 3) - Should do nothing (robustness test)");
                gp2.Activate(7, 3);
                
                System.out.println("\n[8] PumpGallon()");
                gp2.PumpGallon();
                
                System.out.println("\n[9] FullTank()");
                gp2.FullTank();
                break;
                
            case 8:
                System.out.println("Test #8: Activate(4, 7), Start(), PayDebit(123), Pin(123), Regular(), StartPump(), Diesel(), PumpGallon(), FullTank()");
                System.out.println("Expected: 1 gallon pumped; Receipt total: $4 (NOT $7 - Regular selected before pump)");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4, 7)");
                gp2.Activate(4, 7);
                
                System.out.println("\n[2] Start()");
                gp2.Start();
                
                System.out.println("\n[3] PayDebit(123)");
                gp2.PayDebit(123);
                
                System.out.println("\n[4] Pin(123) - Correct PIN");
                gp2.Pin(123);
                
                System.out.println("\n[5] Regular()");
                gp2.Regular();
                
                System.out.println("\n[6] StartPump()");
                gp2.StartPump();
                
                System.out.println("\n[7] Diesel() - Should do nothing (already pumping)");
                gp2.Diesel();
                
                System.out.println("\n[8] PumpGallon()");
                gp2.PumpGallon();
                
                System.out.println("\n[9] FullTank()");
                gp2.FullTank();
                break;
                
            case 9:
                System.out.println("Test #9: Activate(4, 7), Start(), PayDebit(123), Pin(124), Pin(124), Pin(124), Start(), PayCredit(), Approved(), Diesel(), StartPump(), PumpGallon(), PumpGallon(), StopPump()");
                System.out.println("Expected: Wrong pin msg, Too many attempts msg; 2 gallons pumped; Receipt total: $14");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4, 7)");
                gp2.Activate(4, 7);
                
                System.out.println("\n[2] Start()");
                gp2.Start();
                
                System.out.println("\n[3] PayDebit(123)");
                gp2.PayDebit(123);
                
                System.out.println("\n[4] Pin(124) - Wrong PIN (attempt 1)");
                gp2.Pin(124);
                
                System.out.println("\n[5] Pin(124) - Wrong PIN (attempt 2)");
                gp2.Pin(124);
                
                System.out.println("\n[6] Pin(124) - Wrong PIN (attempt 3) - Should eject card");
                gp2.Pin(124);
                
                System.out.println("\n[7] Start() - Start over");
                gp2.Start();
                
                System.out.println("\n[8] PayCredit()");
                gp2.PayCredit();
                
                System.out.println("\n[9] Approved()");
                gp2.Approved();
                
                System.out.println("\n[10] Diesel()");
                gp2.Diesel();
                
                System.out.println("\n[11] StartPump()");
                gp2.StartPump();
                
                System.out.println("\n[12] PumpGallon() - First time");
                gp2.PumpGallon();
                
                System.out.println("\n[13] PumpGallon() - Second time");
                gp2.PumpGallon();
                
                System.out.println("\n[14] StopPump()");
                gp2.StopPump();
                break;
                
            case 10:
                System.out.println("Test #10: Activate(4, 7), Start(), PayDebit(123), Pin(123), Regular(), Diesel(), StartPump(), PumpGallon(), FullTank()");
                System.out.println("Expected: 1 gallon pumped; Receipt total: $7 (Diesel overrides Regular)");
                System.out.println("─".repeat(60));
                
                System.out.println("\n[1] Activate(4, 7)");
                gp2.Activate(4, 7);
                
                System.out.println("\n[2] Start()");
                gp2.Start();
                
                System.out.println("\n[3] PayDebit(123)");
                gp2.PayDebit(123);
                
                System.out.println("\n[4] Pin(123) - Correct PIN");
                gp2.Pin(123);
                
                System.out.println("\n[5] Regular()");
                gp2.Regular();
                
                System.out.println("\n[6] Diesel() - Overrides Regular selection");
                gp2.Diesel();
                
                System.out.println("\n[7] StartPump()");
                gp2.StartPump();
                
                System.out.println("\n[8] PumpGallon()");
                gp2.PumpGallon();
                
                System.out.println("\n[9] FullTank()");
                gp2.FullTank();
                break;
        }
        
        System.out.println("\n✓ Test Case #" + testNum + " completed!");
    }
    
    // Rest of the methods (runGasPump1, runGasPump2, showDesignPatterns, etc.)
    // ... [Keep all the existing interactive methods from previous code]
    
    private static void runGasPump1(Scanner sc) {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("GAS PUMP-1: PAY BY CASH/CREDIT (LITERS)");
        System.out.println("═".repeat(50));
        
        AbstractFactory factory = new GP1Factory();
        GasPump_1 gp1 = new GasPump_1(factory);
        
        boolean back = false;
        while (!back) {
            System.out.println("\nGasPump-1 Operations:");
            System.out.println("─────────────────────");
            System.out.println(" 0. Activate(float price)");
            System.out.println(" 1. Start()");
            System.out.println(" 2. PayCredit()");
            System.out.println(" 3. PayCash(float amount)");
            System.out.println(" 4. Reject()");
            System.out.println(" 5. Approved()");
            System.out.println(" 6. Cancel()");
            System.out.println(" 7. StartPump()");
            System.out.println(" 8. PumpLiter()");
            System.out.println(" 9. StopPump()");
            System.out.println("10. Show Status");
            System.out.println("11. Back to Main Menu");
            System.out.println("─────────────────────");
            
            int choice = getIntInput(sc, "Select operation (0-11): ");
            
            switch (choice) {
                case 0:
                    float price = getFloatInput(sc, "Enter price per liter: ");
                    gp1.Activate(price);
                    break;
                case 1:
                    gp1.Start();
                    break;
                case 2:
                    gp1.PayCredit();
                    break;
                case 3:
                    float cash = getFloatInput(sc, "Enter cash amount: ");
                    gp1.PayCash(cash);
                    break;
                case 4:
                    gp1.Reject();
                    break;
                case 5:
                    gp1.Approved();
                    break;
                case 6:
                    gp1.Cancel();
                    break;
                case 7:
                    gp1.StartPump();
                    break;
                case 8:
                    gp1.PumpLiter();
                    break;
                case 9:
                    gp1.StopPump();
                    break;
                case 10:
                    showGP1Status(gp1);
                    break;
                case 11:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid operation!");
            }
        }
    }
    
    private static void runGasPump2(Scanner sc) {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("GAS PUMP-2: PAY BY CREDIT/DEBIT (GALLONS)");
        System.out.println("═".repeat(50));
        
        AbstractFactory factory = new GP2Factory();
        GasPump_2 gp2 = new GasPump_2(factory);
        
        boolean back = false;
        while (!back) {
            System.out.println("\nGasPump-2 Operations:");
            System.out.println("─────────────────────");
            System.out.println(" 0. Activate(int regPrice, int dieselPrice)");
            System.out.println(" 1. Start()");
            System.out.println(" 2. PayCredit()");
            System.out.println(" 3. PayDebit(int pin)");
            System.out.println(" 4. Pin(int enteredPin)");
            System.out.println(" 5. Reject()");
            System.out.println(" 6. Approved()");
            System.out.println(" 7. Cancel()");
            System.out.println(" 8. Regular()");
            System.out.println(" 9. Diesel()");
            System.out.println("10. StartPump()");
            System.out.println("11. PumpGallon()");
            System.out.println("12. StopPump()");
            System.out.println("13. FullTank()");
            System.out.println("14. Show Status");
            System.out.println("15. Back to Main Menu");
            System.out.println("─────────────────────");
            
            int choice = getIntInput(sc, "Select operation (0-15): ");
            
            switch (choice) {
                case 0:
                    int reg = getIntInput(sc, "Enter Regular price: ");
                    int diesel = getIntInput(sc, "Enter Diesel price: ");
                    gp2.Activate(reg, diesel);
                    break;
                case 1:
                    gp2.Start();
                    break;
                case 2:
                    gp2.PayCredit();
                    break;
                case 3:
                    int pin = getIntInput(sc, "Enter PIN: ");
                    gp2.PayDebit(pin);
                    break;
                case 4:
                    int enteredPin = getIntInput(sc, "Verify PIN: ");
                    gp2.Pin(enteredPin);
                    break;
                case 5:
                    gp2.Reject();
                    break;
                case 6:
                    gp2.Approved();
                    break;
                case 7:
                    gp2.Cancel();
                    break;
                case 8:
                    gp2.Regular();
                    break;
                case 9:
                    gp2.Diesel();
                    break;
                case 10:
                    gp2.StartPump();
                    break;
                case 11:
                    gp2.PumpGallon();
                    break;
                case 12:
                    gp2.StopPump();
                    break;
                case 13:
                    gp2.FullTank();
                    break;
                case 14:
                    showGP2Status(gp2);
                    break;
                case 15:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid operation!");
            }
        }
    }
    
    private static void showDesignPatterns() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("DESIGN PATTERNS IMPLEMENTATION");
        System.out.println("═".repeat(50));
        
        System.out.println("\n1. STATE PATTERN");
        System.out.println("   ├── Purpose: Manage MDA-EFSM states");
        System.out.println("   ├── Files: State.java, S0-S6.java");
        System.out.println("   └── Implementation: Each state handles events differently");
        
        System.out.println("\n2. STRATEGY PATTERN");
        System.out.println("   ├── Purpose: Different algorithms for GP1 and GP2");
        System.out.println("   ├── Files: OP.java, OP1.java, OP2.java");
        System.out.println("   └── Implementation: Swappable output processors");
        
        System.out.println("\n3. ABSTRACT FACTORY PATTERN");
        System.out.println("   ├── Purpose: Create compatible component families");
        System.out.println("   ├── Files: AbstractFactory.java, GP1Factory.java, GP2Factory.java");
        System.out.println("   └── Implementation: Factories produce related objects");
        
        System.out.println("\n" + "─".repeat(50));
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Continue
        }
    }
    
    private static void showGP1Status(GasPump_1 gp1) {
        gp1.displayStatus();
    }
    
    private static void showGP2Status(GasPump_2 gp2) {
        gp2.displayStatus();
    }
    
    private static int getIntInput(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }
    
    private static float getFloatInput(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextFloat()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        return sc.nextFloat();
    }
}