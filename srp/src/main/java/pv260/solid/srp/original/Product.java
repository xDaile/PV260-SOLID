package pv260.solid.srp.original;

import java.util.List;

import static java.util.Arrays.asList;

public class Product {

    private long id;

    private String name;

    private double price;

    public Product(long id,
                   String name,
                   double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static List<Product> obtainProducts() {
        //this would come from some other service,
        //could be a DB query or remote API call etc.
        return asList(new Product(1,
                        "Teddy Bear",
                        15),
                new Product(2,
                        "Toy Car",
                        25),
                new Product(3,
                        "Bike",
                        119),
                new Product(4,
                        "Ball",
                        10));
    }

    public static String formatProduct(Product product) {
        return "<tr><td>" + product.getId() + "</td><td>" + product.getName() + "</td><td>" + product.getPrice()
                + "</td></tr>\n";
    }
}
