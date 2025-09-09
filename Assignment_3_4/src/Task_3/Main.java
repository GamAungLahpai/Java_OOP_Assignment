package Task_3;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Create instances
        Student student = new Student(2024, "John Smith", 22);
        Course course = new Course("MATH201", "Advanced Calculus", "Prof. Anderson");
        Enrollment enrollment = new Enrollment(student, course, "2024-01-15");

        // Serialize
        try {
            FileOutputStream fileOut = new FileOutputStream("enrollments.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(enrollment);
            out.close();
            fileOut.close();
            System.out.println("Enrollment serialized and saved to enrollments.ser");
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }

        // Deserialize
        try {
            FileInputStream fileIn = new FileInputStream("enrollments.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Enrollment deserializedEnrollment = (Enrollment) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("\nDeserialized enrollment:");
            System.out.println(deserializedEnrollment);
            System.out.println("\nStudent: " + deserializedEnrollment.getStudent());
            System.out.println("Course: " + deserializedEnrollment.getCourse());
            System.out.println("Enrollment Date: " + deserializedEnrollment.getEnrollmentDate());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        }
    }
}