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
    private final List<LEDLight> tankSensorLEDs;

    public ControlPanel(Builder builder) {
        //ControlPanel built = builder.build();
        this.btnSwitchEngines = builder.btnSwitchEngines;
        this.btnSwitchWarnlight = builder.btnSwitchWarnlight;
        this.btnSwitchBluelight = builder.btnSwitchBluelight;
        this.btnSwitchFrontlight = builder.btnSwitchFrontlight;
        this.btnSwitchRooflight = builder.btnSwitchRooflight;
        this.btnSwitchSidelight = builder.btnSwitchSidelight;
        this.btnSwitchSelfProtection = builder.btnSwitchSelfProtection;
        this.tankSensorLEDs = builder.tankSensorLEDs;

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

    public static class Builder {
        private final ButtonSwitch btnSwitchEngines;
        private final ButtonSwitch btnSwitchWarnlight;
        private final ButtonSwitch btnSwitchBluelight;
        private final ButtonSwitch btnSwitchFrontlight;
        private final ButtonSwitch btnSwitchRooflight;
        private final ButtonSwitch btnSwitchSidelight;
        private final ButtonSwitch btnSwitchSelfProtection;
        private final List<LEDLight> tankSensorLEDs;

        public Builder(
                ButtonSwitch btnSwitchEngines,
                ButtonSwitch btnSwitchWarnlight,
                ButtonSwitch btnSwitchBluelight,
                ButtonSwitch btnSwitchFrontlight,
                ButtonSwitch btnSwitchRooflight,
                ButtonSwitch btnSwitchSidelight,
                ButtonSwitch btnSwitchSelfProtection,
                List<LEDLight> tankSensorLEDs
        ) {
            this.btnSwitchEngines = btnSwitchEngines;
            this.btnSwitchWarnlight = btnSwitchWarnlight;
            this.btnSwitchBluelight = btnSwitchBluelight;
            this.btnSwitchFrontlight = btnSwitchFrontlight;
            this.btnSwitchRooflight = btnSwitchRooflight;
            this.btnSwitchSidelight = btnSwitchSidelight;
            this.btnSwitchSelfProtection = btnSwitchSelfProtection;
            this.tankSensorLEDs = tankSensorLEDs;
        }

        public ControlPanel build() {
            return new ControlPanel(this);
        }
    }

}
