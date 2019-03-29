package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ClientCardValidator {
    public void validate(ClientCard clientCard) {

        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try{
            format.parse(clientCard.getDateOfBirth());
        } catch (ParseException pe) {
            throw new RuntimeException("The date of birth is not in a correct format!");
        }

        try{
            format.parse(clientCard.getDateOfRegistration());
        } catch (ParseException pe) {
            throw new RuntimeException("The date of registration is not in a correct format!");
        }
    }

    /**
     * validates by CNP that a client card is created only once.
     * @param allCards the existing repository cards.
     * @param clientCard the card to be validated against repository.
     * @throws RuntimeException if there is a client with the same CNP.
     */
    public void validate(List<ClientCard> allCards, ClientCard clientCard) {
        for (ClientCard c : allCards) {
            if (c.getCNP().equals(clientCard.getCNP())) {
                throw new RuntimeException("CNP must be unique");
            }
        }
    }
}
