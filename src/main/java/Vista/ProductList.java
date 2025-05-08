package Vista;

import Modelo.Clothing;
import Modelo.TShirt; // Importa las clases concretas desde backend
import Modelo.Jeans;
import Modelo.Jacket;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @class ProductList
 * @description Muestra una lista de productos disponibles en una ventana.
 */
public class ProductList extends JFrame {

    private JList<Clothing> productJList;
    private DefaultListModel<Clothing> productListModel;

    /**
     * Constructor para la ventana de la lista de productos.
     * @param products La lista de productos a mostrar.
     */
    public ProductList(List<Clothing> products) {
        setTitle("Lista de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setLocationRelativeTo(null); // Centra la ventana

        productListModel = new DefaultListModel<>();
        for (Clothing product : products) {
            productListModel.addElement(product);
        }

        productJList = new JList<>(productListModel);
        productJList.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(productJList);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Actualiza la lista de productos mostrada en la ventana.
     * @param newProducts La nueva lista de productos a mostrar.
     */
    public void updateProducts(List<Clothing> newProducts) {
        productListModel.clear();
        for (Clothing product : newProducts) {
            productListModel.addElement(product);
        }
    }

    // Método main para probar la ventana (simulando datos)
    public static void main(String[] args) {
        List<Clothing> sampleProducts = List.of(
                new TShirt("TS001", "Camiseta Algodón", "Camiseta básica de algodón", 25.99, 50, "M", "Blanco"),
                new Jeans("JN002", "Vaqueros Rectos", "Pantalones vaqueros de corte clásico", 59.99, 30, "32", "Azul"),
                new Jacket("JK003", "Chaqueta Cuero", "Chaqueta de cuero genuino", 120.00, 15, "L", "Negro")
        );
        SwingUtilities.invokeLater(() -> new ProductList(sampleProducts));
    }
}