package cabin;

import button.*;
import task2.CentralUnit;
import instruments.BatteryIndicator;
import instruments.Speedometer;
import instruments.SteeringWheel;
import joystick.Joystick;
import person.ActivePassenger;
import person.Driver;
import person.Operator;
import person.Person;
import seating.Seat;
import seating.SeatFirefighting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cabin {
    private final List<Seat> seatList;

    private final BatteryIndicator batteryIndicator;
    private final Speedometer speedometer;

    private final Pedal gasPedal;
    private final Pedal brakePedal;

    private final SteeringWheel steeringWheel;

    private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof;
    private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront;

    private final ControlPanel ctrlPanel;
    private final CentralUnit centralUnit;

    private final Joystick joystickDriver;
    private final Joystick joystickOperator;

    private final Busdoor busdoorLeft;
    private final ButtonPush doorToggleLeftInside;
    private final ButtonPush doorToggleLeftOutside;
    private final IDCardReader cardReaderLeft;

    private final Busdoor busdoorRight;
    private final ButtonPush doorToggleRightInside;
    private final ButtonPush doorToggleRightOutside;
    private final IDCardReader cardReaderRight;

    private Cabin(Builder builder) {
        //Cabin built = builder.build();
        this.seatList = builder.seatList;

        this.batteryIndicator = builder.batteryIndicator;
        this.speedometer = builder.speedometer;

        this.gasPedal = builder.gasPedal;
        this.brakePedal = builder.brakePedal;

        this.steeringWheel = builder.steeringWheel;

        this.btnRotaryWaterCannonRoof = builder.btnRotaryWaterCannonRoof;
        this.btnRotaryWaterCannonFront = builder.btnRotaryWaterCannonFront;

        this.ctrlPanel = builder.ctrlPanel;
        this.centralUnit = builder.centralUnit;

        this.joystickDriver = builder.joystickDriver;
        this.joystickOperator = builder.joystickOperator;

        this.busdoorLeft = builder.busdoorLeft;
        this.doorToggleLeftInside = builder.doorToggleLeftInside;
        this.doorToggleLeftOutside = builder.doorToggleLeftOutside;
        this.cardReaderLeft = builder.cardReaderLeft;

        this.busdoorRight = builder.busdoorRight;
        this.doorToggleRightInside = builder.doorToggleRightInside;
        this.doorToggleRightOutside = builder.doorToggleRightOutside;
        this.cardReaderRight = builder.cardReaderRight;

    }

    /**********
     * Getter
     *********/

    public List<Seat> getSeatList() {
        return seatList;
    }

    public BatteryIndicator getBatteryIndicator() {
        return batteryIndicator;
    }

    public Speedometer getSpeedometer() {
        return speedometer;
    }

    public Pedal getGasPedal() {
        return gasPedal;
    }

    public Pedal getBrakePedal() {
        return brakePedal;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public ButtonRotaryWaterCannonRoof getBtnRotaryWaterCannonRoof() {
        return btnRotaryWaterCannonRoof;
    }

    public ButtonRotaryWaterCannonFront getBtnRotaryWaterCannonFront() {
        return btnRotaryWaterCannonFront;
    }

    public ControlPanel getCtrlPanel() {
        return ctrlPanel;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }

    public Joystick getJoystickDriver() {
        return joystickDriver;
    }

    public Joystick getJoystickOperator() {
        return joystickOperator;
    }

    public Busdoor getBusDoorLeft() {
        return busdoorLeft;
    }

    public Busdoor getBusDoorRight() {
        return busdoorRight;
    }

    public ButtonPush getDoorToggleLeftOutside() {
        return doorToggleLeftOutside;
    }

    public IDCardReader getCardReaderLeft() {
        return cardReaderLeft;
    }

    public ButtonPush getDoorToggleRightOutside() {
        return doorToggleRightOutside;
    }

    public IDCardReader getCardReaderRight() {
        return cardReaderRight;
    }

    public void enterCabin(Person enterer, Boolean isLeft) {
        try {
            if (!(isLeft ? this.getBusDoorLeft() : this.getBusDoorRight()).getOpen())
                throw new Exception("Door not open");
            for (Seat seat : seatList) {
                if (seat.getOccupied()) continue;

                if (enterer instanceof ActivePassenger) {
                    if (seat instanceof SeatFirefighting && enterer.getClass().equals(((SeatFirefighting) seat).getPersonAllowed())) {
                        seat.sitDown(enterer);
                        enterer.setIsInVehicle(true);
                        if (enterer instanceof Driver) {
                            ((Driver) enterer).equip(this.steeringWheel, this.gasPedal, this.brakePedal, this.joystickDriver, this.doorToggleLeftInside);
                        } else {
                            ((Operator) enterer).equip(this.ctrlPanel, this.joystickOperator, this.btnRotaryWaterCannonFront, this.btnRotaryWaterCannonRoof, this.doorToggleRightInside);
                        }
                    }
                } else {
                    if (!(seat instanceof SeatFirefighting) && seat.getLeftSide() == isLeft) {
                        seat.sitDown(enterer);
                        enterer.setIsInVehicle(true);
                    }
                }
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }

    public Person leaveCabin(Integer row, Boolean isLeft) {
        try {
            if (!(isLeft ? this.getBusDoorLeft() : this.getBusDoorRight()).getOpen())
                throw new Exception("Door not open");

            for (Seat seat : seatList) {
                if (Objects.equals(seat.getSeatRow(), row) && seat.getLeftSide() == isLeft) return seat.leave();
            }
            return null;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    public static class Builder {
        private final CentralUnit centralUnit;

        private final BatteryIndicator batteryIndicator;// = new BatteryIndicator();
        private final Speedometer speedometer;// = new Speedometer();

        private final Pedal gasPedal;
        private final Pedal brakePedal;

        private final SteeringWheel steeringWheel;

        private final ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof;
        private final ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront;

        private final ControlPanel ctrlPanel;

        private final List<Seat> seatList = new ArrayList<>();

        private final Joystick joystickDriver;
        private final Joystick joystickOperator;

        private final Busdoor busdoorLeft;
        private final ButtonPush doorToggleLeftInside;
        private final ButtonPush doorToggleLeftOutside;
        private final IDCardReader cardReaderLeft;

        private final Busdoor busdoorRight;
        private final ButtonPush doorToggleRightInside;
        private final ButtonPush doorToggleRightOutside;
        private final IDCardReader cardReaderRight;

        public Builder(
                ControlPanel controlPanel,
                Pedal gasPedal,
                Pedal brakePedal,
                ButtonRotaryWaterCannonRoof btnRotaryWaterCannonRoof,
                ButtonRotaryWaterCannonFront btnRotaryWaterCannonFront,
                Joystick joystickDriver,
                Joystick joystickOperator,
                SteeringWheel steeringWheel,
                Speedometer speedometer,
                CentralUnit centralUnit,
                BatteryIndicator batteryIndicator,
                Busdoor busdoorLeft,
                ButtonPush doorToggleLeftInside,
                ButtonPush doorToggleLeftOutside,
                IDCardReader cardReaderLeft,
                Busdoor busdoorRight,
                ButtonPush doorToggleRightInside,
                ButtonPush doorToggleRightOutside,
                IDCardReader cardReaderRight
        ) {
            this.busdoorLeft = busdoorLeft;
            this.doorToggleLeftInside = doorToggleLeftInside;
            this.doorToggleLeftOutside = doorToggleLeftOutside;
            this.cardReaderLeft = cardReaderLeft;

            this.busdoorRight = busdoorRight;
            this.doorToggleRightInside = doorToggleRightInside;
            this.doorToggleRightOutside = doorToggleRightOutside;
            this.cardReaderRight = cardReaderRight;


            for (int i = 0; i < 2; i++) {
                Boolean leftSide = (i == 0);
                seatList.add(new Seat(1, leftSide));
            }
            seatList.add(new SeatFirefighting(Driver.class, true));
            seatList.add(new SeatFirefighting(Operator.class, false));

            this.ctrlPanel = controlPanel;

            this.btnRotaryWaterCannonRoof = btnRotaryWaterCannonRoof;
            this.btnRotaryWaterCannonFront = btnRotaryWaterCannonFront;

            this.joystickDriver = joystickDriver;
            this.joystickOperator = joystickOperator;

            this.steeringWheel = steeringWheel;

            this.gasPedal = gasPedal;
            this.brakePedal = brakePedal;
            this.centralUnit = centralUnit;
            this.speedometer = speedometer;
            this.batteryIndicator = batteryIndicator;
        }

        public Cabin build() {
            return new Cabin(this);
        }
    }
}