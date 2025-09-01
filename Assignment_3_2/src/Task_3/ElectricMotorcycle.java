package Task_3;

class ElectricMotorcycle extends AbstractVehicle {
    private int maxRange;

    public ElectricMotorcycle() {
        super("Electric Motorcycle", "Electric", "Green");
        this.batteryLevel = 60;  // Start with 60% battery
        this.maxRange = 150;     // 150km range
    }

    // Override charge method for electric vehicles
    @Override
    public void charge() {
        System.out.println("Charging " + type + "...");
        setBatteryLevel(100);
        System.out.println(type + " fully charged! Battery: " + getBatteryLevel() + "%");
    }

    @Override
    public String getSpecificInfo() {
        return "Type: " + type + "\n" +
                "Fuel: " + fuel + "\n" +
                "Color: " + color + "\n" +
                "Battery Level: " + getBatteryLevel() + "%\n" +
                "Max Range: " + maxRange + " km";
    }
}
