package es1;

import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Es1 {
    public static void main(String[] args) {

        Random rmd = new Random();

        Supplier<Product> productSupplier = () -> {
            long rmdId = rmd.nextLong(10000, 100000);

            double rmdPrice = rmd.nextDouble(10.00, 200.00);

            int rmdName = rmd.nextInt(0, 5);

            int rmdCategory = rmd.nextInt(0, 5);

            List<String> nameList = new ArrayList<>();
            nameList.add("Product A");
            nameList.add("Product B");
            nameList.add("Product C");
            nameList.add("Product D");
            nameList.add("Product E");

            List<String> categoryList = new ArrayList<>();
            categoryList.add("Books");
            categoryList.add("Boys");
            categoryList.add("Baby");
            categoryList.add("Cars");
            categoryList.add("Guns");

            return new Product(rmdId, nameList.get(rmdName), categoryList.get(rmdCategory), rmdPrice);
        };


        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
        }

        List<Product> filteredProducts = productList.stream().filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100).toList();

        System.out.println("Books with a price of over 100 are: " + filteredProducts.size());
        filteredProducts.forEach(System.out::println);

    }
}
