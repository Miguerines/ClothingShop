package com.udistrital.tiendaderopa;

import com.udistrital.tiendaderopa.Shirt;
import com.udistrital.tiendaderopa.Clothing;
import com.udistrital.tiendaderopa.Administrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {
    private Administrator admin;
    private List<Clothing> inventory;
    private Clothing shirt;

    @BeforeEach
    public void setUp() {
        admin = new Administrator("Admin", "Test", "admin@test.com", "pass123");
        inventory = new ArrayList<>();
        shirt = new Shirt("001", "Camisa", "Camisa casual", 29.99, 10, "M", "Azul");
    }

    @Test
    public void testRegisterProductWhenLoggedIn() {
        admin.login("admin@test.com", "pass123");
        admin.registerProduct(shirt, inventory);
        assertTrue(inventory.contains(shirt));
    }

    @Test
    public void testRegisterProductWhenNotLoggedIn() {
        admin.registerProduct(shirt, inventory);
        assertFalse(inventory.contains(shirt));
    }

    @Test
    public void testUpdateInventoryWhenLoggedIn() {
        admin.login("admin@test.com", "pass123");
        inventory.add(shirt);
        admin.updateInventory(shirt, 25, inventory);
        assertEquals(25, shirt.getStockQuantity());
    }

    @Test
    public void testUpdateInventoryWhenProductNotInInventory() {
        admin.login("admin@test.com", "pass123");
        admin.updateInventory(shirt, 25, inventory);
        assertNotEquals(25, shirt.getStockQuantity()); // No cambia
    }
}
