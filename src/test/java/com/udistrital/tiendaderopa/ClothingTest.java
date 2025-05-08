package com.udistrital.tiendaderopa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Modelo.Clothing;

public class ClothingTest {

    // Subclase concreta para instanciar Clothing
    public class ClothingImpl extends Clothing {
        public ClothingImpl(String id, String name, String description, double price, int stockQuantity, String size, String color) {
            super(id, name, description, price, stockQuantity, size, color);
        }
    }

    @Test
    public void testGetId() {
        Clothing clothing = new ClothingImpl("001", "Camisa", "Camisa de algodón", 19.99, 10, "M", "Azul");
        assertEquals("001", clothing.getId());
    }

    @Test
    public void testSetNameAndGetName() {
        Clothing clothing = new ClothingImpl("002", "Pantalón", "Jeans", 49.99, 5, "L", "Negro");
        clothing.setName("Pantalón Slim");
        assertEquals("Pantalón Slim", clothing.getName());
    }

    @Test
    public void testSetPriceAndGetPrice() {
        Clothing clothing = new ClothingImpl("003", "Falda", "Falda corta", 25.00, 8, "S", "Rojo");
        clothing.setPrice(30.00);
        assertEquals(30.00, clothing.getPrice(), 0.01);
    }

    @Test
    public void testToString() {
        Clothing clothing = new ClothingImpl("004", "Blusa", "Blusa formal", 35.00, 12, "M", "Blanco");
        String expected = "004 - Blusa (Size: M, Color: Blanco) - $35,00 - 12 in stock";

        assertEquals(expected, clothing.toString());
    }

}
