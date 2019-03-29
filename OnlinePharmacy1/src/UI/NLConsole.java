package UI;

import Domain.ClientCard;
import Domain.Transaction;
import Domain.Medicament;
import Service.ClientCardService;
import Service.MedicamentService;
import Service.TransactionService;

import java.util.Scanner;

public class NLConsole {
    MedicamentService medicamentService;
    ClientCardService clientCardService;
    TransactionService transactionService;

    private Scanner scanner;

    public NLConsole(MedicamentService medicamentService, ClientCardService clientCardService, TransactionService
            transactionService) {
        this.medicamentService = medicamentService;
        this.clientCardService = clientCardService;
        this.transactionService = transactionService;

        this.scanner = new Scanner(System.in);
    }

    /***
     * shows the menu
     */
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

    /***
     *CRUD Transaction
     */
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

    /***
     * Shows all transactions.
     */
    private void handleViewTransactions() {
        for (Transaction transaction : transactionService.getAll()) {
            System.out.println(transaction);
        }
    }

    /***
     * Removes a transaction.
     */
    private void handleRemoveTransaction() {
        try {
            System.out.print("Enter the id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            transactionService.remove(id);

            System.out.println("Transaction removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    /***
     * Adds or updates a transaction.
     * @throws Exception
     */
    private void handleAddUpdateTransaction() {
        try {
            System.out.println("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter medicament id(empty to not change for update): ");
            int idMedicament = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client card id(0 to not change for update): ");
            int idClientCard = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter number of items(0 to not change for update): ");
            int numberOfItems = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter date(empty to not change for update): ");
            String date = scanner.nextLine();
            System.out.println("Enter time(empty to not change for update): ");
            String time = scanner.nextLine();

            Transaction transaction = transactionService.addOrUpdate(id, idMedicament, idClientCard,
                    numberOfItems, date, time);
            System.out.println(String.format("Added transaction id%s, paid price=%f, discout=%f%%",
                    transaction.getId(), transaction.getDiscountedPrice(), transaction.getDiscount()));
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    /***
     * CRUD ClientCard
     */
    private void runClientCardCrud() {
        while (true) {
            System.out.println("1. Add or update a client");
            System.out.println("2. Remove a client");
            System.out.println("3. View all clients");
            System.out.println("4. Back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddUpdateClientCard();
                    break;
                case "2":
                    handleRemoveClientCard();
                    break;
                case "3":
                    handleViewClientsCards();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    /***
     * Shows all clients cards
     */
    private void handleViewClientsCards() {
        for (ClientCard clientCard : clientCardService.getAll()) {
            System.out.println(clientCard);
        }
    }

    /***
     * Removes a client card
     */
    private void handleRemoveClientCard() {
        try {
            System.out.println("Enter the id to remove: ");
            int id = Integer.parseInt(scanner.nextLine());
            clientCardService.remove(id);

            System.out.println("Client removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    /***
     * Adds or updates a client card
     */
    private void handleAddUpdateClientCard() {
        try {
            System.out.println("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());
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

    private void handleViewMedicaments() {
        for (Medicament medicament : medicamentService.getAll()) {
            System.out.println(medicament);
        }
    }

    private void handleRemoveMedicament(String[] args) {
        try {
            int id = Integer.parseInt(args[0].trim());
            medicamentService.remove(id);

            System.out.println("Medicament removed!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    private void handleAddUpdateMedicament(String[] addUpdateArgs) {
        try {
            int id = Integer.parseInt(addUpdateArgs[0].trim());
            Double price = Double.parseDouble(addUpdateArgs[1].trim());
            String name = addUpdateArgs[2].trim();
            String producer = addUpdateArgs[3].trim();
            Boolean needsRecipe = Boolean.parseBoolean(addUpdateArgs[4].trim());

            medicamentService.addOrUpdate(id, price, name, producer, needsRecipe);

            System.out.println("Medicament added!");
        } catch (Exception ex) {
            System.out.println("Errors:\n" + ex.getMessage());
        }
    }

    public void runMedicamentCrud() {
        while (true) {
            try {
                System.out.print("Type one of the commands below:\n" +
                        "Add Medicament,[id],[price],[name],[producer],[needsRecipe]\n" +
                        "Remove Medicament,[id]\n" +
                        "View Medicaments\n" +
                        "Exit\n");

                String line = scanner.nextLine();           // line is the input which has comma separated words
                String[] input = line.split(",");
                String args[] = new String[10];             // instantiates an array of 10

                // transfer all arguments but first
                for (int i = 0; i < input.length - 1; i++) {
                    args[i] = input[i + 1];
                    System.out.print(args[i]+"\n");
                }

                if (input[0].toLowerCase().equals("exit")) {
                    break;
                }

                if (input[0].toLowerCase().equals("add medicament")) {
                    handleAddUpdateMedicament(args);
                }

                if (input[0].toLowerCase().equals("remove medicament")) {
                    handleRemoveMedicament(args);
                }

                if (input[0].toLowerCase().equals("view medicaments")) {
                    handleViewMedicaments();
                }

            } catch (Exception ex) {
                System.out.println("Errors:\n" + ex.getMessage());
            }
        }
    }
}




