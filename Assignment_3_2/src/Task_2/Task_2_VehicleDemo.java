package Task_2;
// Task 2: Task_2.Vehicle Inheritance Hierarchy
// Single file implementation showing Interface -> Abstract Class -> Concrete Classes


// Task_2.Vehicle interface - Defines a contract that all vehicle types must follow
interface Vehicle {
    void start();

    void stop();

    String getInfo();
}


/*
 * Task_2.AbstractVehicle - Abstract class that implements Task_2.Vehicle interface
 * Provides shared attributes and common functionality for all vehicles
 */
abstract class AbstractVehicle implements Vehicle {
    // Protected = accessible by subclasses
    protected String type;
    protected String fuel;
    protected String color;
    protected boolean isRunning;  // Shared state for all vehicles


     // Constructor for Task_2.AbstractVehicle

    public AbstractVehicle(String type, String fuel, String color) {
        this.type = type;
        this.fuel = fuel;
        this.color = color;
        this.isRunning = false;  // Initially stopped
    }


      // Concrete method - all subclasses inherit this

    public boolean isRunning() {
        return isRunning;
    }


     // Common implementation of start()

    @Override
    public void start() {
        System.out.println(type + " is starting...");
        isRunning = true;
    }


     // Common implementation of stop()

    @Override
    public void stop() {
        System.out.println(type + " is stopping...");
        isRunning = false;
    }


     // Abstract method - subclasses MUST provide their own implementation

    public abstract String getSpecificInfo();


     // Default getInfo() implementation using template method pattern

    @Override
    public String getInfo() {
        return type + " Information:\n" + getSpecificInfo();
    }
}


  // Task_2.Car class - extends Task_2.AbstractVehicle

class Car extends AbstractVehicle {
    public Car() {
        super("Task_2.Car", "Petrol", "Red");
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color;
    }
}


  // Task_2.Motorcycle class - extends Task_2.AbstractVehicle

class Motorcycle extends AbstractVehicle {
    public Motorcycle() {
        super("Task_2.Motorcycle", "Gasoline", "Black");
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color;
    }
}


  // Task_2.Bus class - extends Task_2.AbstractVehicle

class Bus extends AbstractVehicle {
    private String capacity;

    public Bus() {
        super("Task_2.Bus", "Diesel", "Blue");
        this.capacity = "40 passengers";
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Capacity: " + capacity;
    }
}


 // Main demonstration class

public class Task_2_VehicleDemo {
    public static void main(String[] args) {
        System.out.println("Task_2.Vehicle Demonstration\n");

        // Create instances
        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();
        Vehicle bus = new Bus();

        // Demonstrate Task_2.Car
        car.start();
        car.stop();
        System.out.println(car.getInfo());
        System.out.println();

        // Demonstrate Task_2.Motorcycle
        motorcycle.start();
        motorcycle.stop();
        System.out.println(motorcycle.getInfo());
        System.out.println();

        // Demonstrate Task_2.Bus
        bus.start();
        bus.stop();
        System.out.println(bus.getInfo());
        System.out.println();

        // Cast Task_2.Vehicle -> Task_2.AbstractVehicle to access isRunning() method which is not available in Task_2.Vehicle interface
        System.out.println("Inheritance Demonstration:");
        System.out.println("Task_2.Car running status: " + ((AbstractVehicle) car).isRunning());
        System.out.println("Task_2.Bus running status: " + ((AbstractVehicle) bus).isRunning());
        System.out.println("Task_2.Motorcycle running status: " + ((AbstractVehicle) motorcycle).isRunning());
    }
}