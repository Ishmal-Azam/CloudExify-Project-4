public interface Purchasable {

    void purchase(Product product, int quantity)
            throws OutOfStockException;

    double calculateTotal(Product product, int quantity);
}
