import Domain.ClientCardValidator;
import Domain.MedicamentValidator;
import Domain.TransactionValidator;
import Repository.ClientCardRepository;
import Repository.MedicamentRepository;
import Repository.TransactionRepository;
import Service.ClientCardService;
import Service.MedicamentService;
import Service.TransactionService;
import UI.NLConsole;

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

        NLConsole console = new NLConsole(medicamentService, clientCardService, transactionService);
        console.run();
    }
}

