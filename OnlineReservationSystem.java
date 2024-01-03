import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Reservation {
    String user_id;
    String train_number;
    String class_type;
    String date_of_journey;
    String source;
    String destination;
    String pnr;

    public Reservation(String user_id, String train_number, String class_type, String date_of_journey,
                       String source, String destination, String pnr) {
        this.user_id = user_id;
        this.train_number = train_number;
        this.class_type = class_type;
        this.date_of_journey = date_of_journey;
        this.source = source;
        this.destination = destination;
        this.pnr = pnr;
    }
}

public class OnlineReservationSystem {
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=== Online Reservation System ===");
        String user_id = login();

        while (true) {
            System.out.println("\n1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Logout");

            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeReservation(user_id);
                    break;
                case 2:
                    cancelReservation(user_id);
                    break;
                case 3:
                    System.out.println("Logout successful. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private static String login() {
        System.out.print("Enter your login id: ");
        String user_id = scanner.next();

        return user_id;
    }

    private static void makeReservation(String user_id) {
        System.out.println("\n=== Reservation Form ===");

        System.out.print("Enter Train Number: ");
        String train_number = scanner.next();
        System.out.print("Enter Class Type: ");
        String class_type = scanner.next();
        System.out.print("Enter Date of Journey: ");
        String date_of_journey = scanner.next();
        System.out.print("Enter Source: ");
        String source = scanner.next();
        System.out.print("Enter Destination: ");
        String destination = scanner.next();

        String pnr = generatePNR();

        Reservation reservation = new Reservation(user_id, train_number, class_type, date_of_journey, source, destination, pnr);
        reservations.add(reservation);

        System.out.println("Reservation successful. Your PNR is: " + pnr);
    }

    private static void cancelReservation(String user_id) {
        System.out.println("\n=== Cancellation Form ===");

        System.out.print("Enter PNR Number to cancel reservation: ");
        String pnr = scanner.next();

        for (Reservation reservation : reservations) {
            if (reservation.user_id.equals(user_id) && reservation.pnr.equals(pnr)) {
                displayReservationDetails(reservation);

                System.out.print("Do you want to cancel this reservation? (yes/no): ");
                String confirmation = scanner.next().toLowerCase();

                if (confirmation.equals("yes")) {
                    reservations.remove(reservation);
                    System.out.println("Reservation canceled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                return;
            }
        }

        System.out.println("Reservation not found for the provided PNR.");
    }

    private static String generatePNR() {
      
        return "PNR" + random.nextInt(1000000);
    }

    private static void displayReservationDetails(Reservation reservation) {
        System.out.println("Reservation Details:");
        System.out.println("Train Number: " + reservation.train_number);
        System.out.println("Class Type: " + reservation.class_type);
        System.out.println("Date of Journey: " + reservation.date_of_journey);
        System.out.println("Source: " + reservation.source);
        System.out.println("Destination: " + reservation.destination);
    }
}

