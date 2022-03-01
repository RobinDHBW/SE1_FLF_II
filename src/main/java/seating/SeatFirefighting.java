package seating;

import person.Person;

import java.util.Arrays;

public class SeatFirefighting extends Seat {
    protected Class personAllowed;

    public SeatFirefighting(Class personAllowed, Boolean leftSide) {
        super(0, leftSide);
        this.personAllowed = personAllowed;
    }

    @Override
    public void sitDown(Person person) {
        try {
            if (!person.getClass().equals(personAllowed))
                throw new Exception("This Seat is reserved for: " + personAllowed);
            super.sitDown(person);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public Class getPersonAllowed() {
        return personAllowed;
    }
}