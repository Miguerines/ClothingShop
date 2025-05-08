package backend;

/**
 * Abstract base class for all clothing items in the store.
 * Contains common properties for all clothing products.
 */
public abstract class Clothing {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String size;
    private String color;

    public Clothing(String id, String name, String description, double price, 
                   int stockQuantity, String size, String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.size = size;
        this.color = color;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Size: %s, Color: %s) - $%.2f - %d in stock",
                id, name, size, color, price, stockQuantity);
    }
}