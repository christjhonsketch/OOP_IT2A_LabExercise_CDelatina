public class ProjectTester {
    public static void main(String[] args) {
        // Create dental services
        DentalService cleaning = new Cleaning();
        DentalService extraction = new Extraction();
        DentalService filling = new Filling();

        // Create appointments
        Appointment a1 = new Appointment("P001", "Juan Dela Cruz", cleaning, "10:00 AM");
        Appointment a2 = new Appointment("P002", "Maria Clara", extraction, "11:30 AM");
        Appointment a3 = new Appointment("P003", "Jose Rizal", filling, "1:00 PM");

        // Print appointments
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
    }
}

