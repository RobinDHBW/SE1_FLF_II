import flf.FLF;
import person.Driver;
import person.Operator;
import person.Person;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        ArrayList<Person> authorizedPersons = new ArrayList<>();
        authorizedPersons.add(new Driver("Red Adair"));
        authorizedPersons.add(new Operator("Sam"));
        FLF flf = new FLF.Builder(authorizedPersons).build();
    }
}