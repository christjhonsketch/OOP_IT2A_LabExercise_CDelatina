public class Appointment {
    private Patient patient;
    private String service;  // now just a String
    private String appointmentTime;

    public Appointment(Patient patient, String service, String time) {
        this.patient = patient;
        this.service = service;
        this.appointmentTime = time;
    }

    @Override
    public String toString() {
        return String.format("%s | Service: %s | Time: %s",
                patient, service, appointmentTime);
    }

    // Getters and Setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create Patients
        Patient p1 = new Patient("P001", "Alice", 25, "09171234567");
        Patient p2 = new Patient("P002", "Bob", -5, "123"); // invalid age + contact

        // Create Appointments
        Appointment a1 = new Appointment(p1, "Cleaning", "10:00 AM");
        Appointment a2 = new Appointment(p2, "Extraction", "2:00 PM");

        // Display Appointments
        System.out.println(a1);
        System.out.println(a2);
    }
}