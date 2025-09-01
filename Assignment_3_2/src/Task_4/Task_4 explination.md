# Task 4: Fuel Efficiency Calculation

## What I Did
Added fuel efficiency calculation to all vehicles. Each vehicle now stores and returns its fuel efficiency value through a new method in the Vehicle interface.

## Problem
The Vehicle interface needed a way for all vehicles to report their fuel efficiency, but different vehicle types use different units and have different efficiency values.

## Solution
Added `calculateFuelEfficiency()` method to Vehicle interface and stored efficiency values in AbstractVehicle. Each vehicle gets its efficiency value through the constructor.

## Code Changes

### Interface Update
```java
interface Vehicle {
    void start();
    void stop();
    String getInfo();
    double calculateFuelEfficiency(); // NEW method
}
```

### AbstractVehicle Changes
```java
abstract class AbstractVehicle implements Vehicle, ElectricVehicle {
    protected double fuelEfficiency; // NEW attribute
    
    // Updated constructor
    public AbstractVehicle(String type, String fuel, String color, double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency; // Store efficiency value
    }
    
    // NEW method implementation
    public double calculateFuelEfficiency() {
        return fuelEfficiency; // Return stored value
    }
}
```

### Vehicle Constructor Updates
All vehicle classes now pass efficiency values to parent constructor:

- **Car:** 15.0 km/L 
- **Motorcycle:** 35.0 km/L 
- **Bus:** 4.0 km/L 
- **Electric Car:** 0.2 kWh/km 
- **Electric Motorcycle:** 0.1 kWh/km 

## Implementation Details

### Traditional Vehicles
Use kilometers per liter (km/L) as efficiency unit. Values are realistic based on vehicle type and size.

### Electric Vehicles
Use kilowatt-hours per kilometer (kWh/km) as efficiency unit. Lower numbers mean better efficiency for electric vehicles.

### Storage Method
Efficiency values are stored as instance variables in AbstractVehicle rather than calculated dynamically. This follows the hint in the requirements and keeps implementation simple.

## Key Concepts Used
- Interface method addition
- Constructor parameter passing
- Polymorphism (same method, different values)
- Different units for different vehicle types

## Result
All vehicles can now report their fuel efficiency through the same interface method, but each vehicle type has realistic efficiency values appropriate for that vehicle category. The program displays efficiency in vehicle information and provides a comparison at the end.