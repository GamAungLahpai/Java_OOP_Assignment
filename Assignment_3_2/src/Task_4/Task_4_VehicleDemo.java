package Task_4;

public class Task_4_VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        // Create traditional vehicles
        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle bus = new Bus();

        // Create electric vehicles
        Vehicle electricCar = new ElectricCar();
        Vehicle electricMotorcycle = new ElectricMotorcycle();

        // Test traditional vehicles
        car.start();
        car.stop();
        System.out.println(car.getInfo());
        System.out.println();

        motorcycle.start();
        motorcycle.stop();
        System.out.println(motorcycle.getInfo());
        System.out.println();

        bus.start();
        bus.stop();
        System.out.println(bus.getInfo());
        System.out.println();

        // Test electric vehicles
        electricCar.start();
        electricCar.stop();
        System.out.println(electricCar.getInfo());
        System.out.println();

        electricMotorcycle.start();
        electricMotorcycle.stop();
        System.out.println(electricMotorcycle.getInfo());
        System.out.println();

        // Task 3
        System.out.println("Charging Test:");
        ((AbstractVehicle) car).charge();
        ((AbstractVehicle) electricCar).charge();

        // NEW: Task 4 - Fuel efficiency comparison
        System.out.println("\nFuel Efficiency Comparison:");
        System.out.println("Car: " + car.calculateFuelEfficiency() + " km/L");
        System.out.println("Motorcycle: " + motorcycle.calculateFuelEfficiency() + " km/L");
        System.out.println("Bus: " + bus.calculateFuelEfficiency() + " km/L");
        System.out.println("Electric Car: " + electricCar.calculateFuelEfficiency() + " kWh/km");
        System.out.println("Electric Motorcycle: " + electricMotorcycle.calculateFuelEfficiency() + " kWh/km");
    }
}