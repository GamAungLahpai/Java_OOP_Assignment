package Task_2;

import java.io.*;

public class FibonacciGenerator {

    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("fibonacci.csv");
            writer.write("Index,Value\n");

            long a = 0, b = 1;
            writer.write("1," + a + "\n");

            for (int i = 2; i <= 60; i++) {
                writer.write(i + "," + b + "\n");
                long temp = a + b;
                a = b;
                b = temp;
            }

            writer.close();
            System.out.println("Fibonacci CSV file created successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}