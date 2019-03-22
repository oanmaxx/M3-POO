package Service;

import Domain.ClientCard;
import Repository.ClientCardRepository;

import java.util.List;

public class ClientCardService {
    private ClientCardRepository repository;

    public ClientCardService(ClientCardRepository repository) {
        this.repository = repository;
    }

    public void addOrUpdate(String id, String CNP, String firstName, String lastName, String dateOfBirth, String dateOfRegistration) {
        ClientCard existing = repository.findById(id);
        if (existing != null) {
            // keep unchanged fields as they were
            if (CNP.isEmpty()) {
                CNP = existing.getCNP();
            }
            if (firstName.isEmpty()) {
                firstName = existing.getFirstName();
            }
            if (dateOfBirth.isEmpty()) {
                dateOfBirth = existing.getDateOfBirth();
            }
            if (dateOfBirth.isEmpty()) {
                dateOfBirth = existing.getDateOfBirth();
            }
            if (dateOfRegistration.isEmpty()) {
                dateOfRegistration = existing.getDateOfRegistration();
            }
        }
        ClientCard clientCard = new ClientCard(id, CNP, firstName, lastName, dateOfBirth, dateOfRegistration);
        repository.upsert(clientCard);
    }

    public void remove(String id) {
        repository.remove(id);
    }

    public List<ClientCard> getAll() {
        return repository.getAll();
    }
}
