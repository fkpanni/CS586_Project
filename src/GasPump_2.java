public class GasPump_2 {
    private MDA_EFSM m;
    private DataStore d;
    private OP op;
    
    public GasPump_2(AbstractFactory factory) {
        this.m = factory.createMDA_EFSM();
        this.d = factory.createDataStore();
        this.op = factory.createOutputProcessor();
        
        this.op.setDataStore(d);
        this.m.setOP(op);
    }
    
    public void Activate(int a, int b) {
        System.out.println("\n[GasPump-2] Activate(" + a + ", " + b + ") called");
        if (a > 0 && b > 0) {
            ((DS2) d).setTemp_a(a);
            ((DS2) d).setTemp_b(b);
            m.Activate();
        } else {
            System.out.println("  Error: Prices must be positive");
        }
    }
    
    public void Start() {
        System.out.println("\n[GasPump-2] Start() called");
        m.Start();
    }
    
    public void PayCredit() {
        System.out.println("\n[GasPump-2] PayCredit() called");
        m.PayCredit();
    }
    
    public void Reject() {
        System.out.println("\n[GasPump-2] Reject() called");
        m.Reject();
    }
    
    public void PayDebit(int p) {
        System.out.println("\n[GasPump-2] PayDebit(" + p + ") called");
        ((DS2) d).setTemp_p(p);
        m.PayDebit();
    }
    
    public void Pin(int x) {
        System.out.println("\n[GasPump-2] Pin(" + x + ") called");
        DS2 ds = (DS2) d;
        if (ds.getPin() == x) {
            m.CorrectPin();
        } else {
            m.IncorrectPin(2);  // Changed from 1 to 2 (max 3 attempts = 2 wrong + 1 correct)
        }
    }
    
    public void Cancel() {
        System.out.println("\n[GasPump-2] Cancel() called");
        m.Cancel();
    }
    
    public void Approved() {
        System.out.println("\n[GasPump-2] Approved() called");
        m.Approved();
    }
    
    public void Diesel() {
        System.out.println("\n[GasPump-2] Diesel() called");
        m.SelectGas(2);
    }
    
    public void Regular() {
        System.out.println("\n[GasPump-2] Regular() called");
        m.SelectGas(1);
    }
    
    public void StartPump() {
        System.out.println("\n[GasPump-2] StartPump() called");
        DS2 ds = (DS2) d;
        if (ds.getPrice() > 0) {
            m.StartPump();
        } else {
            System.out.println("  Error: Please select gas type first");
        }
    }
    
    public void PumpGallon() {
        System.out.println("\n[GasPump-2] PumpGallon() called");
        m.Pump();
    }
    
    public void StopPump() {
        System.out.println("\n[GasPump-2] StopPump() called");
        m.StopPump();
    }
    
    public void FullTank() {
        System.out.println("\n[GasPump-2] FullTank() called");
        m.StopPump();
    }

    









    public void displayStatus() {
        DS2 ds = (DS2) d;
        if (ds == null) {
            System.out.println("GasPump-2 not initialized");
            return;
        }
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("GAS PUMP-2 - CURRENT STATUS");
        System.out.println("═".repeat(50));
        
        System.out.printf("Price Configuration:\n");
        System.out.printf("  • Regular price: $%d\n", ds.getRprice());
        System.out.printf("  • Diesel price: $%d\n", ds.getDprice());
        
        if (ds.getPrice() > 0) {
            System.out.printf("  • Selected gas: %s ($%d)\n", 
                            ds.getPrice() == ds.getRprice() ? "Regular" : "Diesel", 
                            ds.getPrice());
        } else {
            System.out.printf("  • Selected gas: Not yet selected\n");
        }
        
        System.out.printf("\nTransaction Details:\n");
        System.out.printf("  • Gallons pumped: %d\n", ds.getG());
        System.out.printf("  • Current total: $%d\n", ds.getTotal());
        
        if (ds.getPin() != 0) {
            System.out.printf("  • PIN entered: %d\n", ds.getPin());
        }
        
        System.out.printf("\nSystem Information:\n");
        System.out.printf("  • Temp PIN storage: %d\n", ds.getTemp_p());
        
        // Show what operations are likely valid
        System.out.println("\n" + "─".repeat(50));
        System.out.println("Based on typical state machine flow:");
        if (ds.getRprice() == 0 && ds.getDprice() == 0) {
            System.out.println("→ Need to: Activate(regPrice, dieselPrice)");
        } else if (ds.getPrice() == 0) {
            System.out.println("→ Need to: Select gas type (Regular or Diesel)");
        } else if (ds.getG() > 0) {
            System.out.println("→ Can: PumpGallon() or StopPump()/FullTank()");
        }
        System.out.println("═".repeat(50));
    }
}