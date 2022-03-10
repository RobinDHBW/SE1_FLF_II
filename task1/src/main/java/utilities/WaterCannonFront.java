package utilities;

public class WaterCannonFront extends WaterCannon {
    private final Integer rotationStepSize;
    private Integer position = 0;

    public WaterCannonFront(Integer rotationStepSize) {
        this.rotationStepSize = rotationStepSize;
    }

    @Override
    public void toggle() {
        for (int i = 0; i < (this.state ? this.position / this.rotationStepSize : 1); i++) {
            if (this.state) {
                rotateLeft();
            } else {
                rotateRight();
            }
        }

        super.toggle();
    }

    public void rotateLeft() {
        if (position > 0) position -= rotationStepSize;
    }


    public void rotateRight() {
        if (position < 180) position += rotationStepSize;
    }

    public Integer getPosition() {
        return position;
    }
}
