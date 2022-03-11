package cabin;

import lights.LEDLight;
import task7.ButtonSwitchTask7;


public class ControlPanel {
    private final ButtonSwitchTask7 btnSwitchEngines;
    private final ButtonSwitchTask7 btnSwitchWarnlight;
    private final ButtonSwitchTask7 btnSwitchBluelight;
    private final ButtonSwitchTask7 btnSwitchFrontlight;
    private final ButtonSwitchTask7 btnSwitchRooflight;
    private final ButtonSwitchTask7 btnSwitchSidelight;
    private final ButtonSwitchTask7 btnSwitchSelfProtection;
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

    public ButtonSwitchTask7 getBtnSwitchEngines() {return btnSwitchEngines;}

    public ButtonSwitchTask7 getBtnSwitchWarnlight() {
        return btnSwitchWarnlight;
    }

    public ButtonSwitchTask7 getBtnSwitchBluelight() {
        return btnSwitchBluelight;
    }

    public ButtonSwitchTask7 getBtnSwitchFrontlight() {
        return btnSwitchFrontlight;
    }

    public ButtonSwitchTask7 getBtnSwitchRooflight() {
        return btnSwitchRooflight;
    }

    public ButtonSwitchTask7 getBtnSwitchSidelight() {
        return btnSwitchSidelight;
    }

    public ButtonSwitchTask7 getBtnSwitchSelfProtection() {
        return btnSwitchSelfProtection;}

    public LEDLight getWaterTankSensorLED() {
        return waterTankSensorLED;
    }

    public LEDLight getFoamTankSensorLED() {
        return foamTankSensorLED;
    }

    public static class Builder {
        private final ButtonSwitchTask7 btnSwitchEngines;
        private final ButtonSwitchTask7 btnSwitchWarnlight;
        private final ButtonSwitchTask7 btnSwitchBluelight;
        private final ButtonSwitchTask7 btnSwitchFrontlight;
        private final ButtonSwitchTask7 btnSwitchRooflight;
        private final ButtonSwitchTask7 btnSwitchSidelight;
        private final ButtonSwitchTask7 btnSwitchSelfProtection;
        private final LEDLight waterTankSensorLED;
        private final LEDLight foamTankSensorLED;

        public Builder(
                ButtonSwitchTask7 btnSwitchEngines,
                ButtonSwitchTask7 btnSwitchWarnlight,
                ButtonSwitchTask7 btnSwitchBluelight,
                ButtonSwitchTask7 btnSwitchFrontlight,
                ButtonSwitchTask7 btnSwitchRooflight,
                ButtonSwitchTask7 btnSwitchSidelight,
                ButtonSwitchTask7 btnSwitchSelfProtection,
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
