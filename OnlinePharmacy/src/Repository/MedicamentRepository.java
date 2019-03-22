package Repository;

import Domain.Medicament;
import Domain.MedicamentValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicamentRepository {

    private Map<Integer, Medicament> storage = new HashMap<>();
    private MedicamentValidator validator;

    public MedicamentRepository(MedicamentValidator validator) {
        this.validator = validator;
    }

    //Read
    public Medicament findById(int id) {
        return storage.get(id);
    }

    /**
     * Adds or updates a medicament if it already exists. (Create/Update/Edit)
     * @param medicament the medicament to add or update.
     */
    public void upsert(Medicament medicament) {
        validator.validate(medicament);
        storage.put(medicament.getId(), medicament);
    }

    /**
     * Removes a medicament with a given id. (Delete)
     * @param id the id.
     * @throws RuntimeException if there is no medicament with the given id.
     */
    public void remove(Integer id) {
        if (!storage.containsKey(id)) {
            throw new RuntimeException("There is no medicament with the given id to remove.");
        }
        storage.remove(id);
    }

    public List<Medicament> getAll() {
        return new ArrayList<>(storage.values());
    }
}

