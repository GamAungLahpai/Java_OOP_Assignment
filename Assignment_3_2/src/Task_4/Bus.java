package Task_4;

class Bus extends AbstractVehicle {
    private String capacity;

    public Bus() {
        super("Bus", "Diesel", "Blue", 4.0); // Fixed: Added fuel efficiency parameter
        this.capacity = "40 passengers";
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Capacity: " + capacity + "\n" +
                "Fuel Efficiency: " + calculateFuelEfficiency() + " km/L\n" +
                "Battery Level: " + getBatteryLevel() + "% (N/A - not electric)";
    }
}
