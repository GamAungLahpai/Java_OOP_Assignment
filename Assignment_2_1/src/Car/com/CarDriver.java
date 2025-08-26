package Car.com;

public class CarDriver {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota Corolla", 80, 0); // using constructor with fuel + speed
        myCar.fillTank();

        // Normal acceleration
        for (int i = 0; i < 6; i++) {
            myCar.accelerate();
            System.out.println(myCar.getTypeName() + ": speed is " + myCar.getSpeed() + " km/h");
        }

        // === Test Cruise Control ===
        System.out.println("\nTesting cruise control...");

        myCar.setTargetSpeed(50);                          // set target speed
        boolean cruiseOn = myCar.turnOnCruiseControl();    // try to enable

        if (cruiseOn) {
            System.out.println("Cruise control ON. Target speed: " + myCar.getTargetSpeed());

            // Simulate driving while cruise control adjusts speed
            for (int i = 0; i < 5; i++) {
                myCar.updateCruiseControl();   // car adjusts toward target
                System.out.println("Current speed: " + myCar.getSpeed() + " km/h");
            }
        } else {
            System.out.println("Cruise control could not be turned ON.");
        }

        myCar.turnOffCruiseControl();
        System.out.println("Cruise control OFF.");

        // Normal deceleration afterwards
        while (myCar.getSpeed() > 0) {
            myCar.decelerate(15);
            System.out.println(myCar.getTypeName() + ": speed is " + myCar.getSpeed() + " km/h");
        }
    }
}
