package Repository;

import Domain.ClientCard;
import Domain.ClientCardValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class ClientCardRepository {

        private Map<String, ClientCard> storage = new HashMap<>();
        private ClientCardValidator validator;

        public ClientCardRepository(ClientCardValidator validator) {
            this.validator = validator;
        }

        public ClientCard findById(String id) {
            return storage.get(id);
        }

        /**
         * Adds or updates a client card if it already exists.
         * @param clientCard the client card to add or update.
         */
        public void upsert(ClientCard clientCard) {
            validator.validate(this.getAll(), clientCard);
            storage.put(clientCard.getId(), clientCard);
        }

        /**
         * Removes a client card with a given id.
         * @param id the id.
         * @throws RuntimeException if there is no client card with the given id.
         */
        public void remove(String id) {
            if (!storage.containsKey(id)) {
                throw new RuntimeException("There is no client card with the given id to remove.");
            }

            storage.remove(id);
        }

        public List<ClientCard> getAll() {
            return new ArrayList<>(storage.values());
        }
    }
