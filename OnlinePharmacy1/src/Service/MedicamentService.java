package Service;

import Domain.Medicament;
import Repository.MedicamentRepository;

import java.util.List;

public class MedicamentService {
    private MedicamentRepository repository;

    public MedicamentService(MedicamentRepository repository) {
        this.repository = repository;
    }

    public void addOrUpdate(int id, double price, String name, String producer, boolean needsRecipe) {
        Medicament existing = repository.findById(id);
        if (existing != null) {
            if(price == 0) {
                price = existing.getPrice();
            }
            if(name.isEmpty()) {
                name = existing.getName();
            }
            if(producer.isEmpty()) {
                producer = existing.getProducer();
            }
        }
        Medicament medicament = new Medicament(id, price, name, producer, needsRecipe);
        repository.upsert(medicament);
    }
    public void remove(int id) {
        repository.remove(id);
    }
    public List<Medicament> getAll() {
        return repository.getAll();
    }
}



