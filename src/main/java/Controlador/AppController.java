package Controlador;

import backend.Administrator;
import backend.Clothing;
import backend.GenericClothing;
import frontend.AdminPanel;
import frontend.ProductList;
import frontend.ShoppingCartView;
import frontend.Storefront;
import backend.ShoppingCart;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @class AppController
 * @description Clase principal y controlador inicial para iniciar la aplicación de la tienda de ropa.
 */
public class AppController {

    public static void main(String[] args) {
        // 1. Inicializar los datos del backend (simulado)
        Administrator admin = new Administrator("Admin", "User", "admin@tienda.com", "password");
        admin.login("admin@tienda.com", "password"); // Simular inicio de sesión

        List<Clothing> initialInventory = new ArrayList<>();
        initialInventory.add(new GenericClothing("GC001", "Camiseta Básica", "Algodón suave", 25.99, 100, "M", "Blanco"));
        initialInventory.add(new GenericClothing("GC002", "Pantalón Vaquero", "Corte recto clásico", 59.50, 50, "32", "Azul"));
        initialInventory.add(new GenericClothing("GC003", "Chaqueta Cuero", "Cuero genuino de alta calidad", 120.00, 20, "L", "Negro"));

        ShoppingCart customerCart = new ShoppingCart();
        customerCart.addItem(new GenericClothing("GC001", "Camiseta Básica", "Algodón suave", 25.99, 100, "M", "Blanco"), 2);
        customerCart.addItem(new GenericClothing("GC002", "Pantalón Vaquero", "Corte recto clásico", 59.50, 50, "32", "Azul"), 1);

        // 2. Crear e iniciar la interfaz de usuario en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // a. Crear y mostrar el panel de administración
            AdminPanel adminPanel = new AdminPanel(admin, initialInventory);
            adminPanel.setVisible(true);

            // b. Crear y mostrar la lista de productos (inicialmente con el mismo inventario)
            ProductList productListWindow = new ProductList(initialInventory);
            productListWindow.setVisible(true);

            // c. Crear y mostrar la vista del carrito de compras
            ShoppingCartView shoppingCartView = new ShoppingCartView(customerCart);
            shoppingCartView.setVisible(true);

            // d. Crear y mostrar la interfaz de la tienda
            Storefront storefront = new Storefront(initialInventory, customerCart);
            storefront.setVisible(true);
        });

        System.out.println("Aplicación iniciada desde el controlador.");
    }
}
