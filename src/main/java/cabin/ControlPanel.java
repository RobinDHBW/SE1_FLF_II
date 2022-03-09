package cabin;

import button.ButtonSwitch;
import lights.LEDLight;

import java.util.List;


public class ControlPanel {
    private final ButtonSwitch btnSwitchEngines;
    private final ButtonSwitch btnSwitchWarnlight;
    private final ButtonSwitch btnSwitchBluelight;
    private final ButtonSwitch btnSwitchFrontlight;
    private final ButtonSwitch btnSwitchRooflight;
    private final ButtonSwitch btnSwitchSidelight;
    private final ButtonSwitch btnSwitchSelfProtection;
    private final LEDLight waterTankSensorLED;
    private final LEDLight foamTankSensorLED;

    public ControlPanel(Builder builder) {
        //ControlPanel built = builder.build();
        this.btnSwitchEngines = builder.btnSwitchEngines;
        this.btnSwitchWarnlight = builder.btnSwitchWarnlight;
        this.btnSwitchBluelight = builder.btnSwitchBluelight;
        this.btnSwitchFrontlight = builder.btnSwitchFrontlight;
        this.btnSwitchRooflight = builder.btnSwitchRooflight;
        this.btnSwitchSidelight = builder.btnSwitchSidelight;
        this.btnSwitchSelfProtection = builder.btnSwitchSelfProtection;
        this.waterTankSensorLED = builder.waterTankSensorLED;
        this.foamTankSensorLED = builder.foamTankSensorLED;

    }

    /**********
     * Getter
     *********/

    public ButtonSwitch getBtnSwitchEngines() {
        return btnSwitchEngines;
    }

    public ButtonSwitch getBtnSwitchWarnlight() {
        return btnSwitchWarnlight;
    }

    public ButtonSwitch getBtnSwitchBluelight() {
        return btnSwitchBluelight;
    }

    public ButtonSwitch getBtnSwitchFrontlight() {
        return btnSwitchFrontlight;
    }

    public ButtonSwitch getBtnSwitchRooflight() {
        return btnSwitchRooflight;
    }

    public ButtonSwitch getBtnSwitchSidelight() {
        return btnSwitchSidelight;
    }

    public ButtonSwitch getBtnSwitchSelfProtection() {
        return btnSwitchSelfProtection;
    }

    public LEDLight getWaterTankSensorLED() {
        return waterTankSensorLED;
    }

    public LEDLight getFoamTankSensorLED() {
        return foamTankSensorLED;
    }

    public static class Builder {
        private final ButtonSwitch btnSwitchEngines;
        private final ButtonSwitch btnSwitchWarnlight;
        private final ButtonSwitch btnSwitchBluelight;
        private final ButtonSwitch btnSwitchFrontlight;
        private final ButtonSwitch btnSwitchRooflight;
        private final ButtonSwitch btnSwitchSidelight;
        private final ButtonSwitch btnSwitchSelfProtection;
        private final LEDLight waterTankSensorLED;
        private final LEDLight foamTankSensorLED;

        public Builder(
                ButtonSwitch btnSwitchEngines,
                ButtonSwitch btnSwitchWarnlight,
                ButtonSwitch btnSwitchBluelight,
                ButtonSwitch btnSwitchFrontlight,
                ButtonSwitch btnSwitchRooflight,
                ButtonSwitch btnSwitchSidelight,
                ButtonSwitch btnSwitchSelfProtection,
                LEDLight waterTankSensorLED,
                LEDLight foamTankSensorLED
        ) {
            this.btnSwitchEngines = btnSwitchEngines;
            this.btnSwitchWarnlight = btnSwitchWarnlight;
            this.btnSwitchBluelight = btnSwitchBluelight;
            this.btnSwitchFrontlight = btnSwitchFrontlight;
            this.btnSwitchRooflight = btnSwitchRooflight;
            this.btnSwitchSidelight = btnSwitchSidelight;
            this.btnSwitchSelfProtection = btnSwitchSelfProtection;
            this.waterTankSensorLED = waterTankSensorLED;
            this.foamTankSensorLED = foamTankSensorLED;
        }

        public ControlPanel build() {
            return new ControlPanel(this);
        }
    }

}
