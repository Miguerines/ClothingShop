import java.util.List;

/**
 * Represents an administrator in the clothing store system.
 * Extends Person with inventory management capabilities.
 */
public class Administrator extends Person {

    /**
     * Constructor for Administrator
     * @param firstName Admin's first name
     * @param lastName Admin's last name
     * @param email Admin's email
     * @param password Admin's password
     */
    public Administrator(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    /**
     * Registers a new product in the inventory
     * @param newProduct Clothing item to register
     * @param inventory List where the product will be added
     */
    public void registerProduct(Clothing newProduct, List<Clothing> inventory) {
        if (!isLoggedIn()) {
            System.out.println("Administrator login required to register products");
            return;
        }
        
        inventory.add(newProduct);
        System.out.println("Product successfully registered: " + newProduct.getName());
    }

    /**
     * Updates the stock quantity for a product
     * @param product Product to update
     * @param newQuantity New stock quantity
     * @param inventory Inventory to modify
     */
    public void updateInventory(Clothing product, int newQuantity, List<Clothing> inventory) {
        if (!isLoggedIn()) {
            System.out.println("Administrator login required to update inventory");
            return;
        }
        
        if (inventory.contains(product)) {
            product.setStockQuantity(newQuantity);
            System.out.println("Inventory updated for " + product.getName() + 
                             ". New quantity: " + newQuantity);
        } else {
            System.out.println("Product not found in inventory");
        }
    }
}