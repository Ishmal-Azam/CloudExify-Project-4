public abstract class Product {

    protected String productID;
    protected String name;
    protected double price;
    protected int stock;
    protected double rating;

    public Product(String productID, String name, double price,
                   int stock, double rating) {

        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
    }

    // Getters

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getRating() {
        return rating;
    }

    // Setters

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // Stock methods

    public void increaseStock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
        }
    }

    public boolean decreaseStock(int quantity) {

        if (quantity <= 0) {
            return false;
        }

        if (quantity > stock) {
            return false;
        }

        stock -= quantity;
        return true;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    // Product type

    public abstract String getProductType();

    // Product information

    @Override
    public String toString() {

        return productID + " | "
                + name + " | Rs. "
                + String.format("%.2f", price)
                + " | Stock: "
                + stock;
    }
}