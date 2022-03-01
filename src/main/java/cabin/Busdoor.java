package cabin;

import java.util.Arrays;

public class Busdoor {
    private final VehicleSide vehicleSide;
    private Boolean isOpen = false;
    private Boolean isLocked = true;

    public Busdoor(VehicleSide side) {
        this.vehicleSide = side;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void toggleDoor() {
        try {
            if (this.isLocked) throw new Exception("Unlock door before toggle");
            this.isOpen = !this.isOpen;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public void toggleDoorLock() {
        try {
            if (isOpen) throw new Exception("Door has to be closed before locking");
            this.isLocked = !this.isLocked;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

}
