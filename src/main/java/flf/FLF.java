package flf;

import button.*;
import cabin.Busdoor;
import cabin.Cabin;
import cabin.ControlPanel;
import cabin.VehicleSide;
import centralUnit.CentralUnit;
import configuration.Configuration;
import drive.Drive;
import firefighting.CannonIdentifier;
import firefighting.WaterCannonFront;
import firefighting.WaterCannonRoof;
import firefighting.WaterDieSelfprotection;
import instruments.BatteryIndicator;
import instruments.Speedometer;
import instruments.SteeringWheel;
import joystick.Joystick;
import joystick.JoystickType;
import lights.*;
import person.EmployeeFirebase;
import person.Person;
import tank.MixingProcessor;
import tank.Tank;
import tank.TankSubject;
import task1_imp.MixingUnitMediator;
import task8.TankSensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FLF {
    private final List<SearchLight> searchLightsFront;
    private final List<SearchLight> searchLightsRoof;
    private final List<SearchLight> searchLightsSide;
    private final List<DirectionIndicator> directionIndicatorsLeft;
    private final List<DirectionIndicator> directionIndicatorsRight;
    private final List<BrakingLight> brakingLights;

    private final List<FlashingBlueLight> flashingBlueLights;
    private final List<WarningLight> warningLights;

    private final Cabin cabin;

    private final Drive drive;

    private final MixingProcessor mixingProcessor;
    private final WaterCannonFront waterCannonFront;
    private final WaterCannonRoof waterCannonRoof;
    private final ArrayList<WaterDieSelfprotection> waterDieSelfprotection;
    private final Tank foamTank;
    private final Tank waterTank;
    private final String identifier;

    private Boolean maintenanceState = false;

    private FLF(Builder builder) {

        //FLF built = builder.build();
        this.brakingLights = builder.brakingLights;
        this.searchLightsFront = builder.searchLightsFront;
        this.searchLightsRoof = builder.searchLightsRoof;
        this.searchLightsSide = builder.searchLightsSide;
        this.directionIndicatorsLeft = builder.directionIndicatorsLeft;
        this.directionIndicatorsRight = builder.directionIndicatorsRight;

        this.flashingBlueLights = builder.flashingBlueLights;
        this.warningLights = builder.warningLights;

        this.cabin = builder.cabin;

        this.drive = builder.drive;

        this.mixingProcessor = builder.mixingProcessor;
        this.waterCannonFront = builder.waterCannonFront;
        this.waterCannonRoof = builder.waterCannonRoof;
        this.waterDieSelfprotection = builder.waterDieSelfprotection;
        this.foamTank = builder.foamTank;
        this.waterTank = builder.waterTank;
        this.identifier = builder.identifier;
    }

    /**********
     * Getter
     *********/

    public List<SearchLight> getSearchLightsFront() {
        return searchLightsFront;
    }

    public List<SearchLight> getSearchLightsRoof() {
        return searchLightsRoof;
    }

    public List<SearchLight> getSearchLightsSide() {
        return searchLightsSide;
    }

    public List<DirectionIndicator> getDirectionIndicatorsLeft() {
        return directionIndicatorsLeft;
    }

    public List<DirectionIndicator> getDirectionIndicatorsRight() {
        return directionIndicatorsRight;
    }

    public List<BrakingLight> getBrakingLights() {
        return brakingLights;
    }

    public List<FlashingBlueLight> getFlashingBlueLights() {
        return flashingBlueLights;
    }

    public List<WarningLight> getWarningLights() {
        return warningLights;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public Drive getDrive() {
        return drive;
    }

    public MixingProcessor getMixingProcessor() {
        return mixingProcessor;
    }

    public Boolean getSearchLightFrontState() {
        return this.searchLightsFront.get(0).getState();
    }

    public Boolean getSearchLightRoofState() {
        return this.searchLightsRoof.get(0).getState();
    }

    public Boolean getSearchLightSideState() {
        return this.searchLightsSide.get(0).getState();
    }

    public Boolean getBlueLightState() {
        return this.flashingBlueLights.get(0).getState();
    }

    public Boolean getWarnLightsState() {
        return this.warningLights.get(0).getState();
    }

    public Boolean getWaterTankLEDState() {
        return this.cabin.getCtrlPanel().getWaterTankSensorLED().getState();
    }

    public Boolean getFoamTankLEDState() {
        return this.cabin.getCtrlPanel().getFoamTankSensorLED().getState();
    }

    public LEDColor getWaterTankLEDColor() {
        return this.cabin.getCtrlPanel().getWaterTankSensorLED().getLEDColor();
    }

    public LEDColor getFoamTankLEDColor() {
        return this.cabin.getCtrlPanel().getFoamTankSensorLED().getLEDColor();
    }

    public void enterFLF(Person enterer, Boolean isLeft) {
        this.cabin.enterCabin(enterer, isLeft);
    }

    public Person leaveFLF(Integer row, Boolean isLeft) {
        return this.cabin.leaveCabin(row, isLeft);
    }

    public void toggleMaintenance(EmployeeFirebase eFB) {
        if (this.maintenanceState) {
            eFB.uneqip();
        } else {
            eFB.equip(this.mixingProcessor, this.drive);
        }
        this.maintenanceState = !this.maintenanceState;
    }

    public Integer drive() {
        try {
            if (this.maintenanceState) throw new Exception("Cannot drive, while in maintenance mode");
            return this.drive.drive();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.err.println(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    /**
     *
     */
    public static class Builder {
        private final String identifier = Configuration.instance.flfIdentifier;
        private final List<WarningLight> warningLights = new ArrayList<>();
        private final List<DirectionIndicator> directionIndicatorsLeft = new ArrayList<>();
        private final List<DirectionIndicator> directionIndicatorsRight = new ArrayList<>();
        private final List<BrakingLight> brakingLights = new ArrayList<>();
        private final List<SearchLight> searchLightsFront = new ArrayList<>();
        private final List<SearchLight> searchLightsRoof = new ArrayList<>();
        private final List<SearchLight> searchLightsSide = new ArrayList<>();
        private final List<FlashingBlueLight> flashingBlueLights = new ArrayList<>();
        private final Cabin cabin;

        private final JoystickType joystickType = JoystickType.INTELLIGENT;

        private final Drive drive = new Drive();
        private final ArrayList<WaterDieSelfprotection> waterDieSelfprotection = new ArrayList<>();
        private MixingProcessor mixingProcessor;
        private final LEDLight waterTankSensorLED = new LEDLight(LightPosition.CONTROL_PANEL, 1, LEDColor.RED);
        private final LEDLight foamTankSensorLED = new LEDLight(LightPosition.CONTROL_PANEL, 1, LEDColor.RED);


        private WaterCannonFront waterCannonFront;
        private WaterCannonRoof waterCannonRoof;
        private Tank foamTank;
        private Tank waterTank;
        private CentralUnit centralUnit;
        private TankSensor waterTankSensor = new TankSensor();
        private TankSensor foamTankSensor = new TankSensor();

        public Builder(ArrayList<Person> authorizedPersons) {


            buildLights();
            buildFirefighting();

            Speedometer speedometer = new Speedometer();
            BatteryIndicator batteryIndicator = new BatteryIndicator();
            Busdoor busdoorLeft = new Busdoor(VehicleSide.LEFT);
            Busdoor busdoorRight = new Busdoor(VehicleSide.RIGHT);

            centralUnit = new CentralUnit(warningLights, flashingBlueLights, searchLightsFront, searchLightsRoof, searchLightsSide, directionIndicatorsLeft, directionIndicatorsRight, mixingProcessor, drive, speedometer, batteryIndicator, authorizedPersons, busdoorLeft, busdoorRight, Configuration.instance.encryptionStrategy, waterTankSensorLED, foamTankSensorLED);
            this.waterTankSensor.addListener(centralUnit);
            this.foamTankSensor.addListener(centralUnit);

            ButtonPush doorToggleLeftInside = new ButtonPush(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).toggleDoor(VehicleSide.LEFT);
                }
            };
            ButtonPush doorToggleLeftOutside = new ButtonPush(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).toggleDoor(VehicleSide.LEFT);
                }
            };

            ButtonPush doorToggleRightInside = new ButtonPush(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).toggleDoor(VehicleSide.RIGHT);
                }
            };
            ButtonPush doorToggleRightOutside = new ButtonPush(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).toggleDoor(VehicleSide.RIGHT);
                }
            };

            Pedal pedalAcc = new Pedal(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).accelerate();
                }
            };
            Pedal pedalBrake = new Pedal(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).brake();
                }
            };

            ButtonRotaryWaterCannonFront btnCannonFront = new ButtonRotaryWaterCannonFront(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    ((MixingProcessor) this.operatingDevice).setSprayCapacityPerlIteration(CannonIdentifier.CANNON_FRONT, this.amountPerIteration);
                }
            };
            ButtonRotaryWaterCannonRoof btnCannonRoof = new ButtonRotaryWaterCannonRoof(this.mixingProcessor) {
                @Override
                public void operateDevice() {
                    ((MixingProcessor) this.operatingDevice).setSprayCapacityPerlIteration(CannonIdentifier.CANNON_ROOF, this.amountPerIteration);
                }
            };

            Joystick joystickDriver = buildJoystick(true, joystickType);
            Joystick joystickOperator = buildJoystick(false, joystickType);

            SteeringWheel steeringWheel = new SteeringWheel(centralUnit) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).steer(this.getDegree());
                }
            };

            this.cabin = new Cabin.Builder(
                    this.buildControlPanel(centralUnit),
                    pedalAcc,
                    pedalBrake,
                    btnCannonRoof,
                    btnCannonFront,
                    joystickDriver,
                    joystickOperator,
                    steeringWheel,
                    speedometer,
                    centralUnit,
                    batteryIndicator,
                    busdoorLeft,
                    doorToggleLeftInside,
                    doorToggleLeftOutside,
                    new IDCardReader(centralUnit),
                    busdoorRight,
                    doorToggleRightInside,
                    doorToggleRightOutside,
                    new IDCardReader(centralUnit)
            ).build();


        }

        private void buildLights() {
            // add Breaklights
            for (int i = 0; i < 2; i++) {
                LightPosition position = i == 0 ? LightPosition.BACK_LEFT : LightPosition.BACK_RIGHT;
                this.brakingLights.add(new BrakingLight(position));
            }

            // add Searchlights Front
            for (int i = 0; i < 6; i++) {
                LightPosition position = switch (i) {
                    case 0, 1, 2 -> LightPosition.FRONT_LEFT;
                    default -> LightPosition.FRONT_RIGHT;
                };
                this.searchLightsFront.add(new SearchLight(position));
            }
            for (int i = 0; i < 4; i++) {
                this.searchLightsRoof.add(new SearchLight(LightPosition.ROOF_FRONT));
            }
            for (int i = 0; i < 10; i++) {
                LightPosition position = i < 5 ? LightPosition.LEFT_SIDE : LightPosition.RIGHT_SIDE;
                this.searchLightsSide.add(new SearchLight((position)));
            }

            // add Indicators
            for (int i = 0; i < 4; i++) {
                LightPosition position = switch (i) {
                    case 0 -> LightPosition.FRONT_LEFT;
                    case 1 -> LightPosition.BACK_LEFT;
                    case 2 -> LightPosition.FRONT_RIGHT;
                    default -> LightPosition.BACK_RIGHT;
                };
                if (i < 2) {
                    this.directionIndicatorsLeft.add(new DirectionIndicator(position));
                } else {
                    this.directionIndicatorsRight.add(new DirectionIndicator(position));
                }

            }

            // add small FlashingBlueLights
            for (int i = 0; i < 2; i++) {
                this.flashingBlueLights.add(new FlashingBlueLightSmall(LightPosition.FRONT_AREA));
            }

            // add medium FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_BACK_LEFT : LightPosition.ROOF_BACK_RIGHT;
                this.flashingBlueLights.add(new FlashingBlueLightMedium(position));
            }

            // add big FlashingBlueLights
            for (int i = 0; i < 4; i++) {
                LightPosition position = i < 2 ? LightPosition.ROOF_FRONT_LEFT : LightPosition.ROOF_FRONT_RIGHT;
                this.flashingBlueLights.add(new FlashingBlueLightBig(position));
            }

            // add WarningLights
            for (int i = 0; i < 2; i++) {
                this.warningLights.add(new WarningLight(i < 1 ? LightPosition.ROOF_FRONT_LEFT : LightPosition.ROOF_BACK_RIGHT));
            }
        }

        private void buildFirefighting() {

            this.waterCannonFront = new WaterCannonFront(90);
            this.waterCannonRoof = new WaterCannonRoof();
            this.foamTank = new Tank(TankSubject.FOAM, 75, 45, 10, this.foamTankSensor);
            this.waterTank = new Tank(TankSubject.WATER, 75, 45, 30, this.waterTankSensor);

            //add Waterdies
            for (int i = 0; i < 7; i++) {
                this.waterDieSelfprotection.add(new WaterDieSelfprotection(100));
            }
            this.mixingProcessor = new MixingProcessor(this.waterCannonRoof, this.waterCannonFront, this.waterDieSelfprotection, this.foamTank, this.waterTank);
        }

        private Joystick buildJoystick(Boolean isDriver, JoystickType joystickType) {
            Joystick joystick = null;
            ButtonPress btnPressLeft;
            ButtonPress btnPressRight;
            ButtonPush btnPush;
            ButtonPress btnPress;
            CannonIdentifier ident = isDriver ? CannonIdentifier.CANNON_FRONT : CannonIdentifier.CANNON_ROOF;

            switch (joystickType) {
                case CLASSIC -> {
                    btnPressLeft = new ButtonPress(this.mixingProcessor) {
                        @Override
                        public void operateDevice() {
                            ((MixingProcessor) this.operatingDevice).toggle(ident);
                        }
                    };
                    btnPressRight = new ButtonPress(this.mixingProcessor) {
                        @Override
                        public void operateDevice() {
                            if (((MixingProcessor) this.operatingDevice).getCannonState(ident)) {
                                ((MixingUnitMediator) this.operatingDevice).changeMixingRate();
                            }
                        }
                    };
                    btnPush = new ButtonPush(this.mixingProcessor) {
                        @Override
                        public void operateDevice() {
                            if (((MixingProcessor) this.operatingDevice).getCannonState(ident)) {
                                ((MixingProcessor) this.operatingDevice).spray(ident);
                            }
                        }
                    };
                    joystick = new Joystick(JoystickType.CLASSIC, btnPush, btnPressLeft, btnPressRight);
                }
                case INTELLIGENT -> {
                    btnPress = new ButtonPress(this.mixingProcessor) {
                        @Override
                        public void operateDevice() {
                            if (isHeld5seconds()) {
                                ((MixingProcessor) this.operatingDevice).toggle(ident);
                            } else if (hold5sec()) {
                                ((MixingProcessor) this.operatingDevice).toggle(ident);
                            }
                            if (((MixingProcessor) this.operatingDevice).getCannonState(ident)) {
                                ((MixingProcessor) this.operatingDevice).changeMixingRate();
                            }
                        }
                    };
                    btnPush = new ButtonPush(this.mixingProcessor) {
                        @Override
                        public void operateDevice() {
                            if (((MixingProcessor) this.operatingDevice).getCannonState(ident)) {
                                ((MixingProcessor) this.operatingDevice).spray(ident);
                            }
                        }
                    };
                    joystick = new Joystick(JoystickType.INTELLIGENT, btnPush, btnPress);
                }
            }
            return joystick;
        }

        private ControlPanel buildControlPanel(CentralUnit cu) {
            List<ButtonSwitch> switches = new ArrayList<>();
            ButtonSwitch btnWarnLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchWarningLight();
                }
            };

            ButtonSwitch btnBlueLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchBlueLight();
                }
            };

            ButtonSwitch btnFrontLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchFrontLight();
                }
            };

            ButtonSwitch btnRoofLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchRoofLight();
                }
            };

            ButtonSwitch btnSideLight = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchSideLight();
                }
            };

            ButtonSwitch btnSelfProtection = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchSelfprotection();
                }
            };

            ButtonSwitch btnEngines = new ButtonSwitch(cu) {
                @Override
                public void operateDevice() {
                    ((CentralUnit) this.operatingDevice).switchEngines();
                }
            };

            return new ControlPanel.Builder(btnEngines, btnWarnLight, btnBlueLight, btnFrontLight, btnRoofLight, btnSideLight, btnSelfProtection, waterTankSensorLED, foamTankSensorLED).build();
        }

        /**
         * @return FLF.FLF
         */
        public FLF build() {
            return new FLF(this);
        }


    }
}