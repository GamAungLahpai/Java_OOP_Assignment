package Task_3;

abstract class AbstractVehicle implements Vehicle, ElectricVehicle {
    // Common attributes for all vehicles
    protected String type;
    protected String fuel;
    protected String color;
    protected boolean isRunning;
    protected int batteryLevel;  // New: battery level for all vehicles


     // Constructor for AbstractVehicle

    public AbstractVehicle(String type, String fuel, String color) {
        this.type = type;
        this.fuel = fuel;
        this.color = color;
        this.isRunning = false;
        this.batteryLevel = 0;  // Non-electric vehicles have 0% battery
    }


    //  Common start implementation
    @Override
    public void start() {
        System.out.println(type + " is starting...");
        isRunning = true;
    }


    //  Common stop implementation

    @Override
    public void stop() {
        System.out.println(type + " is stopping...");
        isRunning = false;
    }


    // Common isRunning method

    public boolean isRunning() {
        return isRunning;
    }


     // Default charge implementation for non-electric vehicles
     // Electric vehicles will override this method

    @Override
    public void charge() {
        System.out.println("No possible to charge " + type + " - not an electric vehicle.");
    }


     // Default battery level for non-electric vehicles

    @Override
    public int getBatteryLevel() {
        return batteryLevel;  // Will be 0 for non-electric vehicles
    }

    // Default battery setter
    @Override
    public void setBatteryLevel(int level) {
        if (level >= 0 && level <= 100) {
            this.batteryLevel = level;
        }
    }

    // Abstract method - each vehicle provides specific info
    public abstract String getSpecificInfo();

    // Template method for getInfo()
    @Override
    public String getInfo() {
        return type + " Information:\n" + getSpecificInfo();
    }
}
