package Task1;

/**
 * Assignment 8.1 - Task 1: Pen class
 * Design this class to pass the given tests
 *
 * A pen that can draw when the cap is off.
 * The pen has a color and can change color when the cap is on.
 */
public class Pen {

    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");
        private final String color;
        Color(String color) {
            this.color = color;
        }
        @Override
        public String toString() {
            return color;
        }
    }

    // Instance variables
    private Color currentColor;
    private boolean capIsOn;

    /**
     * Default constructor - creates a red pen with cap on
     */
    public Pen() {
        this.currentColor = Color.RED;  // Default color is red
        this.capIsOn = true;             // Cap starts on
    }

    /**
     * Constructor with color parameter
     * @param color the initial color of the pen
     */
    public Pen(Color color) {
        this.currentColor = color;
        this.capIsOn = true;  // Cap starts on
    }

    /**
     * Draws with the pen if cap is off
     * @return "Drawing [color]" if cap is off, empty string if cap is on
     */
    public String draw() {
        if (capIsOn) {
            return "";  // Can't draw when cap is on
        } else {
            return "Drawing " + currentColor.toString();
        }
    }

    /**
     * Removes the cap from the pen
     */
    public void capOff() {
        this.capIsOn = false;
    }

    /**
     * Puts the cap on the pen
     */
    public void capOn() {
        this.capIsOn = true;
    }

    /**
     * Changes the color of the pen
     * Note: Color can only be changed when the cap is on
     * @param newColor the new color for the pen
     */
    public void changeColor(Color newColor) {
        if (capIsOn) {
            this.currentColor = newColor;
        }
        // If cap is off, color change is ignored (safety feature!)
    }
}