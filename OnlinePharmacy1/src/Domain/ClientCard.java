package Domain;

import java.util.Objects;

public class ClientCard {
    private int id;
    private String CNP, firstName, LastName, dateOfBirth, dateOfRegistration;

    public ClientCard(int id, String CNP, String firstName, String lastName, String dateOfBirth, String dateOfRegistration) {
        this.id = id;
        this.CNP = CNP;
        this.firstName = firstName;
        LastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "ClientCard{" +
                "id='" + id + '\'' +
                ", CNP='" + CNP + '\'' +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCard that = (ClientCard) o;           //Client client = (Client) o;
        return Objects.equals(id, that.id);         //return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CNP, firstName, LastName, dateOfBirth, dateOfRegistration);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
