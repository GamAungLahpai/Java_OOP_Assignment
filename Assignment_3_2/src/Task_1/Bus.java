package Task_1;

class Bus implements Vehicle {
    private String type;
    private String fuel;
    private String capacity;  // Bus has capacity instead of color

    public Bus() {
        this.type = "Bus";
        this.fuel = "Diesel";      // Different fuel type
        this.capacity = "40 passengers";  // Unique property for Bus
    }

    @Override
    public void start() {
        System.out.println("Bus is starting...");
    }

    @Override
    public void stop() {
        System.out.println("Bus is stopping...");
    }

    @Override
    public String getInfo() {
        // Same method signature, different information structure
        return "Bus Information:\n" +
                "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Capacity: " + capacity;
    }
}
