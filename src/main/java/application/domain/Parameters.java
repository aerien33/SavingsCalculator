
package application.domain;

public class Parameters {
    
    private double amount;
    private double rate;
    
    public Parameters() {
        this(0, 0);
    }
    
    public Parameters(double amount, double rate) {
        this.amount = amount;
        this.rate = rate;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public double getRate() {
        return this.rate;
    } 
    
}
