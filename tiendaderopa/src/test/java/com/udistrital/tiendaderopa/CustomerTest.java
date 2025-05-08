package com.udistrital.tiendaderopa;

import com.udistrital.tiendaderopa.Customer;
import com.udistrital.tiendaderopa.Clothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    private Customer customer;
    private Clothing shirt;
    private List<Clothing> inventory;

    @BeforeEach
    public void setUp() {
        customer = new Customer("Juan", "Lopez", "juan@test.com", "1234");
        shirt = new Shirt("002", "Polo", "Polo básico", 19.99, 15, "L", "Rojo");
        inventory = new ArrayList<>();
        inventory.add(shirt);
    }

    @Test
    public void testViewProductsWhenLoggedIn() {
        customer.login("juan@test.com", "1234");
        assertDoesNotThrow(() -> customer.viewProducts(inventory));
    }

    @Test
    public void testAddToCartWhenLoggedIn() {
        customer.login("juan@test.com", "1234");
        boolean result = customer.addToCart(shirt, 2);
        assertTrue(result);
        assertEquals(2, customer.getCart().getItemCount());
    }

    @Test
    public void testAddToCartWhenNotLoggedIn() {
        boolean result = customer.addToCart(shirt, 2);
        assertFalse(result);
        assertEquals(0, customer.getCart().getItemCount());
    }
}
