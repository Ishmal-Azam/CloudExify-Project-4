public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) {

        if (amount <= 0) {
            return;
        }

        quantity += amount;
    }

    public void decreaseQuantity(int amount) {

        if (amount <= 0) {
            return;
        }

        if (amount >= quantity) {
            quantity = 1;
        } else {
            quantity -= amount;
        }
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
