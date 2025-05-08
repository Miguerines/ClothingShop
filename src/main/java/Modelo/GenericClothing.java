/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Clase genérica para representar un artículo de ropa.
 */
public class GenericClothing extends Clothing {
    public GenericClothing(String id, String name, String description, double price, int stockQuantity, String size, String color) {
        super(id, name, description, price, stockQuantity, size, color);
    }
}