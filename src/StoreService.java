import java.util.ArrayList;
import java.util.List;

public class StoreService implements Purchasable {

    private final List<Product> products;
    private final List<Order> orders;

    public StoreService() {

        products = new ArrayList<>();
        orders = new ArrayList<>();

        loadSampleProducts();
    }

    private void loadSampleProducts() {

        products.add(new Electronics(
                "E001",
                "Wireless Headphones",
                4500,
                15,
                4.5,
                "Sony",
                12
        ));

        products.add(new Electronics(
                "E002",
                "Smart Watch",
                8500,
                10,
                4.3,
                "Samsung",
                12
        ));

        products.add(new Electronics(
                "E003",
                "Wireless Mouse",
                1800,
                25,
                4.2,
                "Logitech",
                6
        ));

        products.add(new Book(
                "B001",
                "Java Programming",
                2500,
                12,
                4.7,
                "Herbert Schildt",
                "McGraw Hill"
        ));

        products.add(new Book(
                "B002",
                "Clean Code",
                3200,
                8,
                4.8,
                "Robert C. Martin",
                "Prentice Hall"
        ));

        products.add(new Clothing(
                "C001",
                "Classic T-Shirt",
                1800,
                20,
                4.4,
                "M",
                "Cotton"
        ));

        products.add(new Clothing(
                "C002",
                "Denim Jacket",
                4500,
                7,
                4.6,
                "L",
                "Denim"
        ));
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Product findProduct(String productID) {

        for (Product product : products) {

            if (product.getProductID()
                    .equalsIgnoreCase(productID)) {

                return product;
            }
        }

        return null;
    }

    public List<Product> searchProducts(String keyword) {

        List<Product> results = new ArrayList<>();

        if (keyword == null) {
            return results;
        }

        keyword = keyword.toLowerCase();

        for (Product product : products) {

            if (product.getName()
                    .toLowerCase()
                    .contains(keyword)
                    ||
                product.getProductType()
                    .toLowerCase()
                    .contains(keyword)) {

                results.add(product);
            }
        }

        return results;
    }

    public List<Product> getProductsByCategory(
            String category) {

        List<Product> results = new ArrayList<>();

        for (Product product : products) {

            if (product.getProductType()
                    .equalsIgnoreCase(category)) {

                results.add(product);
            }
        }

        return results;
    }

    @Override
    public void purchase(Product product, int quantity)
            throws OutOfStockException {

        if (product == null) {
            throw new OutOfStockException(
                    "Product not found.");
        }

        if (quantity <= 0) {
            throw new OutOfStockException(
                    "Invalid quantity.");
        }

        if (quantity > product.getStock()) {
            throw new OutOfStockException(
                    "Only " + product.getStock()
                    + " item(s) available.");
        }

        product.decreaseStock(quantity);
    }

    @Override
    public double calculateTotal(
            Product product,
            int quantity) {

        if (product == null || quantity <= 0) {
            return 0;
        }

        return product.getPrice() * quantity;
    }

    public void addOrder(Order order) {

        if (order != null) {
            orders.add(order);
        }
    }
}