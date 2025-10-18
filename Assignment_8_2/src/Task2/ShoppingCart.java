package Task2;

import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCart class
 * Implementation following TDD approach
 *
 * Manages items in a shopping cart with prices
 */
public class ShoppingCart {

    // Inner class to represent an item with name and price
    private static class Item {
        String name;
        double price;

        Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    // List to store all items in the cart
    private List<Item> items;

    /**
     * Constructor - initializes an empty shopping cart
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the shopping cart
     * @param name the name of the item
     * @param price the price of the item
     */
    public void addItem(String name, double price) {
        items.add(new Item(name, price));
    }

    /**
     * Removes the first occurrence of an item from the cart
     * @param name the name of the item to remove
     */
    public void removeItem(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(name)) {
                items.remove(i);
                break; // Remove only the first occurrence
            }
        }
    }

    /**
     * Returns the total number of items in the cart
     * @return the count of items
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Calculates the total cost of all items in the cart
     * @return the total price
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.price;
        }
        return total;
    }

    /**
     * Clears all items from the cart
     */
    public void clear() {
        items.clear();
    }

    /**
     * Returns a list of all item names in the cart
     * @return list of item names
     */
    public List<String> getItems() {
        List<String> itemNames = new ArrayList<>();
        for (Item item : items) {
            itemNames.add(item.name);
        }
        return itemNames;
    }
}