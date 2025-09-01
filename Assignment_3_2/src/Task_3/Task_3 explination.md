# Task 3: Electric Vehicles

## What I Did
Added electric vehicles to the existing vehicle system. Now we have both regular cars and electric cars working together.

## Main Problem
How to make electric and regular vehicles work in the same system when electric vehicles need charging but regular vehicles don't.

## Solution
Made AbstractVehicle implement both Vehicle and ElectricVehicle interfaces. This means all vehicles have charging methods, but regular vehicles just say "cannot charge".

## Code Structure

### New Interface
```java
interface ElectricVehicle {
    void charge();
    int getBatteryLevel();
    void setBatteryLevel(int level);
}
```

### AbstractVehicle Changes
```java
abstract class AbstractVehicle implements Vehicle, ElectricVehicle {
    // Default for regular vehicles
    public void charge() {
        System.out.println("No possible to charge " + type);
    }
}
```

### New Electric Classes
- ElectricCar: Overrides charge() to actually charge
- ElectricMotorcycle: Same as above but for motorcycles

## How It Works

**Regular vehicles (Car, Bus, Motorcycle):**
- Use default charge() method that says "cannot charge"
- Battery level always 0%

**Electric vehicles (ElectricCar, ElectricMotorcycle):**
- Override charge() to actually charge the battery
- Have real battery levels and charging

## Key Concepts Used
- Multiple interface implementation
- Method overriding
- Polymorphism (same method, different behavior)
- Default implementations

## Result
All vehicles can be treated the same way, but electric vehicles actually charge while regular vehicles say they cannot charge. This lets us add electric vehicles without breaking existing code.