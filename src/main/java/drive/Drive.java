package drive;

import batteryManagement.BatteryManagement;
import batteryManagement.Coulomb;
import task5.AdapterSocket;
import task5.ICharger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import task2.centralUnitUtils.Subscriber;

public class Drive extends Subscriber{

    private final List<ElectricEngine> engines = new ArrayList<>();
    private final List<AxleSteerable> steerables = new ArrayList<>();
    private final List<Axle> axles = new ArrayList<>();
    private final BatteryManagement batteryManagement = BatteryManagement.instance;
    private Integer speed = 0;

    private final ICharger adapterSocket = new AdapterSocket(batteryManagement);

    public Drive() {
        super(001);
        for (int i = 0; i < 2; i++) {
            engines.add(new ElectricEngine(2));
            steerables.add(new AxleSteerable());
            axles.add(new Axle());
        }
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer brake() {
        if (this.speed == 0) return this.speed;
        Double calc = Double.valueOf(this.speed);
        for (Axle a : Stream.concat(steerables.stream(), axles.stream()).toList()) {
            calc += a.brake();
        }
        this.speed = calc.intValue();
        return speed;
    }

    public Integer accelerate() {
        for (ElectricEngine e : engines) {
            this.speed += e.accelerate();
        }
        return this.speed;
    }

    public Integer drive() {
        Integer eAmount = this.speed * 25;
        List<Coulomb> energy = this.batteryManagement.remove(eAmount);
        for (ElectricEngine e : engines) {
            e.drive(energy);
        }
        return this.speed;
    }

    public void toggleEngine() {
        for (ElectricEngine e : engines) {
            e.toggle();
        }
    }

    public void steer(Integer degree) {
        for (AxleSteerable as : steerables) {
            as.steer(degree);
        }
    }

    public Integer getSteeringAngle() {
        return this.steerables.get(0).getSteeringAngle();
    }

    public Boolean getEngineState() {
        return this.engines.get(0).getState();
    }

    public void fillComplete() {
        this.batteryManagement.fillComplete();
    }

    public Double getRelativeFillState() {
        return this.batteryManagement.getRelativeFillState();
    }

    public Integer getAbsoluteFillState() {
        return batteryManagement.getAbsoluteFillState();
    }

    public Integer getCapacity() {
        return batteryManagement.getCapacity();
    }

    public void load1000(){adapterSocket.loadonepole(1000);}

}
