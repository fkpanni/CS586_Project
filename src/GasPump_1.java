public class GasPump_1 {
    private MDA_EFSM m;
    private DataStore d;
    private OP op;
    
    public GasPump_1(AbstractFactory factory) {
        this.m = factory.createMDA_EFSM();
        this.d = factory.createDataStore();
        this.op = factory.createOutputProcessor();
        
        this.op.setDataStore(d);
        this.m.setOP(op);
    }
    
    public void Activate(float a) {
        System.out.println("\n[GasPump-1] Activate(" + a + ") called");
        if (a > 0) {
            ((DS1) d).setTemp_a(a);
            m.Activate();
        } else {
            System.out.println("  Error: Price must be positive");
        }
    }
    
    public void Start() {
        System.out.println("\n[GasPump-1] Start() called");
        m.Start();
    }
    
    public void PayCash(float c) {
        System.out.println("\n[GasPump-1] PayCash(" + c + ") called");
        if (c > 0) {
            ((DS1) d).setTemp_c(c);
            m.PayCash();
        } else {
            System.out.println("  Error: Cash amount must be positive");
        }
    }
    
    public void PayCredit() {
        System.out.println("\n[GasPump-1] PayCredit() called");
        m.PayCredit();
    }
    
    public void Reject() {
        System.out.println("\n[GasPump-1] Reject() called");
        m.Reject();
    }
    
    public void Approved() {
        System.out.println("\n[GasPump-1] Approved() called");
        m.Approved();
    }
    
    public void Cancel() {
        System.out.println("\n[GasPump-1] Cancel() called");
        m.Cancel();
    }
    
    public void StartPump() {
        System.out.println("\n[GasPump-1] StartPump() called");
        m.StartPump();
    }
    
    public void PumpLiter() {
        System.out.println("\n[GasPump-1] PumpLiter() called");
        DS1 ds = (DS1) d;
        if (ds.getW() == 1) {
            m.Pump();
        } else if (ds.getCash() > 0 && ds.getCash() < ds.getPrice() * (ds.getL() + 1)) {
            System.out.println("  Not enough cash to pump another liter");
            m.StopPump();
        } else {
            m.Pump();
        }
    }
    
    public void StopPump() {
        System.out.println("\n[GasPump-1] StopPump() called");
        m.StopPump();
    }
















     public String getStatus() {
        DS1 ds = (DS1) d;
        StringBuilder status = new StringBuilder();
        
        status.append("╔══════════════════════════════════════════════════════╗\n");
        status.append("║                GAS PUMP-1 STATUS                     ║\n");
        status.append("╚══════════════════════════════════════════════════════╝\n");
        
        // Get current state name from MDA_EFSM
        status.append("Current State: ").append(getCurrentStateName()).append("\n");
        status.append("────────────────────────────────────────────────────────\n");
        
        if (ds != null) {
            status.append(String.format("Price per liter: $%.2f\n", ds.getPrice()));
            status.append(String.format("Cash deposited: $%.2f\n", ds.getCash()));
            status.append(String.format("Liters pumped: %d\n", ds.getL()));
            status.append(String.format("Total amount: $%.2f\n", ds.getTotal()));
            status.append(String.format("W flag: %d ", ds.getW()));
            status.append(ds.getW() == 1 ? "(credit/debit mode)" : "(cash mode)").append("\n");
            
            // Show available operations based on state
            status.append("\nAvailable Operations in current state:\n");
            String[] ops = getAvailableOperations();
            for (String op : ops) {
                status.append("  • ").append(op).append("\n");
            }
            
            // Show transaction summary
            status.append("\nTransaction Summary:\n");
            if (ds.getL() > 0) {
                status.append(String.format("  You have pumped %d liter(s) at $%.2f each\n", 
                                          ds.getL(), ds.getPrice()));
                status.append(String.format("  Current total: $%.2f\n", ds.getTotal()));
                
                if (ds.getW() == 0 && ds.getCash() > 0) { // Cash mode
                    float remaining = ds.getCash() - ds.getTotal();
                    if (remaining > 0) {
                        status.append(String.format("  Cash remaining: $%.2f\n", remaining));
                    } else if (remaining < 0) {
                        status.append(String.format("  Additional cash needed: $%.2f\n", -remaining));
                    }
                }
            }
        } else {
            status.append("DataStore not initialized\n");
        }
        
        return status.toString();
    }
    
    // Helper method to get current state name
    private String getCurrentStateName() {
        // This would require exposing state from MDA_EFSM
        // For now, we'll track it differently or add a method to MDA_EFSM
        return "State tracking needs MDA_EFSM method";
    }
    
    // Helper method to get available operations based on state
    private String[] getAvailableOperations() {
        // This is a simplified version - in reality, this should come from MDA_EFSM
        return new String[]{
            "Depends on current state",
            "Check state diagram for valid operations"
        };
    }
    
    // Simple status display for menu option
    public void displayStatus() {
        DS1 ds = (DS1) d;
        if (ds == null) {
            System.out.println("GasPump-1 not initialized");
            return;
        }
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("GAS PUMP-1 - CURRENT STATUS");
        System.out.println("═".repeat(50));
        
        System.out.printf("Price Configuration:\n");
        System.out.printf("  • Price per liter: $%.2f\n", ds.getPrice());
        
        System.out.printf("\nTransaction Details:\n");
        System.out.printf("  • Liters pumped: %d\n", ds.getL());
        System.out.printf("  • Current total: $%.2f\n", ds.getTotal());
        
        if (ds.getCash() > 0) {
            System.out.printf("  • Cash deposited: $%.2f\n", ds.getCash());
            float remaining = ds.getCash() - ds.getTotal();
            if (remaining > 0) {
                System.out.printf("  • Cash remaining: $%.2f\n", remaining);
            } else if (remaining < 0) {
                System.out.printf("  • Additional cash needed: $%.2f\n", -remaining);
            }
        }
        
        System.out.printf("\nSystem Flags:\n");
        System.out.printf("  • Payment mode: %s\n", 
                         ds.getW() == 1 ? "Credit/Debit" : "Cash");
        
        // Show what operations are likely valid
        System.out.println("\n" + "─".repeat(50));
        System.out.println("Based on typical state machine flow:");
        if (ds.getPrice() == 0) {
            System.out.println("→ Need to: Activate(price) then Start()");
        } else if (ds.getTotal() == 0 && ds.getL() == 0) {
            System.out.println("→ Need to: Select payment method (PayCash or PayCredit)");
        } else if (ds.getL() > 0) {
            System.out.println("→ Can: PumpLiter() or StopPump()");
        }
        System.out.println("═".repeat(50));
    }
}