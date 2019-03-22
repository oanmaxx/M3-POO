package Service;

import Domain.CheckIn;
import Domain.RoomAverageRatingViewModel;
import CheckInRepository.CheckInRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckInService {
    private CheckInRepository repository;

    /**
     * @param repository
     */
    public CheckInService(CheckInRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id
     * @param roomNumber
     * @param daysNumber
     * @param personsNumber
     */

    public void checkIn(int id, int roomNumber, int daysNumber, int personsNumber) {

        CheckIn checkin = new CheckIn(id, roomNumber, daysNumber, personsNumber);
        List<CheckIn> checkins = repository.getAll();
        for (CheckIn c : checkins) {
            if (c.getRoomNumber() == roomNumber && !c.isCheckedOut()) {      //daca exista un checkin pentru camera ceruta si care este activ
                throw new RuntimeException("That room is already taken!");
            }
        }
        repository.add(checkin);
    }

    public void checkedOutService(int roomNumber, String feedback, double rating) {
        CheckIn occupiedRoom = null;
        List<CheckIn> checkins = repository.getAll();
        for (CheckIn c : checkins) {
            if (c.getRoomNumber() == roomNumber && !c.isCheckedOut()) {
                occupiedRoom = c;
            }
        }

        if (occupiedRoom != null) {
            occupiedRoom.setFeedback(feedback);
            occupiedRoom.setRating(rating);
            occupiedRoom.setCheckedOut(true);
            repository.update(occupiedRoom);
        } else {
            throw new RuntimeException("There is no free room!");
        }
    }

    public List<RoomAverageRatingViewModel> getRoomRatingAverages() {
        List<RoomAverageRatingViewModel> results = new ArrayList<>();
        Map<Integer, List<Double>> ratingsForRooms = new HashMap<>();

        for (CheckIn c : repository.getAll()) {
            if (c.isCheckedOut()) {
                int roomNumber = c.getRoomNumber();
                double rating = c.getRating();

                if (!ratingsForRooms.containsKey(roomNumber)) {
                    ratingsForRooms.put(roomNumber, new ArrayList<>());
                }
                ratingsForRooms.get(roomNumber).add(rating);
            }
        }

        for (int roomNumber : ratingsForRooms.keySet()) {
            List<Double> ratings = ratingsForRooms.get(roomNumber);
            double average = 0;
            for (double p : ratings) {
                average += p;
            }
            average /= ratings.size();
            results.add(new RoomAverageRatingViewModel(roomNumber, average));
        }

        results.sort((r1, r2) -> {
            if (r1.getAverageRating() > r2.getAverageRating())
                return -1;
            else if (r1.getAverageRating() == r2.getAverageRating())
                return 0;
            else
                return 1;
        });
        return results;
    }

    public List<CheckIn> getAll() {
        return repository.getAll();
    }
}
