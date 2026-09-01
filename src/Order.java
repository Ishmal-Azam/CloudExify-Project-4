import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String orderID;
    private final Customer customer;
    private final List<CartItem> items;
    private double totalAmount;
    private String status;

    public Order(String orderID,
                 Customer customer,
                 ShoppingCart cart) {

        this.orderID = orderID;
        this.customer = customer;
        this.items = new ArrayList<>();

        for (CartItem item : cart.getItems()) {

            CartItem copy = new CartItem(
                    item.getProduct(),
                    item.getQuantity());

            items.add(copy);
        }

        this.totalAmount = cart.getTotal();
        this.status = "Pending";
    }

    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void calculateTotal() {

        totalAmount = 0;

        for (CartItem item : items) {
            totalAmount += item.getSubtotal();
        }
    }
}
