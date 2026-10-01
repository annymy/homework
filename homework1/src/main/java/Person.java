public final class Person {//immutable class

    private final String name;
    private final String dateOfBirth;
    private final Home home;

    public Person(String name, String dateOfBirth, Home home) {
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.home = new Home(home);
    }

    public String getDateOfBight() {
        return dateOfBirth;
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
                dateOfBirth +
                ", address " + home;
    }
}