public class Clothing extends Product {

    private String size;
    private String material;

    public Clothing(String productID,
                    String name,
                    double price,
                    int stock,
                    double rating,
                    String size,
                    String material) {

        super(productID, name, price, stock, rating);

        this.size = size;
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String getProductType() {
        return "Clothing";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Size: " + size
                + " | Material: " + material;
    }
}