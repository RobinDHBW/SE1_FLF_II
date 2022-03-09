package utilities;

public class WaterCannonRoof extends WaterCannon {

    private final SegmentMovable segmentMovable;

    public WaterCannonRoof() {
        BranchSegment segment = new BranchSegment(3, new Integer[]{6, 6, 5});
        this.segmentMovable = new SegmentMovable(90);
    }

    @Override
    public void toggle() {
        super.toggle();
        this.segmentMovable.move(this.state);
    }
}
