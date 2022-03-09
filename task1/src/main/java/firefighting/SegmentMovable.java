package firefighting;

public class SegmentMovable extends Segment {
    Integer rotationStepSize, position = 0;


    public SegmentMovable(Integer rotationStepSize) {
        this.rotationStepSize = rotationStepSize;
    }

    public void move(Boolean moveUp) {
        this.position += moveUp ? this.rotationStepSize : this.rotationStepSize * -1;
    }

    public Integer getPosition() {
        return position;
    }
}
