package Task_4;

class Motorcycle extends AbstractVehicle {
    public Motorcycle() {
        super("Motorcycle", "Gasoline", "Black", 35.0); // Fixed: Added fuel efficiency parameter
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
