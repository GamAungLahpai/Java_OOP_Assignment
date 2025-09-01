package Task_3;

class Car extends AbstractVehicle {
    public Car() {
        super("Car", "Petrol", "Red");
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color + "\n" +
                "Battery Level: " + getBatteryLevel() + "% (N/A - not electric)";
    }
}
