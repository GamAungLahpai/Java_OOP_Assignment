package Task_3;

import java.util.HashMap;
import java.util.Map;

public class GroceryListManager {
    private static final class Item {
        double cost;
        String category;

        Item(double cost, String category) {
            this.cost = cost;
            this.category = category;
        }
    }

    private HashMap<String, Item> groceryList = new HashMap<>();

    public void addItem(String item, double cost, String category) {
        if (item == null || category == null || item.isEmpty() || category.isEmpty()) {
            System.out.println("Cannot add null or empty item/category.");
            return;
        }
        if (cost < 0) {
            System.out.println("Cost cannot be negative.");
            return;
        }

        Item oldItem = groceryList.get(item);
        if (oldItem != null) {
            groceryList.put(item, new Item(cost, category));
            System.out.println("\"" + item + "\" exists. Updating cost $" +
                    String.format("%.2f", oldItem.cost) + " → $" + String.format("%.2f", cost) +
                    ", category \"" + oldItem.category + "\" → \"" + category + "\".");
        } else {
            groceryList.put(item, new Item(cost, category));
            System.out.println("\"" + item + "\" added: cost $" + String.format("%.2f", cost) +
                    ", category \"" + category + "\".");
        }
    }

    public void removeItem(String item) {
        if (item == null || item.isEmpty()) {
            System.out.println("Cannot remove empty or null item.");
            return;
        }
        if (groceryList.remove(item) != null) {
            System.out.println("\"" + item + "\" removed.");
        } else {
            System.out.println("\"" + item + "\" not found.");
        }
    }

    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.containsKey(item);
    }

    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        int idx = 1;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            Item it = entry.getValue();
            System.out.println(idx + ". " + entry.getKey() + " - $" +
                    String.format("%.2f", it.cost) + " [" + it.category + "]");
            idx++;
        }
    }

    public void displayByCategory(String category) {
        int idx = 1;
        boolean found = false;
        for (Map.Entry<String, Item> entry : groceryList.entrySet()) {
            if (entry.getValue().category.equalsIgnoreCase(category)) {
                System.out.println(idx + ". " + entry.getKey() + " - $" +
                        String.format("%.2f", entry.getValue().cost));
                idx++;
                found = true;
            }
        }
        if (!found) System.out.println("No items found in category \"" + category + "\".");
    }

    public double calculateTotalCost() {
        double total = 0;
        for (Item it : groceryList.values()) total += it.cost;
        return total;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();
        manager.addItem("Apples", 2.50, "Fruits");
        manager.addItem("Milk", 1.80, "Dairy");
        manager.addItem("Bread", 2.20, "Bakery");

        System.out.println("\nFull list:");
        manager.displayList();

        String checkItem = "Milk";
        System.out.println("\nIs \"" + checkItem + "\" in the list? " + manager.checkItem(checkItem));

        System.out.println("\nRemoving \"" + checkItem + "\"...");
        manager.removeItem(checkItem);

        System.out.println("\nUpdated list:");
        manager.displayList();
    }
}
