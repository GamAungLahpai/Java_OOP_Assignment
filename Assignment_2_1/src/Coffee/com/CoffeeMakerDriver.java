package Coffee.com;

public class CoffeeMakerDriver {
    public static void main(String[] args) {
        // Create an instance of CoffeeMaker
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        // Turn the coffee maker on
        coffeeMaker.switchOnOff();
        if (coffeeMaker.getOn()) {
            System.out.println("Coffee maker is on");
        }

        // Set coffee type to espresso
        coffeeMaker.setCoffeeType(CoffeeMaker.CoffeeType.ESPRESSO);
        System.out.println("Coffee type is " + coffeeMaker.getCoffeeType().toString().toLowerCase());

        // Set coffee amount to 50 ml
        coffeeMaker.setCoffeeAmount(50);
        System.out.println("Coffee amount is " + coffeeMaker.getCoffeeAmount() + " ml");

        // Turn the coffee maker off
        coffeeMaker.switchOnOff();
        if (!coffeeMaker.getOn()) {
            System.out.println("Coffee maker is off");
        }
    }
}