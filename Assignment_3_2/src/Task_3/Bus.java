package Task_3;

class Bus extends AbstractVehicle {
    private String capacity;

    public Bus() {
        super("Bus", "Diesel", "Blue");
        this.capacity = "40 passengers";
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Capacity: " + capacity + "\n" +
                "Battery Level: " + getBatteryLevel() + "% (N/A - not electric)";
    }
}


