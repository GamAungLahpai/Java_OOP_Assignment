import java.util.Scanner;

public class Task_2 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the 1st leg of the right triangle in cm:  ");
        double first = scanner.nextDouble();

        System.out.print("Please enter the 2nd leg of the right triangle  in cm:  ");
        double second = scanner.nextDouble();

        double hypotenuse = Math.sqrt(first*first + second*second);

        System.out.printf("The hypotenuse of the triangle is: %.1f cm%n", hypotenuse);

    }
}
