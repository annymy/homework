import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {

    @Test
    public void shouldNotBeChangeHomeWhenAddressChanges(){
        Home home = new Home("Penza", 35, 2);
        Person person = new Person("Zina", "22.12.2001", home);

        home.setAddress("Moscow");

        assertEquals("Penza", person.getHome().getAddress());
    }

    @Test
    public void shouldNotBeChangesPersonsHome(){
        Home home = new Home("Madrid", 150, 4);
        Person person = new Person("Oleg", "30.01.1964", home);

        Home personsHome = person.getHome();
        personsHome.setAddress("Tbilisi");

        assertEquals("Madrid", person.getHome().getAddress());
    }
}
