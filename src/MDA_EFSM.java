public class MDA_EFSM {
    private State currentState;
    private OP op;
    private int k; // For PIN attempts
    
    public MDA_EFSM() {
        // Initial state
        currentState = new S0();
    }
    
    public void setOP(OP op) {
        this.op = op;
        currentState.setMDA_EFSM(this);
    }
    
    public OP getOP() {
        return op;
    }
    
    public void setK(int k) {
        this.k = k;
    }
    
    public int getK() {
        return k;
    }
    
    // State transition
    public void changeState(State newState) {
        System.out.println("    State changed from " + currentState.stateName + " to " + newState.stateName);
        this.currentState = newState;
        newState.setMDA_EFSM(this);
    }
    
    // Event handling methods
    public void Activate() { 
        System.out.println("\n[MDA-EFSM] Activate() event received");
        currentState.Activate(); 
    }
    
    public void Start() { 
        System.out.println("\n[MDA-EFSM] Start() event received");
        currentState.Start(); 
    }
    
    public void PayCredit() { 
        System.out.println("\n[MDA-EFSM] PayCredit() event received");
        currentState.PayCredit(); 
    }
    
    public void PayCash() { 
        System.out.println("\n[MDA-EFSM] PayCash() event received");
        currentState.PayCash(); 
    }
    
    public void PayDebit() { 
        System.out.println("\n[MDA-EFSM] PayDebit() event received");
        currentState.PayDebit(); 
    }
    
    public void Reject() { 
        System.out.println("\n[MDA-EFSM] Reject() event received");
        currentState.Reject(); 
    }
    
    public void Cancel() { 
        System.out.println("\n[MDA-EFSM] Cancel() event received");
        currentState.Cancel(); 
    }
    
    public void Approved() { 
        System.out.println("\n[MDA-EFSM] Approved() event received");
        currentState.Approved(); 
    }
    
    public void StartPump() { 
        System.out.println("\n[MDA-EFSM] StartPump() event received");
        currentState.StartPump(); 
    }
    
    public void Pump() { 
        System.out.println("\n[MDA-EFSM] Pump() event received");
        currentState.Pump(); 
    }
    
    public void StopPump() { 
        System.out.println("\n[MDA-EFSM] StopPump() event received");
        currentState.StopPump(); 
    }
    
    public void SelectGas(int g) { 
        System.out.println("\n[MDA-EFSM] SelectGas(" + g + ") event received");
        currentState.SelectGas(g); 
    }
    
    public void CorrectPin() { 
        System.out.println("\n[MDA-EFSM] CorrectPin() event received");
        currentState.CorrectPin(); 
    }
    
    public void IncorrectPin(int max) { 
        System.out.println("\n[MDA-EFSM] IncorrectPin(" + max + ") event received");
        currentState.IncorrectPin(max); 
    }
}