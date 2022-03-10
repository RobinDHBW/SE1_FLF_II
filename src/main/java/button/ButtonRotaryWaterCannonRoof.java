package button;

import tank.PipeDistribution;

public class ButtonRotaryWaterCannonRoof extends ButtonRotary {
    private RoofCannonMode mode = RoofCannonMode.A;

    public ButtonRotaryWaterCannonRoof(PipeDistribution o) {
        super(o, 500);

    }

    public RoofCannonMode getMode() {
        return mode;
    }

    private void setParam(Integer amount, RoofCannonMode mode) {
        this.mode = mode;
        this.amountPerIteration = amount;
        this.operateDevice();
    }

    @Override
    public void rotateLeft() {
        switch (mode) {
            case C -> this.setParam(1000, RoofCannonMode.B);
            case A, B -> this.setParam(500, RoofCannonMode.A);
        }
    }

    @Override
    public void rotateRight() {
        switch (mode) {
            case A -> this.setParam(1000, RoofCannonMode.B);
            case B, C -> this.setParam(2500, RoofCannonMode.C);
        }
    }
}
