package Shape.com;

public class ShapeCalculator {
    public static void main(String[] args) {
        System.out.println("Shape Calculator\n");

        // Create an array of Shape objects with different shapes and colors
        Shape[] shapes = {
                new Circle(5.0, "Red"),
                new Rectangle(4.0, 6.0, "Blue"),
                new Triangle(3.0, 8.0, "Green")
        };

        // Loop through the array and display area of each shape with color
        for (Shape shape : shapes) {
            double area = shape.calculateArea();
            System.out.println("Area of " + shape + " (Color: " + shape.getColor() + "): " + area);
        }
    }
}