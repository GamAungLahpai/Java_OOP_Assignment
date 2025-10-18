package Task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ShoppingCart using JUnit 5
 * Following TDD approach
 */
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void testAddItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);

        assertEquals(2, cart.getItemCount(), "Cart should contain 2 items");
    }

    @Test
    void testRemoveItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.removeItem("Apple");

        assertEquals(1, cart.getItemCount(),
                "Cart should contain 1 item after removal");
    }

    @Test
    void testCalculateTotal() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        assertEquals(2.25, cart.calculateTotal(), 0.01, "Total should be 2.25");
    }

    @Test
    void testEmptyCart() {
        assertEquals(0, cart.getItemCount(), "Empty cart should have 0 items");
        assertEquals(0.0, cart.calculateTotal(), 0.01,
                "Empty cart total should be 0.0");
    }

    @Test
    void testAddMultipleQuantitiesOfSameItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 1.0);

        assertEquals(3, cart.getItemCount(), "Cart should contain 3 items");
        assertEquals(3.0, cart.calculateTotal(), 0.01, "Total should be 3.0");
    }

    @Test
    void testRemoveItemThatDoesNotExist() {
        cart.addItem("Apple", 1.0);
        cart.removeItem("Banana");

        assertEquals(1, cart.getItemCount(),
                "Cart should still contain 1 item");
    }

    @Test
    void testRemoveAllItems() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);

        cart.removeItem("Apple");
        cart.removeItem("Banana");
        cart.removeItem("Orange");

        assertEquals(0, cart.getItemCount(), "Cart should be empty");
        assertEquals(0.0, cart.calculateTotal(), 0.01, "Total should be 0.0");
    }

    @Test
    void testAddItemWithZeroPrice() {
        cart.addItem("Free Sample", 0.0);

        assertEquals(1, cart.getItemCount(), "Cart should contain 1 item");
        assertEquals(0.0, cart.calculateTotal(), 0.01, "Total should be 0.0");
    }

    @Test
    void testCalculateTotalWithDecimals() {
        cart.addItem("Milk", 2.99);
        cart.addItem("Bread", 1.49);
        cart.addItem("Eggs", 3.25);

        assertEquals(7.73, cart.calculateTotal(), 0.01, "Total should be 7.73");
    }

    @Test
    void testRemoveOnlyOneInstanceOfItem() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Apple", 1.0);
        cart.removeItem("Apple");

        assertEquals(1, cart.getItemCount(),
                "Cart should contain 1 item after removing one Apple");
        assertEquals(1.0, cart.calculateTotal(), 0.01, "Total should be 1.0");
    }

    @Test
    void testClearCart() {
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.clear();

        assertEquals(0, cart.getItemCount(), "Cart should be empty after clear");
        assertEquals(0.0, cart.calculateTotal(), 0.01,
                "Total should be 0.0 after clear");
    }

    @Test
    void testMultipleOperations() {
        // Complex test with multiple operations
        cart.addItem("Apple", 1.0);
        cart.addItem("Banana", 0.5);
        cart.addItem("Orange", 0.75);
        assertEquals(3, cart.getItemCount());
        assertEquals(2.25, cart.calculateTotal(), 0.01);

        cart.removeItem("Banana");
        assertEquals(2, cart.getItemCount());
        assertEquals(1.75, cart.calculateTotal(), 0.01);

        cart.addItem("Grape", 1.25);
        assertEquals(3, cart.getItemCount());
        assertEquals(3.0, cart.calculateTotal(), 0.01);
    }
}