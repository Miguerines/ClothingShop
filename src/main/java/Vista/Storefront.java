package Vista;

import Modelo.Clothing;
import Modelo.ShoppingCart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @class Storefront
 * @description Interfaz para que los usuarios exploren el inventario y agreguen productos al carrito.
 */
public class Storefront extends JFrame {

    private JList<Clothing> inventoryList;
    private DefaultListModel<Clothing> inventoryListModel;
    private JButton addToCartButton;
    private ShoppingCart cart;
    private ShoppingCartView cartView;

    /**
     * Constructor para la interfaz de la tienda.
     * @param initialInventory La lista inicial de productos en el inventario.
     * @param cart El carrito de compras del cliente.
     */
    public Storefront(List<Clothing> initialInventory, ShoppingCart cart) {
        this.cart = cart;
        this.cartView = new ShoppingCartView(cart); // Inicializa la vista del carrito

        setTitle("Tienda de Ropa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inventoryListModel = new DefaultListModel<>();
        for (Clothing product : initialInventory) {
            inventoryListModel.addElement(product);
        }
        inventoryList = new JList<>(inventoryListModel);
        inventoryList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane inventoryScrollPane = new JScrollPane(inventoryList);

        addToCartButton = new JButton("Agregar al Carrito");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clothing selectedProduct = inventoryList.getSelectedValue();
                if (selectedProduct != null) {
                    String quantityStr = JOptionPane.showInputDialog(Storefront.this,
                            "Ingrese la cantidad para " + selectedProduct.getName() + ":", "Cantidad", JOptionPane.QUESTION_MESSAGE);
                    if (quantityStr != null && !quantityStr.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(quantityStr);
                            if (quantity > 0 && selectedProduct.getStockQuantity() >= quantity) {
                                cart.addItem(selectedProduct, quantity);
                                cartView.updateCartDisplay(); // Actualiza la vista del carrito
                                JOptionPane.showMessageDialog(Storefront.this,
                                        quantity + " " + selectedProduct.getName() + "(s) agregado(s) al carrito.",
                                        "Carrito", JOptionPane.INFORMATION_MESSAGE);
                                // Aquí podrías también actualizar la cantidad en stock mostrada en la lista
                            } else if (quantity <= 0) {
                                JOptionPane.showMessageDialog(Storefront.this,
                                        "Por favor, ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(Storefront.this,
                                        "No hay suficiente stock disponible de " + selectedProduct.getName() + ".",
                                        "Stock Insuficiente", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(Storefront.this,
                                    "Por favor, ingrese un número para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(Storefront.this,
                            "Por favor, seleccione un producto para agregar al carrito.",
                            "Seleccionar Producto", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton viewCartButton = new JButton("Ver Carrito");
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartView.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);

        setLayout(new BorderLayout());
        add(inventoryScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Actualiza la lista de productos mostrada en la tienda.
     * @param newInventory La nueva lista de productos a mostrar.
     */
    public void updateInventory(List<Clothing> newInventory) {
        inventoryListModel.clear();
        for (Clothing product : newInventory) {
            inventoryListModel.addElement(product);
        }
    }

    // Método main para probar la interfaz de la tienda (simulando datos)
    public static void main(String[] args) {
        List<Clothing> sampleInventory = List.of(new Modelo.TShirt("TS001", "Camiseta Algodón", "Camiseta básica de algodón", 25.99, 50, "M", "Blanco"),
                new Modelo.Jeans("JN002", "Vaqueros Rectos", "Pantalones vaqueros de corte clásico", 59.99, 30, "32", "Azul"),
                new Modelo.Jacket("JK003", "Chaqueta Cuero", "Chaqueta de cuero genuino", 120.00, 15, "L", "Negro"),
                new Modelo.GenericClothing("GC004", "Gorra Deportiva", "Gorra ajustable para deportes", 15.00, 100, "Unitalla", "Negro")
        );
        ShoppingCart testCart = new ShoppingCart();
        SwingUtilities.invokeLater(() -> new Storefront(sampleInventory, testCart));
    }
}