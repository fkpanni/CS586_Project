/***************************************************************
 * STATE PATTERN IMPLEMENTATION - FIXED VERSION
 * 
 * Now shows operation name but nothing happens when invalid
 * This matches the professor's demonstration
 ***************************************************************/
public abstract class State {
    protected MDA_EFSM mdaEFSM;
    protected OP op;
    protected String stateName;
    
    public State(String name) {
        this.stateName = name;
    }
    
    public void setMDA_EFSM(MDA_EFSM mdaEFSM) {
        this.mdaEFSM = mdaEFSM;
        this.op = mdaEFSM.getOP();
    }
    
    // Default implementations that just show operation was called
    public void Activate() { 
        System.out.println("Operation Activate() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void Start() { 
        System.out.println("Operation Start() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void PayCredit() { 
        System.out.println("Operation PayCredit() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void PayCash() { 
        System.out.println("Operation PayCash() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void PayDebit() { 
        System.out.println("Operation PayDebit() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void Reject() { 
        System.out.println("Operation Reject() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void Cancel() { 
        System.out.println("Operation Cancel() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void Approved() { 
        System.out.println("Operation Approved() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void StartPump() { 
        System.out.println("Operation StartPump() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void Pump() { 
        System.out.println("Operation Pump() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void StopPump() { 
        System.out.println("Operation StopPump() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void SelectGas(int g) { 
        System.out.println("Operation SelectGas(" + g + ") called in state " + stateName + " - Nothing happens"); 
    }
    
    public void CorrectPin() { 
        System.out.println("Operation CorrectPin() called in state " + stateName + " - Nothing happens"); 
    }
    
    public void IncorrectPin(int max) { 
        System.out.println("Operation IncorrectPin(" + max + ") called in state " + stateName + " - Nothing happens"); 
    }
}

// Concrete States - Fixed to show operation but do nothing when invalid
class S0 extends State {
    public S0() {
        super("S0");
    }
    
    @Override
    public void Activate() {
        System.out.println("Operation Activate() called in state S0");
        System.out.print("  ");
        op.StorePrices();
        mdaEFSM.changeState(new S1());
    }
}

class S1 extends State {
    public S1() {
        super("S1");
    }
    
    @Override
    public void Start() {
        System.out.println("Operation Start() called in state S1");
        System.out.print("  ");
        op.PayMsg();
        System.out.print("  ");
        op.InitializeData();
        System.out.print("  ");
        op.SetW(1);
        mdaEFSM.changeState(new S2());
    }
}

class S2 extends State {
    public S2() {
        super("S2");
    }
    
    @Override
    public void PayCash() {
        System.out.println("Operation PayCash() called in state S2");
        System.out.print("  ");
        op.StoreCash();
        System.out.print("  ");
        op.DisplayMenu();
        System.out.print("  ");
        op.SetW(1);
        mdaEFSM.changeState(new S4());
    }
    
    @Override
    public void PayCredit() {
        System.out.println("Operation PayCredit() called in state S2");
        System.out.print("  ");
        op.SetW(1);
        mdaEFSM.changeState(new S3());
    }
    
    @Override
    public void PayDebit() {
        System.out.println("Operation PayDebit() called in state S2");
        System.out.print("  ");
        op.EnterPinMsg();
        System.out.print("  ");
        op.StorePin();
        mdaEFSM.setK(0);
        mdaEFSM.changeState(new S6());
    }
    
    @Override
    public void Cancel() {
        System.out.println("Operation Cancel() called in state S2");
        System.out.print("  ");
        op.CancelMsg();
        System.out.print("  ");
        op.ReturnCash();
        mdaEFSM.changeState(new S0());
    }
}

class S3 extends State {
    public S3() {
        super("S3");
    }
    
    @Override
    public void Approved() {
        System.out.println("Operation Approved() called in state S3");
        System.out.print("  ");
        op.DisplayMenu();
        System.out.print("  ");
        op.EjectCard();
        mdaEFSM.changeState(new S4());
    }
    
    @Override
    public void Reject() {
        System.out.println("Operation Reject() called in state S3");
        System.out.print("  ");
        op.RejectMsg();
        System.out.print("  ");
        op.EjectCard();
        mdaEFSM.changeState(new S0());
    }
}

class S4 extends State {
    public S4() {
        super("S4");
    }
    
    @Override
    public void Cancel() {
        System.out.println("Operation Cancel() called in state S4");
        System.out.print("  ");
        op.CancelMsg();
        System.out.print("  ");
        op.ReturnCash();
        mdaEFSM.changeState(new S0());
    }
    
    @Override
    public void SelectGas(int g) {
        System.out.println("Operation SelectGas(" + g + ") called in state S4");
        System.out.print("  ");
        op.SetPrice(g);
    }
    
    @Override
    public void StartPump() {
        System.out.println("Operation StartPump() called in state S4");
        System.out.print("  ");
        op.SetInitialValues();
        mdaEFSM.changeState(new S5());
    }
}

class S5 extends State {
    public S5() {
        super("S5");
    }
    
    @Override
    public void Pump() {
        System.out.println("Operation Pump() called in state S5");
        System.out.print("  ");
        op.PumpGasUnit();
        System.out.print("  ");
        op.GasPumpedMsg();
    }
    
    @Override
    public void StopPump() {
        System.out.println("Operation StopPump() called in state S5");
        System.out.print("  ");
        op.PrintReceipt();
        mdaEFSM.changeState(new S0());
    }
}

class S6 extends State {
    public S6() {
        super("S6");
    }
    
    @Override
    public void CorrectPin() {
        System.out.println("Operation CorrectPin() called in state S6");
        System.out.print("  ");
        op.DisplayMenu();
        System.out.print("  ");
        op.EjectCard();
        mdaEFSM.changeState(new S4());
    }
    
    @Override
    public void IncorrectPin(int max) {
        System.out.println("Operation IncorrectPin(" + max + ") called in state S6");
        if (mdaEFSM.getK() >= max) {  // Changed from > to >= for correct counting
            System.out.print("  ");
            op.WrongPinMsg();
            System.out.print("  ");
            op.EjectCard();
            mdaEFSM.changeState(new S0());
        } else {
            System.out.print("  ");
            op.WrongPinMsg();
            mdaEFSM.setK(mdaEFSM.getK() + 1);
        }
    }
}