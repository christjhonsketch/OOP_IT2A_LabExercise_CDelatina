public class Extraction extends DentalService {
    public Extraction() {
        super("Tooth Extraction", 1500.00);
    }
    

    public double calculateCost() {
        return getPrice();
    }

    
    public String getDescription() {
        return "Tooth extraction procedure";
    }
}