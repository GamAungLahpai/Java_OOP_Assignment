import java.util.Scanner;

public class Task_3 {
    public static void main(String[]args){
        Scanner scanner=new Scanner(System.in);

        System.out.print("Enter the weights in gram ");
        double weight = Double.parseDouble(scanner.nextLine());

        //  constat
        final double LUOTI_IN_GRAM = 13.28;
        final double NAULA_IN_GRAM = 32 * LUOTI_IN_GRAM;
        final double LEIVISKA_IN_GRAM = 20 * NAULA_IN_GRAM;

        // Calculation
        int leiviskä = (int) (weight/LEIVISKA_IN_GRAM);
        int naula = (int) ((weight - leiviskä * LEIVISKA_IN_GRAM)/(NAULA_IN_GRAM));
        double luoti = (weight - leiviskä * LEIVISKA_IN_GRAM - naula * NAULA_IN_GRAM)/(LUOTI_IN_GRAM);

        System.out.printf("%.0f grams is %d leviskä, %d naula, and %.2f luoti.%n",weight, leiviskä, naula, luoti);


    }
}

