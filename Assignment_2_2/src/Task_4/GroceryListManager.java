package Task_4;

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

    private HashMap<String, Item> groceryList = new HashMap<>();

    // Add item with quantity
    public void addItem(String item, double cost, String category, int quantity) {
        if (item == null || item.isEmpty() || category == null || category.isEmpty() || cost < 0 || quantity <= 0) {
            System.out.println("Invalid input. Item, category must be non-empty, cost >= 0, quantity > 0.");
            return;
        }

        Item existing = groceryList.get(item);
        if (existing != null) {
            existing.cost = cost;
            existing.category = category;
            existing.quantity += quantity;
            System.out.println("\"" + item + "\" exists. Updated cost $" + String.format("%.2f", cost) +
                    ", category \"" + category + "\", new quantity " + existing.quantity + ".");
        } else {
            groceryList.put(item, new Item(cost, category, quantity));
            System.out.println("\"" + item + "\" added: cost $" + String.format("%.2f", cost) +
                    ", category \"" + category + "\", quantity " + quantity + ".");
        }
    }

    // Update quantity of an existing item
    public void updateQuantity(String item, int newQuantity) {
        if (item == null || item.isEmpty() || newQuantity < 0) {
            System.out.println("Invalid input for item or quantity.");
            return;
        }

        Item it = groceryList.get(item);
        if (it != null) {
            int oldQty = it.quantity;
            it.quantity = newQuantity;
            System.out.println("\"" + item + "\" quantity updated from " + oldQty + " to " + newQuantity + ".");
        } else {
            System.out.println("\"" + item + "\" not found in the list.");
        }
    }

    // Remove an item
    public void removeItem(String item) {
        if (item == null || item.isEmpty()) {
            System.out.println("Invalid item name.");
            return;
        }

        if (groceryList.remove(item) != null) {
            System.out.println("\"" + item + "\" removed from the list.");
        } else {
            System.out.println("\"" + item + "\" not found in the list.");
        }
    }

    // Check if an item exists
    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.containsKey(item);
    }

    // Display all items
    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        int index = 1;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            Item it = e.getValue();
            System.out.println(index + ". " + e.getKey() + " - $" + String.format("%.2f", it.cost) +
                    " [" + it.category + "], qty: " + it.quantity);
            index++;
        }
    }

    // Display only items with positive quantity
    public void displayAvailableItems() {
        int index = 1;
        boolean any = false;
        for (Map.Entry<String, Item> e : groceryList.entrySet()) {
            Item it = e.getValue();
            if (it.quantity > 0) {
                System.out.println(index + ". " + e.getKey() + " - $" + String.format("%.2f", it.cost) +
                        " [" + it.category + "], qty: " + it.quantity);
                index++;
                any = true;
            }
        }
        if (!any) {
            System.out.println("No available items with positive quantity.");
        }
    }

    // Calculate total cost considering quantity
    public double calculateTotalCost() {
        double total = 0.0;
        for (Item it : groceryList.values()) {
            total += it.cost * it.quantity;
        }
        return total;
    }

    // Demo
    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();

        manager.addItem("Apples", 2.50, "Fruits", 3);
        manager.addItem("Milk", 1.80, "Dairy", 2);
        manager.addItem("Bread", 2.20, "Bakery", 1);

        System.out.println("\nAll items:");
        manager.displayList();

        System.out.println("\nAvailable items:");
        manager.displayAvailableItems();

        System.out.println("\nCheck if Milk is present: " + manager.checkItem("Milk"));

        System.out.println("\nUpdating quantities...");
        manager.updateQuantity("Apples", 0);
        manager.updateQuantity("Milk", 5);
        manager.updateQuantity("Bread", 2);

        System.out.println("\nAvailable items after updates:");
        manager.displayAvailableItems();

        System.out.println("\nTotal cost: $" + String.format("%.2f", manager.calculateTotalCost()));

        System.out.println("\nRemoving Bread...");
        manager.removeItem("Bread");

        System.out.println("\nFinal list:");
        manager.displayList();
    }
}
