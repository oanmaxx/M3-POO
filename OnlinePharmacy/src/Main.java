import Domain.*;
import Repository.MedicamentRepository;
import Repository.ClientCardRepository;
import Repository.TransactionRepository;
import Service.MedicamentService;
import Service.ClientCardService;
import Service.TransactionService;
import UI.Console;

public class Main {

    public static void main(String[] args) {

        MedicamentValidator medicamentValidator = new MedicamentValidator();
        ClientCardValidator clientCardValidator = new ClientCardValidator();
        TransactionValidator transactionValidator = new TransactionValidator();

        MedicamentRepository medicamentRepository = new MedicamentRepository(medicamentValidator);
        ClientCardRepository clientCardRepository = new ClientCardRepository(clientCardValidator);
        TransactionRepository transactionRepository = new TransactionRepository(transactionValidator);

        MedicamentService medicamentService = new MedicamentService(medicamentRepository);
        ClientCardService clientCardService = new ClientCardService(clientCardRepository);
        TransactionService transactionService = new TransactionService(transactionRepository, medicamentRepository);

        Console console = new Console(medicamentService, clientCardService, transactionService);
        console.run();
    }
}
