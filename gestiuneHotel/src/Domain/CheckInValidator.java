package Domain;

public class CheckInValidator {
    public void validate(CheckIn checkin) {
        if(checkin.getDaysNumber() <= 0)
            throw new RuntimeException("The number of days cannot be negative!");
        }
    }