package Task_1;

//Motorcycle class - Another concrete implementation of Vehicle interface
public class Motorcycle implements Vehicle {

    // Private fields = Encapsulation (data hiding principle)
    private String type;
    private String fuel;
    private String color;

    // Constructor
    public Motorcycle(){
        this.type = "Motorcycle";
        this.fuel = "Gasoline";
        this.color = "Black";
    }


    @Override
    public void start(){
    // Same method signature, different message = Method Overriding
        System.out.println("Motorcycle is starting... ");

    }

    @Override
    public void stop() {
        System.out.println("Motorcycle is stopping... ");

    }

    @Override
    public String getInfo() {
    // Same return type and purpose, different content
        return "Motorcycle Information:\n" +
                "Type" + type + "\n" +
                "Fuel" + fuel + "\n" +
                "Color" + color;
    }
}
