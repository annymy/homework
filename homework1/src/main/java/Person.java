public final class Person {//immutable class

    private final String name;
    private final String dateOfBight;
    private final Home home;

    public Person(String dateOfBight, String name, Home home) {
        this.dateOfBight = dateOfBight;
        this.name = name;
        this.home = new Home(home);
    }

    public String getDateOfBight() {
        return dateOfBight;
    }


    public String getName() {
        return name;
    }

    public Home getHome() {
        return new Home(home);
    }

    @Override
    public String toString() {
        return name + " " +
                dateOfBight +
                ", adress " + home;
    }
}