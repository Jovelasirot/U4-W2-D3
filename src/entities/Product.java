package entities;

public class Product {
    private long id;
    private String name;
    private String category;
    private Double price;

    public Product(long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
