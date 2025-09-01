package Task_1;

//Car class - Concrete implementation of Vehicle interface
public class Car implements Vehicle {
    // Private fields, Encapsulation (data hiding principle)
    private String type;
    private String fuel;
    private String color;

    // Constructor = Special method that runs when object is created
    public Car() {
        this.type = "Car";
        this.fuel = "Petrol";
        this.color = "Red";
    }

    //@Override = Annotation telling compiler "this method implements interface method"
    @Override
    public void start() {
        System.out.println("Car is starting... ");

    }

    @Override
    public void stop() {
        System.out.println("Car is stopping... ");

    }

    @Override
    public String getInfo() {
        // Polymorphism: Same method name, different behavior for each vehicle type
        return "Car Information:\n" +
                "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color;
    }
}
