import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<CartItem> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity)
            throws OutOfStockException {

        if (product == null) {
            return;
        }

        if (quantity <= 0) {
            return;
        }

        if (quantity > product.getStock()) {
            throw new OutOfStockException(
                    "Only " + product.getStock()
                    + " item(s) available.");
        }

        for (CartItem item : items) {

            if (item.getProduct().getProductID()
                    .equals(product.getProductID())) {

                int newQuantity = item.getQuantity() + quantity;

                if (newQuantity > product.getStock()) {
                    throw new OutOfStockException(
                            "Not enough stock available.");
                }

                item.setQuantity(newQuantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(String productID) {

        items.removeIf(item ->
                item.getProduct()
                        .getProductID()
                        .equals(productID));
    }

    public void updateQuantity(String productID, int quantity)
            throws OutOfStockException {

        if (quantity <= 0) {
            removeProduct(productID);
            return;
        }

        for (CartItem item : items) {

            if (item.getProduct()
                    .getProductID()
                    .equals(productID)) {

                if (quantity > item.getProduct().getStock()) {
                    throw new OutOfStockException(
                            "Not enough stock available.");
                }

                item.setQuantity(quantity);
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public int getTotalItems() {

        int total = 0;

        for (CartItem item : items) {
            total += item.getQuantity();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
