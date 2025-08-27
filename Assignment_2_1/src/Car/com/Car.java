package Car.com;

public class Car {
    private double speed;
    private double gasolineLevel;
    private String typeName;

    // === Cruise Control variables ===
    private boolean cruiseControlOn;
    private double targetSpeed;
    private static final double MIN_CRUISE_SPEED = 30;
    private static final double MAX_CRUISE_SPEED = 180;

    // Existing constructor
    public Car(String typeName) {
        speed = 0;
        gasolineLevel = 0;
        this.typeName = typeName;
        this.cruiseControlOn = false;
    }

    // New constructor
    public Car(String typeName, double gasolineLevel, double speed) {
        this.typeName = typeName;
        this.gasolineLevel = gasolineLevel;
        this.speed = speed;
        this.cruiseControlOn = false;

    }

    // Normal car methods
    public void accelerate() {
        if (gasolineLevel > 0)
            speed += 10;
        else
            speed = 0;
    }

    void decelerate(int amount) {
        if (gasolineLevel > 0) {
            if (amount > 0)
                speed = Math.max(0, speed - amount);
        } else
            speed = 0;
    }

    double getSpeed() {
        return speed;
    }

    String getTypeName() {
        return typeName;
    }

    void fillTank() {
        gasolineLevel = 100;
    }

    double getGasolineLevel() {
        return gasolineLevel;
    }

    // === Cruise Control Methods ===
    public void setTargetSpeed(double target) {
        this.targetSpeed = target;
    }

    public double getTargetSpeed() {
        return targetSpeed;
    }

    public boolean turnOnCruiseControl() {
        if (gasolineLevel <= 0) {
            cruiseControlOn = false;
            return false;
        }
        if (targetSpeed < MIN_CRUISE_SPEED || targetSpeed > MAX_CRUISE_SPEED) {
            cruiseControlOn = false;
            return false;
        }
        cruiseControlOn = true;
        return true;
    }

    public void turnOffCruiseControl() {
        cruiseControlOn = false;
    }

    // This simulates cruise control adjusting speed each tick
    public void updateCruiseControl() {
        if (!cruiseControlOn) return;

        if (gasolineLevel <= 0) {
            cruiseControlOn = false;
            System.out.println("Cruise control OFF (out of fuel).");
            return;
        }

        if (Math.abs(speed - targetSpeed) < 1) {
            speed = targetSpeed;  // close enough
        } else if (speed < targetSpeed) {
            accelerate();
        } else {
            decelerate(5);
        }

        // Safety check: if cannot reach target, turn off
        if ((targetSpeed > MAX_CRUISE_SPEED || targetSpeed < MIN_CRUISE_SPEED)) {
            cruiseControlOn = false;
            System.out.println("Cruise control OFF (invalid target speed).");
        }
    }
}
