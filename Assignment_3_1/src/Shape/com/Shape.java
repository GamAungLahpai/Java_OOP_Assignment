package Shape.com;

public class Shape {
    protected String color;

    // Default constructor
    public Shape() {
        this.color = "White"; // Default color
    }

    // Constructor with color
    public Shape(String color) {
        this.color = color;
    }

    // Method that returns area of 0 (as specified in requirements)
    public double calculateArea() {
        return 0;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }
}