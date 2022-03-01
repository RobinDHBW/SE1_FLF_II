package drive;

import java.util.ArrayList;
import java.util.List;

public class Axle {

    private final List<DiscBrake> brakes = new ArrayList<>();

    public Axle() {
        for (int i = 0; i < 2; i++) {
            List<Tire> tires = new ArrayList<>();
            tires.add(new Tire());
        }
        for (int i = 0; i < 6; i++) {
            brakes.add(new DiscBrake(4.0 / 24));
        }
    }

    public Double brake() {
        Double brakePower = 0.0;
        for (DiscBrake b : brakes) {
            brakePower += b.brake();
        }
        return brakePower;
    }
}
