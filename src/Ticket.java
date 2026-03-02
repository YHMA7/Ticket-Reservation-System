///// Name: Yazan Husaain Alyami /////
///// ID: 2339804 /////
///// Section: CS1 /////

public class Ticket {
    private Flight flight;
    private Passenger passenger;
    private int seatRow;
    private String seatNumber;
    private String classType;
    private int reservationConfirmationNumber;
    protected static int reservation = 100;

    public Ticket(Flight flight, Passenger passenger, int seatRow, String seatNumber, String classType) {
        this.flight = flight;
        this.passenger = passenger;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.reservationConfirmationNumber = reservation++;
    }

    /////Getters/////
    public Flight getFlight() { return flight; }
    public Passenger getPassenger() { return passenger; }
    public String getSeatNumber() { return seatNumber; }
    public int getSeatRow() { return seatRow; }
    public String getClassType() { return classType; }
    public int getReservationConfirmationNumber() { return reservationConfirmationNumber; }

    public double ticketPrice() {
        double basePrice = 100.00;
        if (classType.equals("BusinessClass")) {
            basePrice *= 2;
        } else if (classType.equals("FirstClass")) {
            basePrice *= 3;
        }
        return basePrice;
    }

    @Override
    public String toString() {
        return "Ticket Information: \n" +
                "Reservation Confirmation Number= " + reservationConfirmationNumber +
                ", Flight Number=" + flight.getFlightNumber() +
                ", Passenger Name= " + passenger.getName() +
                ", Seat Number= " + seatRow + seatNumber +
                ", classType= " + classType;
    }
}