import java.util.Objects;

public class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        // TO DO:
        return super.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        // TO DO:
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Guest guest = (Guest) obj;
        return  this.lastName.equals(guest.lastName) &&
                this.firstName.equals(guest.firstName) &&
                this.email.equals(guest.email) &&
                this.phoneNumber.equals(guest.phoneNumber);
    }

    @Override
    public String toString() {
        // TO DO:
        return  " Nume: " + lastName + " " + firstName + ", Email: " + email + ", Telefon: " + phoneNumber;
    }

    public String fullName() {
        // TO DO:
        return lastName + " " + firstName ;
    }
}
