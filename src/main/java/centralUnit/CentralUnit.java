package centralUnit;

import cabin.Busdoor;
import cabin.VehicleSide;
import configuration.Configuration;
import drive.Drive;
import firefighting.CannonIdentifier;
import instruments.BatteryIndicator;
import instruments.Speedometer;
import lights.*;
import person.Person;
import tank.MixingProcessor;
import task4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CentralUnit {
    private final List<WarningLight> warningLights;
    private final List<FlashingBlueLight> flashingBlueLights;
    private final List<SearchLight> searchLightsFront;
    private final List<SearchLight> searchLightsRoof;
    private final List<SearchLight> searchLightsSide;
    private final List<DirectionIndicator> indicatorsLeft;
    private final List<DirectionIndicator> indicatorsRight;
    private final MixingProcessor mixingProcessor;
    private final Drive drive;
    private final Speedometer speedometer;
    private final BatteryIndicator batteryIndicator;
    private final ICryptoStrategy cryptoUnit;
    private final String cryptoCode = Configuration.instance.cuCode;
    private final ArrayList<Person> authorizedPersons;
    private final Busdoor busdoorLeft;
    private final Busdoor busdoorRight;

    public CentralUnit(
            List<WarningLight> warningLights,
            List<FlashingBlueLight> flashingBlueLights,
            List<SearchLight> searchLightsFront,
            List<SearchLight> searchLightsRoof,
            List<SearchLight> searchLightsSide,
            List<DirectionIndicator> indicatorsLeft,
            List<DirectionIndicator> indicatorsRight,
            MixingProcessor mixingProcessor,
            Drive drive,
            Speedometer speedometer,
            BatteryIndicator batteryIndicator,
            ArrayList<Person> authorizedPersons,
            Busdoor busdoorLeft,
            Busdoor busdoorRight,
            EncryptionStrategy encryptionStrategy
    ) {
        this.warningLights = warningLights;
        this.flashingBlueLights = flashingBlueLights;
        this.searchLightsFront = searchLightsFront;
        this.searchLightsRoof = searchLightsRoof;
        this.searchLightsSide = searchLightsSide;
        this.indicatorsLeft = indicatorsLeft;
        this.indicatorsRight = indicatorsRight;
        this.mixingProcessor = mixingProcessor;
        this.drive = drive;
        this.speedometer = speedometer;
        this.batteryIndicator = batteryIndicator;
        this.authorizedPersons = authorizedPersons;
        this.busdoorLeft = busdoorLeft;
        this.busdoorRight = busdoorRight;

        this.cryptoUnit = switch (encryptionStrategy) {
            case AES -> new CryptoStrategyAES();
            case RSA -> new CryptoStrategyRSA();
            default -> new CryptoStrategyDES();
        };


    }

    private Boolean validateAuth(String input) {
        String inputCode = input.substring(input.lastIndexOf("-") + 1);
        if (!inputCode.equals(cryptoCode)) return false;

        input = input.substring(0, input.lastIndexOf("-"));
        String inputPerson = input.substring(input.lastIndexOf("-") + 1);
        for (Person p : authorizedPersons) {
            if (inputPerson.equals(p.getName())) return true;
        }
        return false;
    }

    public void switchEngines() {
        this.drive.toggleEngine();
    }

    public void switchWarningLight() {
        for (Light l : this.warningLights) {
            l.toggle();
        }
    }

    public void switchBlueLight() {
        for (Light l : this.flashingBlueLights) {
            l.toggle();
        }
    }

    public void switchFrontLight() {
        for (Light l : this.searchLightsFront) {
            l.toggle();
        }
    }

    public void switchRoofLight() {
        for (Light l : this.searchLightsRoof) {
            l.toggle();
        }
    }

    public void switchSideLight() {
        for (Light l : this.searchLightsSide) {
            l.toggle();
        }
    }

    public void switchSelfprotection() {
        this.mixingProcessor.toggle(CannonIdentifier.CANNON_SELFPROTECTION);
        this.mixingProcessor.spray(CannonIdentifier.CANNON_SELFPROTECTION);
        this.mixingProcessor.toggle(CannonIdentifier.CANNON_SELFPROTECTION);
    }

    public void steer(Integer degree) {
        this.drive.steer(degree);
        Integer angle = this.drive.getSteeringAngle();

        //First switch off all Indicators
        for (DirectionIndicator di : Stream.concat(indicatorsLeft.stream(), indicatorsRight.stream()).toList()) {
            if (di.getState()) di.toggle();
        }

        //Switch on needed indicators
        if (angle < 0) {
            for (DirectionIndicator di : indicatorsLeft) {
                if (!di.getState()) di.toggle();
            }
        } else if (angle > 0) {
            for (DirectionIndicator di : indicatorsRight) {
                if (!di.getState()) di.toggle();
            }
        }
    }

    public void accelerate() {
        this.speedometer.setSpeed(this.drive.accelerate());
    }

    public void brake() {
        this.speedometer.setSpeed(this.drive.brake());
    }

    public void drive() {
        this.speedometer.setSpeed(this.drive.drive());
        this.batteryIndicator.setIndicator(this.drive.getRelativeFillState());
    }

    public void toggleDoor(VehicleSide side) {
        switch (side) {
            case LEFT -> this.busdoorLeft.toggleDoor();
            case RIGHT -> this.busdoorRight.toggleDoor();
        }
    }

    public void toggleDoorLock(String cipher) {
        try {
            if (validateAuth(this.cryptoUnit.decrypt(cipher, this.cryptoCode))) {
                //Abschließen --> Türen noch offen?
                if (!busdoorLeft.getLocked() && busdoorLeft.getOpen()) busdoorLeft.toggleDoor();
                if (!busdoorRight.getLocked() && busdoorRight.getOpen()) busdoorRight.toggleDoor();

                busdoorLeft.toggleDoorLock();
                busdoorRight.toggleDoorLock();

                //Aufschließen --> Türe öffnen
                if (!busdoorLeft.getLocked() && !busdoorLeft.getOpen()) busdoorLeft.toggleDoor();
                if (!busdoorRight.getLocked() && !busdoorRight.getOpen()) busdoorRight.toggleDoor();
            } else {
                throw new Exception("Can't validate authentication");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
        }
    }
}