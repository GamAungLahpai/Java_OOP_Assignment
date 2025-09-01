package Task_4;

class Car extends AbstractVehicle {
    public Car() {
        super("Car", "Petrol", "Red", 15.0); // Fixed: Added fuel efficiency parameter
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color + "\n" +
                "Fuel Efficiency: " + calculateFuelEfficiency() + " km/L\n" +
                "Battery Level: " + getBatteryLevel() + "% (N/A - not electric)";
    }
}