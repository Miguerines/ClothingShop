package com.udistrital.tiendaderopa;

import Modelo.ShoppingCart;
import Modelo.Clothing;
import Modelo.TShirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private Clothing shirt;

    @BeforeEach
    public void setUp() {
        cart = new ShoppingCart();
        shirt = new TShirt("003", "T-Shirt", "T-Shirt deportiva", 15.99, 20, "S", "Negro");
    }

    @Test
    public void testAddItemValid() {
        boolean result = cart.addItem(shirt, 3);
        assertTrue(result);
        assertEquals(3, cart.getItemCount());
    }

    @Test
    public void testAddItemInvalidQuantity() {
        boolean result = cart.addItem(shirt, -1);
        assertFalse(result);
        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void testRemoveItem() {
        cart.addItem(shirt, 2);
        assertTrue(cart.removeItem(shirt));
        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void testUpdateQuantity() {
        cart.addItem(shirt, 2);
        assertTrue(cart.updateQuantity(shirt, 5));
        assertEquals(5, cart.getItemCount());
    }

    @Test
    public void testCalculateTotal() {
        cart.addItem(shirt, 2);
        assertEquals(31.98, cart.calculateTotal(), 0.01);
    }
}
