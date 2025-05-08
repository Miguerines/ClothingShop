package frontend;

import backend.Administrator;
import backend.Clothing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @class AdminPanel
 * @description Panel de administración para registrar y actualizar productos.
 */
public class AdminPanel extends JFrame {

    private Administrator admin;
    private List<Clothing> inventory;
    private ProductList productListWindow; // Referencia a la ventana de la lista de productos

    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private JTextField stockField;
    private JTextField sizeField;
    private JTextField colorField;
    private JButton registerButton;

    private JTextField updateIdField;
    private JTextField updateStockField;
    private JButton updateButton;

    /**
     * Constructor para el panel de administración.
     * @param administrator El administrador que ha iniciado sesión.
     * @param initialInventory La lista inicial de inventario.
     */
    public AdminPanel(Administrator administrator, List<Clothing> initialInventory) {
        this.admin = administrator;
        this.inventory = initialInventory;
        this.productListWindow = new ProductList(inventory); // Inicializa la ventana de la lista

        setTitle("Panel de Administración");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(2, 1, 10, 10)); // 2 filas, 1 columna

        // Panel para registrar un nuevo producto
        JPanel registerPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        registerPanel.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Producto"));

        registerPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        registerPanel.add(nameField);

        registerPanel.add(new JLabel("Descripción:"));
        descriptionField = new JTextField();
        registerPanel.add(descriptionField);

        registerPanel.add(new JLabel("Precio:"));
        priceField = new JTextField();
        registerPanel.add(priceField);

        registerPanel.add(new JLabel("Cantidad en Stock:"));
        stockField = new JTextField();
        registerPanel.add(stockField);

        registerPanel.add(new JLabel("Talla:"));
        sizeField = new JTextField();
        registerPanel.add(sizeField);

        registerPanel.add(new JLabel("Color:"));
        colorField = new JTextField();
        registerPanel.add(colorField);

        registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (admin.isLoggedIn()) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    try {
                        double price = Double.parseDouble(priceField.getText());
                        int stock = Integer.parseInt(stockField.getText());
                        String size = sizeField.getText();
                        String color = colorField.getText();

                        // Aquí deberías crear una instancia de la clase concreta de Clothing que corresponda
                        // Por ejemplo, podrías tener un JComboBox para seleccionar el tipo de prenda
                        Clothing newProduct = new backend.GenericClothing("TEMP_ID_" + System.currentTimeMillis(), name, description, price, stock, size, color);
                        admin.registerProduct(newProduct, inventory);
                        productListWindow.updateProducts(inventory); // Actualiza la lista en la otra ventana
                        JOptionPane.showMessageDialog(AdminPanel.this, "Producto registrado: " + name);
                        clearInputFields();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AdminPanel.this, "Por favor, ingrese números válidos para precio y stock.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminPanel.this, "Se requiere inicio de sesión del administrador.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        registerPanel.add(new JLabel("")); // Espacio en blanco
        registerPanel.add(registerButton);

        add(registerPanel);

        // Panel para actualizar el inventario
        JPanel updatePanel = new JPanel(new GridLayout(3, 2, 5, 5));
        updatePanel.setBorder(BorderFactory.createTitledBorder("Actualizar Inventario"));

        updatePanel.add(new JLabel("ID del Producto a Actualizar:"));
        updateIdField = new JTextField();
        updatePanel.add(updateIdField);

        updatePanel.add(new JLabel("Nueva Cantidad en Stock:"));
        updateStockField = new JTextField();
        updatePanel.add(updateStockField);

        updateButton = new JButton("Actualizar Stock");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (admin.isLoggedIn()) {
                    String productId = updateIdField.getText();
                    try {
                        int newQuantity = Integer.parseInt(updateStockField.getText());
                        Clothing productToUpdate = null;
                        for (Clothing product : inventory) {
                            if (product.getId().equals(productId)) {
                                productToUpdate = product;
                                break;
                            }
                        }
                        if (productToUpdate != null) {
                            admin.updateInventory(productToUpdate, newQuantity, inventory);
                            productListWindow.updateProducts(inventory); // Actualiza la lista
                            JOptionPane.showMessageDialog(AdminPanel.this, "Stock actualizado para el producto con ID: " + productId);
                            updateIdField.setText("");
                            updateStockField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(AdminPanel.this, "No se encontró ningún producto con el ID: " + productId, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(AdminPanel.this, "Por favor, ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminPanel.this, "Se requiere inicio de sesión del administrador.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        updatePanel.add(new JLabel("")); // Espacio en blanco
        updatePanel.add(updateButton);

        add(updatePanel);

        setVisible(true);
    }

    private void clearInputFields() {
        nameField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        stockField.setText("");
        sizeField.setText("");
        colorField.setText("");
    }

    // Método main para probar el panel de administración
    public static void main(String[] args) {
        Administrator admin = new Administrator("Admin", "User", "admin@tienda.com", "password");
        admin.login("admin@tienda.com", "password");
        List<Clothing> initialInventory = new ArrayList<>();
        initialInventory.add(new backend.GenericClothing("GC001", "Producto Genérico 1", "Descripción 1", 19.99, 100, "S", "Rojo"));
        initialInventory.add(new backend.GenericClothing("GC002", "Producto Genérico 2", "Descripción 2", 39.50, 50, "L", "Verde"));
        SwingUtilities.invokeLater(() -> new AdminPanel(admin, initialInventory));
    }
}

// Clase de ejemplo (debería estar en el paquete 'backend')
class GenericClothing extends Clothing {
    public GenericClothing(String id, String name, String description, double price, int stockQuantity, String size, String color) {
        super(id, name, description, price, stockQuantity, size, color);
    }
}