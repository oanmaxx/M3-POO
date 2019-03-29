package Tests;

import Domain.ClientCard;
import Domain.ClientCardValidator;
import Repository.ClientCardRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientCardRepositoryTests {

    @org.junit.jupiter.api.Test
    void findByIdWithExistingIdShouldReturnCorrectClientCard() {

        ClientCardRepository repo = new ClientCardRepository(new ClientCardValidator());
        ClientCard added = new ClientCard(1, "test", "test", "Gelu","11-03-1922","17-01-2445");
        repo.upsert(added);
        ClientCard found = repo.findById(1);
        assertNotNull(found, "Returned null for existing id!");
        assertEquals(found.getId(), "1", String.format("Returned id %s instead of correct id=1!", found.getId()));
        assertEquals(found.getCNP(), "test", String.format("Returned %s instead of %s", found.getCNP(), added.getCNP()));
        assertEquals(found.getFirstName(), "test", String.format("Returned %s instead of %s", found.getFirstName(), added.getFirstName()));
        assertEquals(found.getLastName(), "Gelu", String.format("Returned %s instead of %s", found.getLastName(), added.getLastName()));
        assertEquals(found.getDateOfBirth(), "11-03-1922", String.format("Returned %s instead of %s", found.getDateOfBirth(), added.getDateOfBirth()));
        assertEquals(found.getDateOfRegistration(), "17-01-2445", String.format("Returned %s instead of %s", found.getDateOfRegistration(), added.getDateOfRegistration()));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        ClientCardRepository repo = new ClientCardRepository(new ClientCardValidator());
        ClientCard added1 = new ClientCard(1, "test1", "test1", "Gelu1","11-03-1922","17-01-2445");
        repo.upsert(added1);

        assertTrue(repo.getAll().size() > 0);

        repo.remove(1);

        assertTrue(repo.getAll().size() == 0, "The item was not removed");
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        // test to get all items
        ClientCardRepository repo = new ClientCardRepository(new ClientCardValidator());
        ClientCard added1 = new ClientCard(1, "test1", "test1", "Gelu1","11-03-1922","17-01-2445");
        ClientCard added2 = new ClientCard(2, "test2", "test2", "Gelu2","11-03-1922","17-01-2445");

        repo.upsert(added1);
        repo.upsert(added2);

        List<ClientCard> allCards = repo.getAll();
        assertSame (allCards.get(0), added1);
        assertSame (allCards.get(1), added2);
    }
}