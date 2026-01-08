public class Filling extends DentalService {
    public Filling() {
        super("Filling", 800.0);
    }
    

    public double calculateCost() {
        return getPrice();
    }

    
    public String getDescription() {
        return "Dental filling procedure";
    }
}