public class Appointment {
    private String patientId;
    private String name;
    private DentalService service;
    private String appointmentTime;
    
    // Constructor
    public Appointment(String patientId, String name, DentalService service, String time) {
        this.patientId = patientId;
        this.name = name;
        this.service = service;
        this.appointmentTime = time;
    }
    
    // Method Overloading (Compile-time Polymorphism) 
    public void book() {
        System.out.println("Appointment booked for " + name);
    }
    
    public void book(String notes) {
        System.out.println("Appointment booked for " + name + " with notes: " + notes);
    }
    
    public void book(String notes, boolean sendConfirmation) {
        System.out.println("Appointment booked for " + name + " with notes: " + notes);
        if (sendConfirmation) {
            System.out.println("Confirmation sent to " + name);
        }
    }
    
    // Virtual method for overriding (Runtime Polymorphism) 
    public void displayDetails() {
        System.out.println("Appointment Details: " + name + " - " + service + " at " + appointmentTime);
    }
    
    // toString override 
   
    public String toString() {
        return String.format("ID: %s | Name: %s | Service: %s | Time: %s",
                patientId, name, service, appointmentTime);
    }
    
    // Getters and Setters 
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DentalService getService() {
        return service;
    }
    public void setService(DentalService service) {
        this.service = service;
    }
    public String getAppointmentTime() {
        return appointmentTime;
    }
    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}

// EmergencyAppointment extends  (Runtime Polymorphism) 
class EmergencyAppointment extends Appointment {
    private String urgency;
    
    public EmergencyAppointment(String patientId, String name, DentalService service, String time, String urgency) {
        super(patientId, name, service, time);
        this.urgency = urgency;
    }
    

    public void displayDetails() {
        System.out.println("EMERGENCY: " + getName() + " - " + getService() + " at " + getAppointmentTime() + " (Urgency: " + urgency + ")");
    }
    
    public String getUrgency() { return urgency; }
}

// RoutineAppointment extends (Runtime Polymorphism)  
class RoutineAppointment extends Appointment {
    private boolean isCheckup;
    
    public RoutineAppointment(String patientId, String name, DentalService service, String time, boolean isCheckup) {
        super(patientId, name, service, time);
        this.isCheckup = isCheckup;
    }
    
    
    public void displayDetails() {
        String type = isCheckup ? "ROUTINE CHECKUP" : "ROUTINE TREATMENT";
        System.out.println(type + ": " + getName() + " - " + getService() + " at " + getAppointmentTime());
    }
    
    public boolean isCheckup() { return isCheckup; }
}
