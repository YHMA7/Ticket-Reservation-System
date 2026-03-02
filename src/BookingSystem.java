///// Name: Yazan Husaain Alyami /////
///// ID: 2339804 /////
///// Section: CS1 /////

import java.io.*;
import java.util.*;

public class BookingSystem {
    // Constants
    private static final int MAX_TICKETS = 100;

    // Array to store tickets
    private static Ticket[] tickets = new Ticket[MAX_TICKETS];

    // Index to keep track of the number of tickets
    private static int ticketIndex = 0;

    // Main method
    public static void main(String[] args) {
        // Initialize arrays for flights and passengers
        Flight[] flights = new Flight[5];
        Passenger[] passengers = new Passenger[10];
        // Try block to handle file operations
        try {
            // Create a PrintWriter for writing output to a file
            PrintWriter writer = new PrintWriter("output.txt");

            // Read flights and passengers from input file
            readFlightsAndPassengers(flights, passengers, "flight_passenger.txt", writer);

            // Read and process commands from inputCommands.txt
            readAndProcessCommands(flights, passengers, "inputCommands.txt", writer);

            // Close the writer
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Method to read flights and passengers from a file
    public static void readFlightsAndPassengers(Flight[] flights, Passenger[] passengers, String filePath, PrintWriter writer) throws FileNotFoundException {
        // Create a File object
        File file = new File(filePath);

        // Create a Scanner to read from the file
        Scanner scanner = new Scanner(file);

        // Variables to keep track of array indices
        int flightIndex = 0;
        int passengerIndex = 0;

        // Read number of flights and passengers from the first line of the file
        int numberOfFlights = scanner.nextInt();
        int numberOfPassengers = scanner.nextInt();

        // Skip the rest of the first line
        scanner.nextLine();

        // Loop through the file to read flights and passengers
        while (scanner.hasNextLine() && (flightIndex < numberOfFlights || passengerIndex < numberOfPassengers)) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts[0].equals("AddFlight") && flightIndex < numberOfFlights) {
                flights[flightIndex++] = new Flight(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                writer.println(flights[flightIndex - 1]);
            } else if (parts[0].equals("AddPassenger") && passengerIndex < numberOfPassengers) {
                passengers[passengerIndex++] = new Passenger(parts[1], parts[2]);
                writer.println(passengers[passengerIndex - 1]);
            }
        }
        scanner.close();
    }

    // Method to read and process commands from a file
    public static void readAndProcessCommands(Flight[] flights, Passenger[] passengers, String filePath, PrintWriter writer) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if (parts[0].equals("BookTicket")) {
                bookTicket(flights, passengers, parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4].charAt(0), parts[5], writer);
            } else if (parts[0].equals("GenerateInvoice")) {
                generateInvoice(Integer.parseInt(parts[1]), writer);
            } else if (parts[0].equals("GenerateIFlightnvoice")) {
                generateFlightInvoice(flights, parts[1], writer);
            }
        }
        scanner.close();
    }

    // Method to book a ticket
    public static boolean bookTicket(Flight[] flights, Passenger[] passengers, String passportNumber, int flightNumber, int seatRow, char seatColumn, String classType, PrintWriter writer) {
        Flight flight = SearchFlight(flights, flightNumber);
        Passenger passenger = SearchPassenger(passengers, passportNumber);
        if (flight == null) {
            writer.println("Flight " + flightNumber + " Not Found");
            return false;
        }
        if (passenger == null) {
            writer.println("Passenger with Passport number " + passportNumber + " is not Registered");
            return false;
        }
        if (!flight.bookSeat(seatRow, seatColumn)) {
            writer.println("Seat " + seatRow + seatColumn + " is already Reserved Or Not found");
            return false;
        }
        if (ticketIndex >= MAX_TICKETS) {
            writer.println("Max ticket limit reached.");
            return false;
        }
        Ticket ticket = new Ticket(flight, passenger, seatRow, String.valueOf(seatColumn), classType);
        tickets[ticketIndex++] = ticket;
        writer.println("*********************BookTicket**************************");
        writer.println("Seat booked successfully.");
        writer.println(ticket);
        return true;
    }

    // Method to search for a flight by flight number
    public static Flight SearchFlight(Flight[] flights, int flightNumber) {
        for (Flight flight : flights) {
            if (flight != null && flight.getFlightNumber().equals(String.valueOf(flightNumber))) {
                return flight;
            }
        }
        return null;
    }

    // Method to search for a passenger by passport number
    public static Passenger SearchPassenger(Passenger[] passengers, String passportNumber) {
        for (Passenger passenger : passengers) {
            if (passenger != null && passenger.getPassportNumber().equals(passportNumber)) {
                return passenger;
            }
        }
        return null;
    }

    public static Ticket findTicketByReservationNumber(int reservationNumber) {
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getReservationConfirmationNumber() == reservationNumber) {
                return ticket;
            }
        }
        return null;
    }

    // Method to find a ticket by reservation number
    public static void generateInvoice(int reservationNumber, PrintWriter writer) {
        Ticket ticket = findTicketByReservationNumber(reservationNumber);
        if (ticket == null) {
            writer.println("*********************Generate Ticket Invoice**************************");
            writer.println("Reservation Number is not available");
            return;
        }
        writer.println("*********************Generate Ticket Invoice**************************");
        writer.println(ticket);
        writer.println("Total ticket price = " + ticket.ticketPrice());
    }

    // Method to generate ticket invoice
    public static void generateFlightInvoice(Flight[] flights, String flightNumber, PrintWriter writer) {
        Flight flight = SearchFlight(flights, Integer.parseInt(flightNumber));
        if (flight == null) {
            writer.println("*********************Generate Flight Invoice**************************");
            writer.println("Flight Not Found or No Ticket booked for this flight");
            return;
        }
        writer.println("*********************Generate Flight Invoice**************************");
        writer.println(flight.printSeatPlan());
        double totalInvoicePrice = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null && ticket.getFlight().getFlightNumber().equals(flightNumber)) {
                writer.println(ticket);
                totalInvoicePrice += ticket.ticketPrice();
            }
        }
        writer.println("Total Invoice price =" + totalInvoicePrice);
    }
}