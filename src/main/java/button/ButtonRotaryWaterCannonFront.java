package button;

import tank.MixingProcessor;
import task1_imp.MixingUnitMediator;

public class ButtonRotaryWaterCannonFront extends ButtonRotary {
    private final Integer stepSize = 500;
    private Integer mode = 1;

    public ButtonRotaryWaterCannonFront(MixingProcessor o) {
        super(o, 500);
    }

    @Override
    public void rotateLeft() {
        if (amountPerIteration > 500 && mode > 1) {
            amountPerIteration -= stepSize;
            mode -= 1;
        }
        this.operateDevice();
    }

    @Override
    public void rotateRight() {
        if (amountPerIteration < 3500 && mode < 7) {
            amountPerIteration += stepSize;
            mode += 1;
        }
        this.operateDevice();
    }

    public Integer getMode() {
        return mode;
    }
}
