///// Name: Yazan Husaain Alyami /////
///// ID: 2339804 /////
///// Section: CS1 /////

public class Flight {

    /////Constructor/////
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private int gateNumber;
    private int row;
    private int column;
    private boolean[][] seatMap;

    //////Method/////
    public Flight(String flightNumber, String departureCity, String arrivalCity, int gateNumber, int row, int column) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.gateNumber = gateNumber;
        this.row = row;
        this.column = column;
        this.seatMap = new boolean[row][column];
    }


    public String getFlightNumber() { return flightNumber; }
    public String getDepartureCity() { return departureCity; }
    public String getArrivalCity() { return arrivalCity; }
    public int getGateNumber() { return gateNumber; }
    public boolean[][] getSeatMap() { return seatMap; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }
    public void setGateNumber(int gateNumber) { this.gateNumber = gateNumber; }


    public boolean bookSeat(int row, char column) {
        int columnIndex = column - 'A';
        if (row >= 0 && row < this.row && columnIndex >= 0 && columnIndex < this.column) {
            if (!seatMap[row][columnIndex]) {
                seatMap[row][columnIndex] = true;
                return true;
            }
        }
        return false;
    }


    public boolean isSeatAvailable(int row, char column) {
        int columnIndex = column - 'A';
        if (row >= 0 && row < this.row && columnIndex >= 0 && columnIndex < this.column) {
            return !seatMap[row][columnIndex];
        }
        return false;
    }


    @Override
    public String toString() {
        return "Flight " + flightNumber + " added successfully";
    }


    public String printSeatPlan() {
        StringBuilder seatPlan = new StringBuilder("Seat Plan:\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                seatPlan.append(seatMap[i][j] ? "X " : "O ");
            }
            seatPlan.append("\n");
        }
        return seatPlan.toString();
    }
}