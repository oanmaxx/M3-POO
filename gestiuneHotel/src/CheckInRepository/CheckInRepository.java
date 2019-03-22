package CheckInRepository;

import Domain.CheckIn;
import Domain.CheckInValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckInRepository {

    private Map<Integer, CheckIn> storage = new HashMap<>();

    private CheckInValidator validator;

    /**
     * Instantiates a repository for cars.
     * @param validator the validator used.
     */
    public CheckInRepository(CheckInValidator validator) {
        this.validator = validator;
        // low coupling, high cohesion
    }

    // metode CRUD: Create, Read, Update, Delete

    /**
     * ...
     * @param checkin
     */
    public void add(CheckIn checkin) {
        if (storage.containsKey(checkin.getId())) {
            throw new RuntimeException("A check-in with that id already exists!");
        }

        validator.validate(checkin);
        storage.put(checkin.getId(), checkin);
    }

    /**
     * ...
     * @param checkin
     */
    public void update(CheckIn checkin) {
        if (!storage.containsKey(checkin.getId())) {
            throw new RuntimeException("There is no checkin with the given id to update!");
        }

        validator.validate(checkin);
        storage.put(checkin.getId(), checkin);
    }

    /**
     * @return a list of all cars.
     */
    public List<CheckIn> getAll() {

        return new ArrayList<>(storage.values());
//        return (List<Car>)storage.values();
        //return storage.values().toArray();
    }
}
