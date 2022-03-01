package drive;

public class DiscBrake {
    private final Double stepSize;

    public DiscBrake(Double stepSize) {
        this.stepSize = stepSize;
    }

    public Double brake() {
        return this.stepSize * -1;
    }
}
