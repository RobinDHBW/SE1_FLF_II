package seating;

import person.Person;

public class Seat {
    private final Respirator respirator = new Respirator();
    protected Integer seatRow;
    protected boolean leftSide;
    protected boolean occupied = false;
    protected Person seatWarmer;

    public Seat(Integer seatRow, Boolean leftSide) {
        this.seatRow = seatRow;
        this.leftSide = leftSide;

    }

    public void sitDown(Person person) {
        this.seatWarmer = person;
        this.occupied = true;
    }

    public Person leave() {
        Person p = this.seatWarmer;
        this.seatWarmer = null;
        this.occupied = false;
        return p;
    }

    public Integer getSeatRow() {
        return this.seatRow;
    }

    public Boolean getLeftSide() {
        return this.leftSide;
    }

    public Boolean getOccupied() {
        return this.occupied;
    }

}