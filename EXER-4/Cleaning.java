public class Cleaning extends DentalService {
    public Cleaning() {
        super("Cleaning", 1000.0);
    }
    
    
    public double calculateCost() {
        return getPrice();
    }

    
    public String getDescription() {
        return "Professional teeth cleaning";
    }
}