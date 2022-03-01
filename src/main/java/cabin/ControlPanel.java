package cabin;

import button.ButtonSwitch;


public class ControlPanel {
    private final ButtonSwitch btnSwitchEngines;
    private final ButtonSwitch btnSwitchWarnlight;
    private final ButtonSwitch btnSwitchBluelight;
    private final ButtonSwitch btnSwitchFrontlight;
    private final ButtonSwitch btnSwitchRooflight;
    private final ButtonSwitch btnSwitchSidelight;
    private final ButtonSwitch btnSwitchSelfProtection;

    public ControlPanel(Builder builder) {
        //ControlPanel built = builder.build();
        this.btnSwitchEngines = builder.btnSwitchEngines;
        this.btnSwitchWarnlight = builder.btnSwitchWarnlight;
        this.btnSwitchBluelight = builder.btnSwitchBluelight;
        this.btnSwitchFrontlight = builder.btnSwitchFrontlight;
        this.btnSwitchRooflight = builder.btnSwitchRooflight;
        this.btnSwitchSidelight = builder.btnSwitchSidelight;
        this.btnSwitchSelfProtection = builder.btnSwitchSelfProtection;

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

        public Builder(
                ButtonSwitch btnSwitchEngines,
                ButtonSwitch btnSwitchWarnlight,
                ButtonSwitch btnSwitchBluelight,
                ButtonSwitch btnSwitchFrontlight,
                ButtonSwitch btnSwitchRooflight,
                ButtonSwitch btnSwitchSidelight,
                ButtonSwitch btnSwitchSelfProtection
        ) {
            this.btnSwitchEngines = btnSwitchEngines;
            this.btnSwitchWarnlight = btnSwitchWarnlight;
            this.btnSwitchBluelight = btnSwitchBluelight;
            this.btnSwitchFrontlight = btnSwitchFrontlight;
            this.btnSwitchRooflight = btnSwitchRooflight;
            this.btnSwitchSidelight = btnSwitchSidelight;
            this.btnSwitchSelfProtection = btnSwitchSelfProtection;
        }

        public ControlPanel build() {
            return new ControlPanel(this);
        }
    }

}
