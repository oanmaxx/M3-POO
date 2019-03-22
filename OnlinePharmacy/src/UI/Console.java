package UI;

import Domain.ClientCard;
import Domain.Medicament;
import Domain.Transaction;
import Service.ClientCardService;
import Service.MedicamentService;
import Service.TransactionService;
import com.sun.security.ntlm.Client;

import java.awt.*;
import java.util.Scanner;

public class Console {
    MedicamentService medicamentService;
    ClientCardService clientCardService;
    TransactionService transactionService;

    private Scanner scanner;

    public Console(MedicamentService medicamentService, ClientCardService clientCardService, TransactionService transactionService) {
        this.medicamentService = medicamentService;
        this.clientCardService = clientCardService;
        this.transactionService = transactionService;

        this.scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Medicament CRUD");
        System.out.println("2. Client Card CRUD");
        System.out.println("3. Transaction CRUD");
        System.out.println("x. Exit");
    }

    public void run() {
        while (true) {
            showMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runMedicamentCrud();
                    break;
                case "2":
                    runClientCardCrud();
                    break;
                case "3":
                    runTransactionCrud();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void runTransactionCrud() {
        while (true) {
            System.out.println("1. Add or update a transaction");
            System.out.println("2. Remove a transaction");
            System.out.println("3. View all transactions");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateTransaction();
                    break;
                case "2":
                    handleRemoveTransaction();
                    break;
                case "3":
                    handleViewTransactions();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewTransactions() {
        for (Transaction transaction : transactionService.getAll()) {
            System.out.println(transaction);
        }
    }

    private void handleRemoveTransaction() {
        try {
            System.out.print("Enter the id to remove:");
            String id = scanner.nextLine();
            transactionService.remove(id);

            System.out.println("Transaction removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateTransaction() {
        try {
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Enter medicament id(empty to not change for update): ");
            int idMedicament = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client card id(0 to not change for update): ");
            String idClientCard = scanner.nextLine();
            System.out.println("Enter number of items(0 to not change for update): ");
            int numberOfItems = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter date(empty to not change for update): ");
            String date = scanner.nextLine();
            System.out.println("Enter time(empty to not change for update): ");
            String time = scanner.nextLine();

            Transaction transaction = transactionService.addOrUpdate(id, idMedicament, idClientCard, numberOfItems, date, time);
            System.out.println(String.format("Added transaction id%s, paid price=%f, discout=%f%%", transaction.getId(), transaction.getDiscountedPrice(), transaction.getDiscount()));
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void runClientCardCrud() {
        while (true) {
            System.out.println("1. Add or update a client");
            System.out.println("2. Remove a client");
            System.out.println("3. View all clients");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateClient();
                    break;
                case "2":
                    handleRemoveClient();
                    break;
                case "3":
                    handleViewClients();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void handleViewClients() {
        for (ClientCard clientCard : clientCardService.getAll()) {
            System.out.println(clientCard);
        }
    }

    private void handleRemoveClient() {
        try {
            System.out.println("Enter the id to remove: ");
            String id = scanner.nextLine();
            clientCardService.remove(id);

            System.out.println("Client removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateClient() {
        try{
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            System.out.println("Enter last name(empty to not change for update): ");
            String lastName = scanner.nextLine();
            System.out.println("Enter first name(empty to not change for update): ");
            String firstName = scanner.nextLine();
            System.out.println("Enter CNP(empty to not change for update): ");
            String CNP = scanner.nextLine();
            System.out.println("Enter date of birth(dd.mm.yyyy - empty to not change for update): ");
            String dateOfBirth = scanner.nextLine();
            System.out.println("Enter date of registration(dd.mm.yyyy - empty to not change for update): ");
            String dateOfRegistration = scanner.nextLine();

            clientCardService.addOrUpdate(id, lastName, firstName, CNP, dateOfBirth, dateOfRegistration);

            System.out.println("Client added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void runMedicamentCrud() {
        while (true) {
            System.out.println("1. Add or update a medicament");
            System.out.println("2. Remove a medicament");
            System.out.println("3. View all medicaments");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateMedicament();
                    break;
                case "2":
                    handleRemoveMedicament();
                    break;
                case "3":
                    handleViewMedicaments();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid error!");
                    break;
            }
        }
    }

    private void handleViewMedicaments() {
        for (Medicament medicament : medicamentService.getAll()) {
            System.out.println(medicament);
        }
    }

    private void handleRemoveMedicament() {
        try{
            System.out.println("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            medicamentService.remove(id);

            System.out.println("Medicament removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateMedicament() {
        try {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter price (0 to not change for update): ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter name (empty to not change for update): ");
            String name = scanner.nextLine();
            System.out.print("Enter producer (empty to not change for update): ");
            String producer = scanner.nextLine();
            System.out.print("Enter if it needs recipe (true / false): ");
            boolean needsRecipe = Boolean.parseBoolean(scanner.nextLine());

            medicamentService.addOrUpdate(id, price, name, producer, needsRecipe);

            System.out.println("Medicament added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }
}
