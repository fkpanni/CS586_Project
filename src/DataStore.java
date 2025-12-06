/***************************************************************
 * DataStore classes for each Gas Pump type
 ***************************************************************/
public abstract class DataStore {
    // Common interface if needed
}

class DS1 extends DataStore {
    // GasPump-1 data store
    private float temp_a;
    private float temp_c;
    private float cash;
    private float total;
    private int L;
    private float price;
    private int w;
    
    // Getters and setters
    public float getTemp_a() { return temp_a; }
    public void setTemp_a(float temp_a) { this.temp_a = temp_a; }
    
    public float getTemp_c() { return temp_c; }
    public void setTemp_c(float temp_c) { this.temp_c = temp_c; }
    
    public float getCash() { return cash; }
    public void setCash(float cash) { this.cash = cash; }
    
    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }
    
    public int getL() { return L; }
    public void setL(int L) { this.L = L; }
    
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    
    public int getW() { return w; }
    public void setW(int w) { this.w = w; }
    
    //toString for easy display
    @Override
    public String toString() {
        return String.format("DS1 Status:\n" +
                           "  Price per liter: $%.2f\n" +
                           "  Cash deposited: $%.2f\n" +
                           "  Liters pumped: %d\n" +
                           "  Total amount: $%.2f\n" +
                           "  W flag: %d\n" +
                           "  Temp_a: $%.2f\n" +
                           "  Temp_c: $%.2f",
                           price, cash, L, total, w, temp_a, temp_c);
    }
}


class DS2 extends DataStore {
    // GasPump-2 data store
    private int temp_a;
    private int temp_b;
    private int temp_p;
    private int pin;
    private int price;
    private int G;
    private int total;
    private int Rprice;
    private int Dprice;
    
    // Getters and setters
    public int getTemp_a() { return temp_a; }
    public void setTemp_a(int temp_a) { this.temp_a = temp_a; }
    
    public int getTemp_b() { return temp_b; }
    public void setTemp_b(int temp_b) { this.temp_b = temp_b; }
    
    public int getTemp_p() { return temp_p; }
    public void setTemp_p(int temp_p) { this.temp_p = temp_p; }
    
    public int getPin() { return pin; }
    public void setPin(int pin) { this.pin = pin; }
    
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    
    public int getG() { return G; }
    public void setG(int G) { this.G = G; }
    
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    
    public int getRprice() { return Rprice; }
    public void setRprice(int Rprice) { this.Rprice = Rprice; }
    
    public int getDprice() { return Dprice; }
    public void setDprice(int Dprice) { this.Dprice = Dprice; }
    
    //toString for easy display
    @Override
    public String toString() {
        return String.format("DS2 Status:\n" +
                           "  Regular price: $%d\n" +
                           "  Diesel price: $%d\n" +
                           "  Selected price: $%d\n" +
                           "  Gallons pumped: %d\n" +
                           "  Total amount: $%d\n" +
                           "  PIN: %d\n" +
                           "  Temp_a (Rprice): $%d\n" +
                           "  Temp_b (Dprice): $%d\n" +
                           "  Temp_p (PIN temp): %d",
                           Rprice, Dprice, price, G, total, pin, temp_a, temp_b, temp_p);
    }
}