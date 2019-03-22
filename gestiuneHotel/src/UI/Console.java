package UI;

import Domain.CheckIn;
import Domain.RoomAverageRatingViewModel;
import Service.CheckInService;

import java.util.Scanner;

public class Console {

    private CheckInService service;
    private Scanner scanner;

    public Console(CheckInService service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }

    private void showMenu() {
        System.out.println("1. Check-in");
        System.out.println("2. Check-out");
        System.out.println("3. Raport feedback camere");
        System.out.println("a. Afisare toate checkins");
        System.out.println("x. Iesire");
    }

    public void run() {

        while (true) {
            showMenu();
            String option = scanner.nextLine();
            if (option.equals("1")) {
                handleCheckIn();
            } else if (option.equals("2")) {
                handleCheckOut();
            } else if (option.equals("3")) {
                handleStandsReport();
            } else if (option.equals("a")) {
                handleShowAll();
            } else if (option.equals("x")) {
                break;
            }
        }
    }

    private void handleStandsReport() {
        for (RoomAverageRatingViewModel roomAverage : service.getRoomRatingAverages())
            System.out.println(roomAverage);
    }

    private void handleCheckOut() {
        try {
            System.out.println("Scrie numarul camerei:");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Scrie feedback-ul:");
            String feedback = scanner.nextLine();
            System.out.println("Scrie rating-ul:");                 // de modificat exprimarea
            double rating = Double.parseDouble(scanner.nextLine());

            service.checkedOutService(roomNumber, feedback, rating);
        } catch (RuntimeException runtimeException) {
            System.out.println("Avem erori: " + runtimeException.getMessage());
        }
    }

    private void handleShowAll() {
        for (CheckIn c : service.getAll())
            System.out.println(c);
    }

    private void handleCheckIn() {

        try {
            System.out.println("Dati id-ul:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Scrie numarul camerei:");
            int roomNumber = Integer.parseInt(scanner.nextLine());
            System.out.println("Scrie numarul de zile:");
            String daysNumber = scanner.nextLine();
            System.out.println("Scrie numarul de persoane:");
            int personsNumber = Integer.parseInt(scanner.nextLine());

            service.checkIn(id, roomNumber, Integer.parseInt(daysNumber), personsNumber);
        } catch (RuntimeException runtimeException) {
            System.out.println("Avem erori: " + runtimeException.getMessage());
        }
    }
}

