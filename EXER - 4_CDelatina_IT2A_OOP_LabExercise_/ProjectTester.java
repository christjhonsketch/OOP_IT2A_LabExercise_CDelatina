public class ProjectTester {
    public static void main(String[] args) {
        System.out.println("=== POLYMORPHISM TESTING ===\n");
        
        // Create dental services
        DentalService cleaning = new Cleaning();
        DentalService extraction = new Extraction();
        DentalService filling = new Filling();
        
        // Test Method Overloading (Compile-time Polymorphism)
        System.out.println("1. METHOD OVERLOADING:");
        Appointment apt1 = new Appointment("001", "John", cleaning, "10:00 AM");
        apt1.book();
        apt1.book("Routine cleaning");
        apt1.book("First visit", true);
        
        System.out.println("\n2. RUNTIME POLYMORPHISM:");
        // Polymorphic array of appointments
        Appointment[] appointments = {
            new Appointment("001", "John", cleaning, "10:00 AM"),
            new EmergencyAppointment("002", "Jane", extraction, "2:00 PM", "High"),
            new RoutineAppointment("003", "Bob", filling, "11:00 AM", true)
        };
        
        // Same method call, different behavior
        for (Appointment apt : appointments) {
            apt.displayDetails(); // Polymorphic method call
        }
        
        System.out.println("\n3. INHERITANCE POLYMORPHISM:");
        for (Appointment apt : appointments) {
            System.out.println(apt.toString()); // Uses inherited toString
        }
        
        // Show service details
        System.out.println("\n4. SERVICE DETAILS:");
        System.out.println(cleaning.getDescription() + " - Cost: PHP " + cleaning.calculateCost());
        System.out.println(extraction.getDescription() + " - Cost: PHP " + extraction.calculateCost());
        System.out.println(filling.getDescription() + " - Cost: PHP " + filling.calculateCost());
    }
}