package Task_3;

class Motorcycle extends AbstractVehicle {
    public Motorcycle() {
        super("Motorcycle", "Gasoline", "Black");
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color + "\n" +
                "Battery Level: " + getBatteryLevel() + "% (N/A - not electric)";
    }
}
