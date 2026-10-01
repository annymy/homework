public class Home { //mutable class
    private String address;
    private double squareMeters;
    private int rooms;

    public Home(String address, double squareMeters, int rooms) {
        this.address = address;
        this.squareMeters = squareMeters;
        this.rooms = rooms;
    }

    public Home(Home home){
        this.address = home.getAddress();
        this.rooms = home.getRooms();
        this.squareMeters = home.getSqr();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSqr() {
        return squareMeters;
    }

    public void setSqr(double squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return address;
    }
}
