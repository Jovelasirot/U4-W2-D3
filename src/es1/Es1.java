package es1;

import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Es1 {
    public static void main(String[] args) {

        Supplier<Product> productSupplier = getProductSupplier();

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
        }

        System.out.println("All products:");
        System.out.println(productList);
        System.out.println("----------------------");

        List<Product> filteredProducts = productList.stream().filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100).toList();

        System.out.println("Books with a price of over 100 are: " + filteredProducts.size());
        filteredProducts.forEach(System.out::println);

    }

    public static Supplier<Product> getProductSupplier() {
        Random rdm = new Random();

        return () -> {
            long rdmId = rdm.nextLong(10000, 100000);

            int rdmName = rdm.nextInt(0, 5);

            int rdmCategory = rdm.nextInt(0, 5);

            double rdmPrice = rdm.nextDouble(10.00, 200.00);

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

            return new Product(rdmId, nameList.get(rdmName), categoryList.get(rdmCategory), rdmPrice);
        };
    }
}
