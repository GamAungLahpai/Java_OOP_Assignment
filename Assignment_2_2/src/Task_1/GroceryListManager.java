package Task_1;

import java.util.ArrayList;

public class GroceryListManager {
    private ArrayList<String> groceryList = new ArrayList<>();

    public void addItem(String item) {
        if (item == null || item.isEmpty()) {
            System.out.println("Cannot add a null or empty item.");
            return;
        }
        if (groceryList.contains(item)) {
            System.out.println("\"" + item + "\" already exists in the list.");
        } else {
            groceryList.add(item);
            System.out.println("\"" + item + "\" has been added to the list.");
        }
    }

    public void removeItem(String item) {
        if (item == null || item.isEmpty()) {
            System.out.println("Cannot remove a null or empty item.");
            return;
        }
        if (groceryList.remove(item)) {
            System.out.println("\"" + item + "\" was removed successfully.");
        } else {
            System.out.println("\"" + item + "\" does not exist in the list.");
        }
    }

    public void displayList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        int count = 1;
        for (String entry : groceryList) {
            System.out.println(count + ". " + entry);
            count++;
        }
    }

    public boolean checkItem(String item) {
        if (item == null) return false;
        return groceryList.contains(item);
    }

    public static void main(String[] args) {
        GroceryListManager manager = new GroceryListManager();
        manager.addItem("Apples");
        manager.addItem("Milk");
        manager.addItem("Bread");

        System.out.println("\nCurrent list:");
        manager.displayList();

        String checkItem = "Milk";
        System.out.println("\nIs \"" + checkItem + "\" in the list? " + manager.checkItem(checkItem));

        System.out.println("\nRemoving \"Milk\"...");
        manager.removeItem("Milk");

        System.out.println("\nUpdated list:");
        manager.displayList();
    }
}
