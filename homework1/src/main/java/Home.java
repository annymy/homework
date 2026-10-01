public class Home { //mutable class
    private String address;
    private double sqr;
    private int rooms;

    public Home(String address, double sqr, int rooms) {
        this.address = address;
        this.sqr = sqr;
        this.rooms = rooms;
    }

    public Home(Home home){
        this.address = home.getAddress();
        this.rooms = home.getRooms();
        this.sqr = home.getSqr();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSqr() {
        return sqr;
    }

    public void setSqr(double sqr) {
        this.sqr = sqr;
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
