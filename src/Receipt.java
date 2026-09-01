public class Receipt {

    private final Order order;

    public Receipt(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public String generateReceipt() {

        StringBuilder receipt = new StringBuilder();

        receipt.append("\n");
        receipt.append("====================================\n");
        receipt.append("          ECOMMERCE RECEIPT         \n");
        receipt.append("====================================\n");

        receipt.append("Order ID: ")
                .append(order.getOrderID())
                .append("\n");

        receipt.append("Customer: ")
                .append(order.getCustomer().getName())
                .append("\n");

        receipt.append("------------------------------------\n");

        for (CartItem item : order.getItems()) {

            receipt.append(item.getProduct().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = Rs. ")
                    .append(String.format("%.2f",
                            item.getSubtotal()))
                    .append("\n");
        }

        receipt.append("------------------------------------\n");

        receipt.append("Total: Rs. ")
                .append(String.format("%.2f",
                        order.getTotalAmount()))
                .append("\n");

        receipt.append("Status: ")
                .append(order.getStatus())
                .append("\n");

        receipt.append("====================================\n");

        return receipt.toString();
    }
}
