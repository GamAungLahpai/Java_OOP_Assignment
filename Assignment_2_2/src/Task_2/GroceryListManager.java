package Task_2;

import java.util.HashMap;

public class GroceryListManager {
    private HashMap<String, Double> groceryList = new HashMap<>();

    public void addItem(String item, double cost) {
        if (item == null || item.isEmpty()) {
            System.out.println("Cannot add empty or null item.");
            return;
        }
        if (groceryList.containsKey(item)) {
            double oldCost = groceryList.get(item);
            groceryList.put(item, cost);
            System.out.println("\"" + item + "\" exists. Updating cost $" +
                    String.format("%.2f", oldCost) + " → $" + String.format("%.2f", cost));
        } else {
            groceryList.put(item, cost);
            System.out.println("\"" + item + "\" added with cost $" + String.format("%.2f", cost));
        }
    }

    public void removeItem(String item) {
        if (item == null || item.isEmpty()) {
            System.out.println("Cannot remove empty or null item.");
            return;
        }
        if (groceryList.remove(item) != null) {
            System.out.println("\"" + item + "\" removed from the list.");
        } else {
            System.out.println("\"" + item + "\" not found in the list.");
        }
    }

    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        int idx = 1;
        for (String item : groceryList.keySet()) {
            System.out.println(idx + ". " + item + " - $" + String.format("%.2f", groceryList.get(item)));
            idx++;
        }
    }

    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.containsKey(item);
    }

    public double calculateTotalCost() {
        double total = 0;
        for (double cost : groceryList.values()) total += cost;
        return total;
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();
        manager.addItem("Apples", 2.50);
        manager.addItem("Milk", 1.80);
        manager.addItem("Bread", 2.20);

        System.out.println("\nList with costs:");
        manager.displayList();

        String checkItem = "Milk";
        System.out.println("\nIs \"" + checkItem + "\" in the list? " + manager.checkItem(checkItem));

        System.out.println("\nRemoving \"Milk\"...");
        manager.removeItem("Milk");

        System.out.println("\nUpdated list:");
        manager.displayList();

        System.out.println("\nTotal cost: $" + String.format("%.2f", manager.calculateTotalCost()));
    }
}
