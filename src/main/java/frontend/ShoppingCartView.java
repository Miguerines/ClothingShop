/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.Clothing;
import backend.GenericClothing; // Importa la clase desde el paquete backend
import backend.ShoppingCart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * @class ShoppingCartView
 * @description Muestra el contenido del carrito de compras.
 */
public class ShoppingCartView extends JFrame {

    private ShoppingCart cart;
    private DefaultListModel<String> cartListModel;
    private JList<String> cartJList;
    private JLabel totalLabel;

    /**
     * Constructor para la ventana del carrito de compras.
     * @param cart El carrito de compras a mostrar.
     */
    public ShoppingCartView(ShoppingCart cart) {
        this.cart = cart;

        setTitle("Carrito de Compras");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cartListModel = new DefaultListModel<>();
        cartJList = new JList<>(cartListModel);
        cartJList.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(cartJList);

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton clearButton = new JButton("Vaciar Carrito");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.clearCart();
                updateCartDisplay();
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(totalLabel);
        bottomPanel.add(clearButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        updateCartDisplay();
        setVisible(true);
    }

    /**
     * Actualiza la visualización del carrito de compras.
     */
    public void updateCartDisplay() {
        cartListModel.clear();
        Map<Clothing, Integer> items = cart.getItems();
        for (Map.Entry<Clothing, Integer> entry : items.entrySet()) {
            Clothing product = entry.getKey();
            int quantity = entry.getValue();
            cartListModel.addElement(product.getName() + " (x" + quantity + ") - $" + String.format("%.2f", product.getPrice() * quantity));
        }
        totalLabel.setText("Total: $" + String.format("%.2f", cart.calculateTotal()));
    }

    /**
     * Agrega un producto al carrito y actualiza la vista.
     * @param product El producto a agregar.
     * @param quantity La cantidad a agregar.
     */
    public void addItemToCart(Clothing product, int quantity) {
        cart.addItem(product, quantity);
        updateCartDisplay();
    }

    // Método main para probar la ventana del carrito (simulando un carrito)
    public static void main(String[] args) {
        ShoppingCart sampleCart = new ShoppingCart();
        sampleCart.addItem(new GenericClothing("GC003", "Producto Prueba 1", "Prueba", 10.00, 5, "M", "Amarillo"), 2);
        sampleCart.addItem(new GenericClothing("GC004", "Producto Prueba 2", "Prueba", 25.50, 10, "L", "Morado"), 1);
        SwingUtilities.invokeLater(() -> new ShoppingCartView(sampleCart));
    }
}