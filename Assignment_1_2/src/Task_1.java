import java.util.Scanner;

/* Task 1 Fahrenheit to Celsius Converter Write a program that prompts the user to enter a temperature in Fahrenheit and converts it to Celsius.
Display the converted temperature on the console with one decimal place.
 */
public class Task_1 {
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);

        System.out.println("Please enter temperature in Fahrenheit: ");
        double userInput = sc.nextDouble();

        double temperature = (userInput - 32)*5/9;
        System.out.printf("Temperature in Celsius is: %.1f%n", temperature);
        sc.close();

    }
}
