import java.util.Scanner;
import java.util.Random;

public class Task_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Arrays of first and last names
        String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "James", "Sophia", "William"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

        // Ask user for number of names
        System.out.print("How many random names should the program generate? ");
        int numberOfNames = scanner.nextInt();

        // Generate random names
        for (int i = 0; i < numberOfNames; i++) {
            int firstIndex = random.nextInt(firstNames.length);
            int lastIndex = random.nextInt(lastNames.length);

            String fullName = firstNames[firstIndex] + " " + lastNames[lastIndex];
            System.out.println(fullName);
        }

        scanner.close();
    }
}