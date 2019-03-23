package Tests;

import Domain.Medicament;
import Domain.MedicamentValidator;
import Repository.MedicamentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MedicamentRepositoryTests {

    @org.junit.jupiter.api.Test
    void findByIdWithExistingIdShouldReturnCorrectMedicament() {

        MedicamentRepository repository = new MedicamentRepository(new MedicamentValidator());
        Medicament added = new Medicament(1, 35.40, "placebo", "motherNature", true);
        repository.upsert(added);
        Medicament found = repository.findById(1);
        assertNotNull(null, "Returned null for existing id!");
        assertEquals(found.getId(), 1, String.format("Returned id %s instead of correct id=1!", found.getId()));
        assertEquals(found.getPrice(), 35.40, String.format("Returned price %s instead of %s", found.getPrice(), added.getPrice()));
        assertEquals(found.getName(), "placebo", String.format("Returned name=%s instead of %s", found.getName(), added.getName()));
        assertEquals(found.getProducer(), "motherNature", String.format("Returned producer %s instead of %s", found.getProducer(), added.getProducer()));
        assertTrue(true, "Returned true for existing id");  //??? medicament/ id
    }

    @org.junit.jupiter.api.Test
    void upsert() {
        MedicamentRepository repository = new MedicamentRepository(new MedicamentValidator());
        Medicament added1 = new Medicament(2, 12.75, "newPlacebo", "motherNatureTheOneAndOnly", false);
        repository.upsert(added1);

        assertTrue(repository.getAll().size() > 0);

        repository.remove(1);

        assertTrue(repository.getAll().size() == 0, "The item was not removed");
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //test to get all the items
        MedicamentRepository repository = new MedicamentRepository(new MedicamentValidator());
        Medicament added = new Medicament(1, 35.40, "placebo", "motherNature", true);
        Medicament added1 = new Medicament(2, 12.75, "newPlacebo", "motherNatureTheOneAndOnly", false);

        repository.upsert(added);
        repository.upsert(added1);

        List<Medicament> allMedicaments = repository.getAll();
        assertSame(allMedicaments.get(0), added);
        assertSame(allMedicaments.get(1), added1);
    }
}
