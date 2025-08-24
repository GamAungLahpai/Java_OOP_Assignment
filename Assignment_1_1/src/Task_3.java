import java.util.Scanner;

public class Task_3 {
    public static void main (String[]args){
        // Task_3

            Scanner scanner = new Scanner(System.in);

            // Request an input from the user
            System.out.println("Give the first number:");
            int first = Integer.parseInt(scanner.nextLine());

            System.out.println("Give the second number:");
            int second = Integer.parseInt(scanner.nextLine());

            System.out.println("Give the third number:");
            int third = Integer.parseInt(scanner.nextLine());

            // Calculation
            double sum = first + second + third;
            double product = first * second * third;
            double average = sum / 3;

            //Printing part
            System.out.println("The sum of the numbers is " + sum);
            System.out.println("The average of the numbers is " + average);
            System.out.println("The product of the numbers is " + product);

    }
}

