package Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a shopping cart that holds items a customer wants to purchase.
 * Manages the cart items and calculates totals.
 */
public class ShoppingCart {
    private Map<Clothing, Integer> items; // Item and quantity
    
    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    /**
     * Adds an item to the shopping cart
     * @param item Clothing item to add
     * @param quantity Quantity to add
     * @return true if successful, false if item is null or quantity invalid
     */
    public boolean addItem(Clothing item, int quantity) {
        if (item == null || quantity <= 0) {
            return false;
        }
        
        // Check if item already exists in cart
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
        return true;
    }

    /**
     * Removes an item from the shopping cart
     * @param item Clothing item to remove
     * @return true if successful, false if item not found
     */
    public boolean removeItem(Clothing item) {
        if (items.containsKey(item)) {
            items.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Updates the quantity of an item in the cart
     * @param item Clothing item to update
     * @param newQuantity New quantity
     * @return true if successful, false if item not found or quantity invalid
     */
    public boolean updateQuantity(Clothing item, int newQuantity) {
        if (items.containsKey(item) && newQuantity > 0) {
            items.put(item, newQuantity);
            return true;
        }
        return false;
    }

    /**
     * Calculates the total price of all items in the cart
     * @return Total cart value
     */
    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Clothing, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Gets all items in the cart
     * @return Map of items and their quantities
     */
    public Map<Clothing, Integer> getItems() {
        return new HashMap<>(items); // Return a copy for encapsulation
    }

    /**
     * Clears all items from the cart
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * Gets the total number of items in the cart (sum of quantities)
     * @return Total item count
     */
    public int getItemCount() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
}