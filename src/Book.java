public class Book extends Product {

    private String author;
    private String publisher;

    public Book(String productID,
                String name,
                double price,
                int stock,
                double rating,
                String author,
                String publisher) {

        super(productID, name, price, stock, rating);

        this.author = author;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getProductType() {
        return "Book";
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Author: " + author
                + " | Publisher: " + publisher;
    }
}