package com.udistrital.tiendaderopa;

import com.udistrital.tiendaderopa.Clothing;
import java.util.List;

/**
 * Represents a customer in the clothing store system.
 * Extends Person with shopping cart functionality.
 */
public class Customer extends Person {
    private ShoppingCart cart;

    /**
     * Constructor for Customer
     * @param firstName Customer's first name
     * @param lastName Customer's last name
     * @param email Customer's email
     * @param password Customer's password
     */
    public Customer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.cart = new ShoppingCart();
    }

    /**
     * Displays all available clothing products
     * @param inventory List of all available products
     */
    public void viewProducts(List<Clothing> inventory) {
        if (!isLoggedIn()) {
            System.out.println("Please login to view products");
            return;
        }
        
        System.out.println("Available Products:");
        for (Clothing product : inventory) {
            System.out.println(product.toString());
        }
    }

    /**
     * Adds a product to the customer's shopping cart
     * @param product Clothing item to add
     * @param quantity Quantity to add
     * @return true if operation was successful, false otherwise
     */
    public boolean addToCart(Clothing product, int quantity) {
        if (!isLoggedIn()) {
            System.out.println("Please login to add items to your cart");
            return false;
        }
        
        return cart.addItem(product, quantity);
    }

    /**
     * Gets the customer's shopping cart
     * @return The ShoppingCart object
     */
    public ShoppingCart getCart() {
        return cart;
    }
}