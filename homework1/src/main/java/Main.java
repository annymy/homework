public class Main {
    public static void main(String[] args) {
        Home home = new Home("Nizhny Novgorod", 75, 3);
        Person person = new Person("Anna", "14.03.1995", home);

        System.out.println(person);


        home.setAddress("Moscow");
        System.out.println(person);
        System.out.println(home);
    }
}