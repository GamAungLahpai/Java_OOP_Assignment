package Task_5;

import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {
    private static final class Item {
        double cost;
        String category;
        int quantity;

        Item(double cost, String category, int quantity) {
            this.cost = cost;
            this.category = category;
            this.quantity = quantity;
        }
    }

    private final HashMap<String, Item> groceryList = new HashMap<>();

    // Add item with cost, category, quantity
    public void addItem(String item, double cost, String category, int quantity) {
        if (item == null || item.isEmpty()) {
            System.out.println("Error: Cannot add empty or null item.");
            return;
        }
        if (category == null || category.isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }
        if (cost < 0) {
            System.out.println("Error: Cost cannot be negative.");
            return;
        }
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be positive.");
            return;
        }

        Item existingItem = groceryList.get(item);
        if (existingItem != null) {
            int updatedQty = existingItem.quantity + quantity;
            groceryList.put(item, new Item(cost, category, updatedQty));
            System.out.println("\"" + item + "\" already exists. Updating: cost $" + String.format("%.2f", existingItem.cost) +
                    " → $" + String.format("%.2f", cost) + ", category \"" + existingItem.category + "\" → \"" + category +
                    "\", quantity " + existingItem.quantity + " → " + updatedQty + ".");
        } else {
            groceryList.put(item, new Item(cost, category, quantity));
            System.out.println("\"" + item + "\" added: cost $" + String.format("%.2f", cost) +
                    ", category \"" + category + "\", quantity " + quantity + ".");
        }
    }

    public void updateQuantity(String item, int newQuantity) {
        if (item == null || item.isEmpty()) {
            System.out.println("Error: Item name cannot be empty.");
            return;
        }
        if (newQuantity < 0) {
            System.out.println("Error: Quantity cannot be negative.");
            return;
        }

        Item it = groceryList.get(item);
        if (it == null) {
            System.out.println("\"" + item + "\" not found in the list.");
            return;
        }
        int oldQuantity = it.quantity;
        it.quantity = newQuantity;
        System.out.println("\"" + item + "\" quantity changed from " + oldQuantity + " to " + newQuantity + ".");
    }

    public void removeItem(String item) {
        if (item == null || item.isEmpty()) {
            System.out.println("Error: Cannot remove empty or null item.");
            return;
        }
        if (groceryList.remove(item) != null) {
            System.out.println("\"" + item + "\" removed from the list.");
        } else {
            System.out.println("\"" + item + "\" was not found in the list.");
        }
    }

    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.containsKey(item.trim());
    }

    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        int idx = 1;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            Item it = entry.getValue();
            System.out.println(idx + ". " + entry.getKey() + " - $" + String.format("%.2f", it.cost) +
                    " [" + it.category + "], qty: " + it.quantity);
            idx++;
        }
    }

    public void displayAvailableItems() {
        int idx = 1;
        boolean anyAvailable = false;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            Item it = entry.getValue();
            if (it.quantity > 0) {
                System.out.println(idx + ". " + entry.getKey() + " - $" + String.format("%.2f", it.cost) +
                        " [" + it.category + "], qty: " + it.quantity);
                idx++;
                anyAvailable = true;
            }
        }
        if (!anyAvailable) System.out.println("No items with positive quantity.");
    }

    public void displayByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }
        int idx = 1;
        boolean found = false;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            Item it = entry.getValue();
            if (it.category.equalsIgnoreCase(category)) {
                System.out.println(idx + ". " + entry.getKey() + " - $" + String.format("%.2f", it.cost) +
                        ", qty: " + it.quantity);
                idx++;
                found = true;
            }
        }
        if (!found) System.out.println("No items found in category \"" + category + "\".");
    }

    public double calculateTotalCost() {
        double sum = 0.0;
        for (Item it : groceryList.values()) sum += it.cost * it.quantity;
        return sum;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        System.out.println("Adding items:");
        manager.addItem("Apples", 2.50, "Fruits", 3);
        manager.addItem("Milk", 1.80, "Dairy", 2);
        manager.addItem("Bread", 2.20, "Bakery", 1);
        manager.addItem("Bananas", 1.10, "Fruits", 6);
        manager.addItem("Butter", 3.40, "Dairy", 1);

        System.out.println("\nDisplaying all items:");
        manager.displayList();

        System.out.println("\nDisplaying by category:");
        manager.displayByCategory("Dairy");
        manager.displayByCategory("Fruits");

        System.out.println("\nUpdating quantities:");
        manager.updateQuantity("Milk", 5);
        manager.updateQuantity("Apples", 0);
        manager.displayAvailableItems();

        System.out.println("\nRemoving an item:");
        manager.removeItem("Bread");

        System.out.println("\nFinal total cost:");
        System.out.println("Total cost: $" + String.format("%.2f", manager.calculateTotalCost()));
    }
}
