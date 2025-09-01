package Task_3;

public class Task3_VehicleDemo {
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

        // Test charging - key requirement for Task 3
        System.out.println("Charging Test:");
        ((AbstractVehicle) car).charge();
        ((AbstractVehicle) bus).charge();
        ((AbstractVehicle) motorcycle).charge();
        ((AbstractVehicle) electricCar).charge();
        ((AbstractVehicle) electricMotorcycle).charge();
    }
}