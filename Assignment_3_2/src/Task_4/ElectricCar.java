package Task_4;

class ElectricCar extends AbstractVehicle {
    private int maxRange;  // Maximum range in kilometers

    public ElectricCar() {
        super("Electric Car", "Electric", "White", 0.2); // Fixed: Added fuel efficiency parameter
        this.batteryLevel = 85;  // Start with 85% battery
        this.maxRange = 400;     // 400km range
    }

    /**
     * Override charge method for electric vehicles
     * Electric vehicles CAN be charged
     */
    @Override
    public void charge() {
        System.out.println("Charging " + type + "...");
        setBatteryLevel(100);  // Charge to 100%
        System.out.println(type + " fully charged! Battery: " + getBatteryLevel() + "%");
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color + "\n" +
                "Efficiency: " + calculateFuelEfficiency() + " kWh/km\n" +
                "Battery Level: " + getBatteryLevel() + "%\n" +
                "Max Range: " + maxRange + " km";
    }
}
