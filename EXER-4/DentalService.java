public abstract class DentalService {
    private String serviceName;
    private double price;

    public DentalService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Abstract methods that subclasses 
    public abstract double calculateCost();
    public abstract String getDescription();

    
    public String toString() {
        return serviceName;
    }
}