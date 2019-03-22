import Domain.CheckInValidator;
import CheckInRepository.CheckInRepository;
import Service.CheckInService;
import UI.Console;

public class Main {

    public static void main(String[] args) {
        CheckInValidator validator = new CheckInValidator();
        CheckInRepository repository = new CheckInRepository(validator);
        CheckInService service = new CheckInService(repository);
        service.checkIn(1, 1, 8, 4);
        service.checkIn(2, 2, 7, 2);
        service.checkedOutService(1, "5", 5);
        service.checkIn(3, 6, 2, 2);
        service.checkedOutService(2, "4.5", 4.0);
        service.checkIn(4, 8, 6, 5);
        service.checkedOutService(6, "3", 4.8);
        service.checkIn(5, 10, 10, 2);
        service.checkedOutService(8, "5", 5.0);
        service.checkIn(6, 11, 8, 4);
        service.checkIn(9, 12, 5, 3);
        Console console = new Console(service);
        console.run();
    }
}
