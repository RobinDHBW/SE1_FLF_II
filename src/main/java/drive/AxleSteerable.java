package drive;

public class AxleSteerable extends Axle implements IAxleSteerable {
    private Integer steeringAngle = 0;

    public AxleSteerable() {

    }

    @Override
    public void steer(Integer degree) {
        this.steeringAngle = degree;
    }

    public Integer getSteeringAngle() {
        return steeringAngle;
    }
}
