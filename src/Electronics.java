public class Electronics extends Product {

    private String brand;
    private int warrantyMonths;

    public Electronics(String productID,
                       String name,
                       double price,
                       int stock,
                       double rating,
                       String brand,
                       int warrantyMonths) {

        super(productID, name, price, stock, rating);

        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    // Getter

    public String getBrand() {
        return brand;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    // Setter

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    // Product type

    @Override
    public String getProductType() {
        return "Electronics";
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Brand: " + brand
                + " | Warranty: " + warrantyMonths + " months";
    }
}