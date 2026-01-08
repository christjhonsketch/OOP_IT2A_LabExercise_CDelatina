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

    // toString override
    @Override
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
