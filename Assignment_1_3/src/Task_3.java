import java.util.Scanner;

public class Task_3{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        int num1 = input.nextInt();

        System.out.print("Enter a second positive integer which is grater than previous one : ");
        int num2 = input.nextInt();

        if (num1 >= num2){
            System.out.println("Try Again! ");
        }
        else  if (num2 > num1){
            System.out.println("Prime number between " + num1 + " and" + num2 + "are the following, ");
            for (int num = num1; num <= num2; num++){
                boolean isPrime = true;

                if  (num <= 1){
                    isPrime = false;
                } else  if (num == 2){
                    isPrime = true;
                } else   if (num % 2 == 0){
                    isPrime = false;
                } else {
                    for (int i = 3; i * i <= num; i += 2){
                        if (num % i == 0){
                            isPrime = false;
                            break;
                        }
                    }
                }
                if (isPrime){
                    System.out.print(num + " ");
                }

            }
            System.out.println();
        }
        input.close();
    }
}
