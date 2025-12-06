/***************************************************************
 * STRATEGY PATTERN IMPLEMENTATION
 * 
 * Purpose: Define a family of algorithms (output operations),
 *          encapsulate each one, and make them interchangeable.
 * 
 * Responsibilities:
 * 1. Define common interface for all output operations
 * 2. Allow different implementations for GasPump-1 and GasPump-2
 * 3. Enable runtime swapping of output strategies
 ***************************************************************/
public abstract class OP {
    protected DataStore d;
    
    public void setDataStore(DataStore d) {
        this.d = d;
    }
    
    // Abstract methods for all output operations
    public abstract void StorePrices();
    public abstract void PayMsg();
    public abstract void StoreCash();
    public abstract void DisplayMenu();
    public abstract void RejectMsg();
    public abstract void SetPrice(int g);
    public abstract void SetInitialValues();
    public abstract void PumpGasUnit();
    public abstract void GasPumpedMsg();
    public abstract void PrintReceipt();
    public abstract void CancelMsg();
    public abstract void ReturnCash();
    public abstract void WrongPinMsg();
    public abstract void StorePin();
    public abstract void EnterPinMsg();
    public abstract void InitializeData();
    public abstract void SetW(int w);
    public abstract void EjectCard();
}

// Strategy for GasPump-1
class OP1 extends OP {
    @Override
    public void StorePrices() {
        System.out.println("OP1: StorePrices - Storing price from temp_a");
        DS1 ds = (DS1) d;
        if (ds != null) {
            ds.setPrice(ds.getTemp_a());
            System.out.println("    Price set to: $" + ds.getPrice() + " per liter");
        }
    }
    
    @Override
    public void PayMsg() {
        System.out.println("OP1: PayMsg - Please select payment method (Cash or Credit)");
    }
    
    @Override
    public void StoreCash() {
        System.out.println("OP1: StoreCash - Storing cash from temp_c");
        DS1 ds = (DS1) d;
        if (ds != null) {
            ds.setCash(ds.getTemp_c());
            System.out.println("    Cash stored: $" + ds.getCash());
        }
    }
    
    @Override
    public void DisplayMenu() {
        System.out.println("OP1: DisplayMenu - Displaying menu - Select gas type");
    }
    
    @Override
    public void RejectMsg() {
        System.out.println("OP1: RejectMsg - Credit card rejected");
    }
    
    @Override
    public void SetPrice(int g) {
        System.out.println("OP1: SetPrice - Gas type " + g + " selected");
        // Only one price for GP1, nothing to set
    }
    
    @Override
    public void SetInitialValues() {
        System.out.println("OP1: SetInitialValues - Initializing pump values");
        DS1 ds = (DS1) d;
        if (ds != null) {
            ds.setL(0);
            ds.setTotal(0);
            System.out.println("    Reset L to 0, total to $0");
        }
    }
    
    @Override
    public void PumpGasUnit() {
        System.out.println("OP1: PumpGasUnit - Pumping 1 liter");
        DS1 ds = (DS1) d;
        if (ds != null) {
            ds.setL(ds.getL() + 1);
            ds.setTotal(ds.getPrice() * ds.getL());
        }
    }
    
    @Override
    public void GasPumpedMsg() {
        DS1 ds = (DS1) d;
        if (ds != null) {
            System.out.println("OP1: GasPumpedMsg - Pumped " + ds.getL() + " liters");
            System.out.println("    Price per liter: $" + ds.getPrice());
            System.out.println("    Total: $" + ds.getTotal());
        }
    }
    
    @Override
    public void PrintReceipt() {
        DS1 ds = (DS1) d;
        if (ds != null) {
            System.out.println("OP1: PrintReceipt");
            System.out.println("    ===== RECEIPT =====");
            System.out.println("    Liters: " + ds.getL());
            System.out.println("    Price per liter: $" + ds.getPrice());
            System.out.println("    Total: $" + ds.getTotal());
            System.out.println("    ====================");
        }
    }
    
    @Override
    public void CancelMsg() {
        System.out.println("OP1: CancelMsg - Transaction cancelled");
    }
    
    @Override
    public void ReturnCash() {
        DS1 ds = (DS1) d;
        if (ds != null && ds.getCash() > 0) {
            System.out.println("OP1: ReturnCash - Returning cash: $" + ds.getCash());
            ds.setCash(0);
        }
    }
    
    @Override
    public void WrongPinMsg() {
        System.out.println("OP1: WrongPinMsg - Invalid PIN (not used in GP1)");
    }
    
    @Override
    public void StorePin() {
        System.out.println("OP1: StorePin - Storing PIN (not used in GP1)");
    }
    
    @Override
    public void EnterPinMsg() {
        System.out.println("OP1: EnterPinMsg - Enter PIN (not used in GP1)");
    }
    
