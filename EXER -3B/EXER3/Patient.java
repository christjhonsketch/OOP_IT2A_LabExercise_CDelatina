// Patient.java
public class Patient {
    private String id;
    private String name;
    private int age;
    private String contactNumber;

    // Constructor
    public Patient(String id, String name, int age, String contactNumber) {
        this.id = id;
        this.name = name;
        setAge(age); // validation
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Setting default age 1.");
            this.age = 1;
        }
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        if (contactNumber != null && contactNumber.matches("\\d{10,11}")) {
            this.contactNumber = contactNumber;
        } else {
            System.out.println("Invalid contact number. Keeping old value.");
        }
    }

    @Override
    public String toString() {
        return String.format("Patient[ID: %s, Name: %s, Age: %d, Contact: %s]",
                id, name, age, contactNumber);
    }
}
