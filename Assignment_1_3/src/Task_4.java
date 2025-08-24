
import java.util.Scanner;

public class Task_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // For the score
        while (true) {
            int score = 0;

            System.out.println("Let's practice multiplication tables!");

            // generate random multiplication problems
            for (int i = 1; i <= 10; i++) {
                int num1 = (int)(Math.random() * 10) + 1;
                int num2 = (int)(Math.random() * 10) + 1;
                int correctAnswer = num1 * num2;//To check correct answer from the user input

                //To print the question to user
                System.out.print("Question " + i + ": " + num1 + " × " + num2 + " = ");
                int userAnswer = scanner.nextInt();

                //To check the answer
                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! The correct answer is " + correctAnswer);
                }
            }
            // Show their score to user
            System.out.println("\nYour score: " + score + "/10");

            // To congratulate if they score 10/10
            if (score == 10) {
                System.out.println("Congratulations! You have mastered the multiplication tables!");
                break;
            } else {
                System.out.println("Let's try again!\n");
            }
        }

        scanner.close();
    }
}