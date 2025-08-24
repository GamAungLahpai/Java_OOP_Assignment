import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter the integers into the array: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        int[] newArray = new int[size];
        int count = 0;

        for (int i = 0; i < size; i++) {
            boolean found = false;
            for (int j = 0; j < count; j++) {
                if (array[i] == newArray[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                newArray[count] = array[i];
                count++;
            }
        }

        System.out.println("Array without duplicates:");
        for (int i = 0; i < count; i++) {
            System.out.print(newArray[i] + " ");
        }

        scanner.close();
    }
}