import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        //Ask user for coefficient number!
        System.out.print("Enter the first coefficient: ");
        double a = sc.nextDouble();
        System.out.print("Enter the second coefficient: ");
        double b = sc.nextDouble();
        System.out.print("Enter the third coefficient: ");
        double c = sc.nextDouble();

        //Calculation
        double quadratic = b * b - 4 * a * c;

        if (quadratic > 0) {
            double root1 = (-b + Math.sqrt(quadratic)) / (2 * a);
            double root2 = (-b - Math.sqrt(quadratic)) / (2 * a);
            System.out.printf("The roots are %.2f and %.2f%n", root1, root2);
        } else if (quadratic == 0) {
            double root = -b / (2 * a);
            System.out.printf("The equation has one real root: %.2f%n", root);
        } else {
            System.out.println("The equation has no real roots");

        }
        sc.close();


    }
}