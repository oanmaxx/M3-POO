package Domain;

import java.util.Objects;

public class CheckIn {
    private int id, roomNumber, daysNumber, personsNumber;
    private String feedback;
    private boolean isCheckedOut;
    private double rating;

    public CheckIn(int id, int roomNumber, int daysNumber, int personsNumber) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.daysNumber = daysNumber;
        this.personsNumber = personsNumber;
        isCheckedOut = false;
    }

    public CheckIn(int id, int roomNumber, int daysNumber, int personsNumber, String feedback, boolean isCheckedOut, double rating) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.daysNumber = daysNumber;
        this.personsNumber = personsNumber;
        this.feedback = feedback;
        this.isCheckedOut = isCheckedOut;
        this.rating = rating;
    }

   @Override
    public String toString() {
        return "CheckIn{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", daysNumber=" + daysNumber +
                ", personsNumber=" + personsNumber +
                ", feedback='" + feedback + '\'' +
                ", isCheckedOut=" + isCheckedOut +
                ", rating=" + rating +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(int daysNumber) {
        this.daysNumber = daysNumber;
    }

    public int getPersonsNumber() {
        return personsNumber;
    }

    public void setPersonsNumber(int personsNumber) {
        this.personsNumber = personsNumber;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

