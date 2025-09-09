package Task_01;

import java.io.*;
import java.net.*;
import java.util.*;

public class TemperatureReader {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://users.metropolia.fi/~jarkkov/temploki.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            reader.readLine(); // Skip header

            List<Double> temperatures = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("01.01.2023")) {
                    String[] parts = line.split(";");
                    String tempStr = parts[1].replace(",", ".");
                    temperatures.add(Double.parseDouble(tempStr));
                }
            }

            reader.close();

            double sum = 0;
            for (double temp : temperatures) {
                sum += temp;
            }

            double average = sum / temperatures.size();
            System.out.printf("Average temperature for January 1st, 2023: %.2f°C%n", average);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}