import java.util.Scanner;

public class Task_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask user to enter the binary number
        System.out.print("Enter the binary number: ");
        String binaryNumber = sc.nextLine();

        int decimalNumber = 0;
        int power = 0;

        //Calculate to get decimal number from binary number
        for (int i = binaryNumber.length() - 1; i >= 0; i--) {
            char bit = binaryNumber.charAt(i);

            if (bit == '1') {
                decimalNumber += (int) Math.pow(2, power);
            }
            power++;
        }
        System.out.printf("Binary %s is = Decimal %d%n", binaryNumber, decimalNumber);

        sc.close();


    }
}
