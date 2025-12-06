import java.util.Scanner;

/**
 * Main driver program for Gas Pump System
 * Demonstrates the MDA architecture with three design patterns
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("===============================");
        System.out.println("   GAS PUMP SYSTEM (MDA)");
        System.out.println("===============================");
        
        // Abstract Factory Pattern Demo
        System.out.println("\n=== ABSTRACT FACTORY PATTERN ===");
        System.out.println("Select Gas Pump Type:");
        System.out.println("1. GasPump-1 (Pay by Cash/Credit, Liters)");
        System.out.println("2. GasPump-2 (Pay by Credit/Debit, Gallons)");
        System.out.print("Enter choice (1 or 2): ");
        
        int choice = sc.nextInt();
        
        if (choice == 1) {
            runGasPump1(sc);
        } else if (choice == 2) {
            runGasPump2(sc);
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
        
        sc.close();
    }
    
    private static void runGasPump1(Scanner sc) {
        // Abstract Factory creates GasPump-1 components
        AbstractFactory factory = new GP1Factory();
        GasPump_1 gp1 = new GasPump_1(factory);
        
        System.out.println("\n=== GasPump-1 MENU ===");
        System.out.println("0. Activate(float)");
        System.out.println("1. Start()");
        System.out.println("2. PayCredit()");
        System.out.println("3. Reject()");
        System.out.println("4. Cancel()");
        System.out.println("5. Approved()");
        System.out.println("6. PayCash(float)");
        System.out.println("7. StartPump()");
        System.out.println("8. PumpLiter()");
        System.out.println("9. StopPump()");
        System.out.println("q. Quit");
        
        char ch = ' ';
        while (ch != 'q') {
            System.out.print("\nSelect Operation (0-9, q): ");
            ch = sc.next().charAt(0);
            
            switch (ch) {
                case '0': {
                    System.out.print("Enter price per liter: ");
                    float a = sc.nextFloat();
                    gp1.Activate(a);
                    break;
                }
                case '1': gp1.Start(); break;
                case '2': gp1.PayCredit(); break;
                case '3': gp1.Reject(); break;
                case '4': gp1.Cancel(); break;
                case '5': gp1.Approved(); break;
                case '6': {
                    System.out.print("Enter cash amount: ");
                    float c = sc.nextFloat();
                    gp1.PayCash(c);
                    break;
                }
                case '7': gp1.StartPump(); break;
                case '8': gp1.PumpLiter(); break;
                case '9': gp1.StopPump(); break;
                case 'q': System.out.println("Exiting GasPump-1..."); break;
                default: System.out.println("Invalid option!");
            }
        }
    }
    
    private static void runGasPump2(Scanner sc) {
        // Abstract Factory creates GasPump-2 components
        AbstractFactory factory = new GP2Factory();
        GasPump_2 gp2 = new GasPump_2(factory);
        
        System.out.println("\n=== GasPump-2 MENU ===");
        System.out.println("0. Activate(int a, int b)");
        System.out.println("1. Start()");
        System.out.println("2. PayCredit()");
        System.out.println("3. Reject()");
        System.out.println("4. PayDebit(int p)");
        System.out.println("5. Pin(int x)");
        System.out.println("6. Cancel()");
        System.out.println("7. Approved()");
        System.out.println("8. Diesel()");
        System.out.println("9. Regular()");
        System.out.println("a. StartPump()");
        System.out.println("b. PumpGallon()");
        System.out.println("c. StopPump()");
        System.out.println("d. FullTank()");
        System.out.println("q. Quit");
        
        char ch = ' ';
        while (ch != 'q') {
            System.out.print("\nSelect Operation (0-d, q): ");
            ch = sc.next().charAt(0);
            
            switch (ch) {
                case '0': {
                    System.out.print("Enter Regular price: ");
                    int a = sc.nextInt();
                    System.out.print("Enter Diesel price: ");
                    int b = sc.nextInt();
                    gp2.Activate(a, b);
                    break;
                }
                case '1': gp2.Start(); break;
                case '2': gp2.PayCredit(); break;
                case '3': gp2.Reject(); break;
                case '4': {
                    System.out.print("Enter PIN: ");
                    int p = sc.nextInt();
                    gp2.PayDebit(p);
                    break;
                }
                case '5': {
                    System.out.print("Enter PIN to verify: ");
                    int x = sc.nextInt();
                    gp2.Pin(x);
                    break;
                }
                case '6': gp2.Cancel(); break;
                case '7': gp2.Approved(); break;
                case '8': gp2.Diesel(); break;
                case '9': gp2.Regular(); break;
                case 'a': gp2.StartPump(); break;
                case 'b': gp2.PumpGallon(); break;
                case 'c': gp2.StopPump(); break;
                case 'd': gp2.FullTank(); break;
                case 'q': System.out.println("Exiting GasPump-2..."); break;
                default: System.out.println("Invalid option!");
            }
        }
    }
}
