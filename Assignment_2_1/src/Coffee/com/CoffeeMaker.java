package Coffee.com;

public class CoffeeMaker {
    //Create Fields
    public enum  CoffeeType {
        NORMAL,ESPRESSO
    }
    private boolean isOn;
    private CoffeeType coffeeType;
    private int coffeeAmount;

    //Constructor
    public  CoffeeMaker() {
        this.isOn = false;
        this.coffeeType = CoffeeType.NORMAL;
        this.coffeeAmount = 10;
    }
    //Method to turn on and off
    public void switchOnOff(){
        this.isOn = !this.isOn;
    }
    // Method to check if device is on
    public boolean getOn(){
        return this.isOn;
    }


    // getter and setter method for coffeeType
    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public boolean setCoffeeType(CoffeeType type) {
        if (this.isOn){
            this.coffeeType = type;
            return true;
        }
        return false;
    }

    // getter and setter method for coffeeAmount
    public boolean setCoffeeAmount(int amount) {
        if(this.isOn && amount >= 10 && amount <= 80){
            this.coffeeAmount = amount;
            return true;
        }
        return false;
    }

    public int getCoffeeAmount() {
        return this.coffeeAmount;
    }
}