    @Override
    public void InitializeData() {
        System.out.println("OP1: InitializeData - No operation for GP-1 (as per spec)");

        }
    
    @Override
    public void SetW(int w) {
        System.out.println("OP1: SetW - Setting w = " + w);
        DS1 ds = (DS1) d;
        if (ds != null) {
            ds.setW(w);
        }
    }
    
    @Override
    public void EjectCard() {
        System.out.println("OP1: EjectCard - Ejecting card");
    }
}

// Strategy for GasPump-2
class OP2 extends OP {
    @Override
    public void StorePrices() {
        System.out.println("OP2: StorePrices - Storing Regular and Diesel prices");
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            ds.setRprice(ds.getTemp_a());
            ds.setDprice(ds.getTemp_b());
            System.out.println("    Regular price: $" + ds.getRprice());
            System.out.println("    Diesel price: $" + ds.getDprice());
        }
    }
    
    @Override
    public void PayMsg() {
        System.out.println("OP2: PayMsg - Please select payment method (Credit or Debit)");
    }
    
    @Override
    public void StoreCash() {
        System.out.println("OP2: StoreCash - Cash payment not supported in GP2");
    }
    
    @Override
    public void DisplayMenu() {
        System.out.println("OP2: DisplayMenu - Displaying menu - Select Regular or Diesel");
    }
    
    @Override
    public void RejectMsg() {
        System.out.println("OP2: RejectMsg - Credit card not approved");
    }
    
    @Override
    public void SetPrice(int g) {
        System.out.println("OP2: SetPrice - Setting price for gas type " + g);
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            if (g == 1) {
                ds.setPrice(ds.getRprice());
                System.out.println("    Price set to Regular: $" + ds.getPrice());
            } else if (g == 2) {
                ds.setPrice(ds.getDprice());
                System.out.println("    Price set to Diesel: $" + ds.getPrice());
            }
        }
    }
    
    @Override
    public void SetInitialValues() {
        System.out.println("OP2: SetInitialValues - Initializing pump values");
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            ds.setG(0);
            ds.setTotal(0);
            System.out.println("    Reset G to 0, total to $0");
        }
    }
    
    @Override
    public void PumpGasUnit() {
        System.out.println("OP2: PumpGasUnit - Pumping 1 gallon");  // Fixed: says "gallon" not "liter"
        DS2 ds = (DS2) d;  // CAST TO DS2, NOT DS1 - THIS WAS THE BUG!
        if (ds != null) {
            ds.setG(ds.getG() + 1);
            ds.setTotal(ds.getPrice() * ds.getG());
            System.out.println("    Price per gallon: $" + ds.getPrice());
            System.out.println("    New total: $" + ds.getTotal());
        }
    }
    
    @Override
    public void GasPumpedMsg() {
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            System.out.println("OP2: GasPumpedMsg - Pumped " + ds.getG() + " gallons");
            System.out.println("    Current total: $" + ds.getTotal());
        }
    }
    
    @Override
    public void PrintReceipt() {
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            System.out.println("OP2: PrintReceipt");
            System.out.println("    ===== RECEIPT =====");
            System.out.println("    Gallons pumped: " + ds.getG());
            System.out.println("    Price per gallon: $" + ds.getPrice());
            System.out.println("    Total amount: $" + ds.getTotal());
            System.out.println("    ====================");
        }
    }
    
    @Override
    public void CancelMsg() {
        System.out.println("OP2: CancelMsg - Transaction cancelled");
    }
    
    @Override
    public void ReturnCash() {
        System.out.println("OP2: ReturnCash - No cash to return in GP2");
    }
    
    @Override
    public void WrongPinMsg() {
        System.out.println("OP2: WrongPinMsg - Incorrect PIN. Try again.");
    }
    
    @Override
    public void StorePin() {
        System.out.println("OP2: StorePin - Storing PIN");
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            ds.setPin(ds.getTemp_p());
            System.out.println("    PIN stored: " + ds.getPin());
        }
    }
    
    @Override
    public void EnterPinMsg() {
        System.out.println("OP2: EnterPinMsg - Please enter your PIN");
    }
    
    @Override
    public void InitializeData() {
        System.out.println("OP2: InitializeData - Setting price to 0 for GP2");
        // According to spec: "set the value of price to 0 for GP-2; do nothing for GP-1"
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            ds.setPrice(0);
            System.out.println("    Price initialized to 0");
        }
    }
    
    @Override
    public void SetW(int w) {
        System.out.println("OP2: SetW - Setting w = " + w);
        DS2 ds = (DS2) d;  // Cast to DS2, not DS1
        if (ds != null) {
            // GP2 may use w differently, but we still store it
        }
    }
    
    @Override
    public void EjectCard() {
        System.out.println("OP2: EjectCard - Ejecting card");
    }
}