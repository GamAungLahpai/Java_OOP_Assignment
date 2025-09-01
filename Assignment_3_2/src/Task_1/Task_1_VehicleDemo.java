package Task_1;

public class Task_1_VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Vehicle Demonstration\n");

        // Create instances of each vehicle type
        // Polymorphism: Vehicle reference can point to any implementing class
        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle bus = new Bus();

        // Demonstrate Car - calls Car's specific implementations
        car.start();
        car.stop();
        System.out.println(car.getInfo());
        System.out.println();

        // Demonstrate Motorcycle - calls Motorcycle's specific implementations
        motorcycle.start();
        motorcycle.stop();
        System.out.println(motorcycle.getInfo());
        System.out.println();

        // Demonstrate Bus - calls Bus's specific implementations
        bus.start();
        bus.stop();
        System.out.println(bus.getInfo());
        System.out.println();

    }
}
