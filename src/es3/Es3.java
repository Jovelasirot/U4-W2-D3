package es3;

import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Es3 {
    public static void main(String[] args) {
        Random rdm = new Random();

        Supplier<Double> rdmPrice = () -> {
            return rdm.nextDouble(101, 200);
        };

        Supplier<Long> rdmId = () -> {
            return rdm.nextLong(1, 100);
        };

        Supplier<Product> productSupplier1 = () -> new Product(rdmId.get(), "Random Boys product", "Tea", rdmPrice.get());

        Supplier<Product> productSupplier = () -> new Product(rdmId.get(), "Random Boys product", "Boys", rdmPrice.get());

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
            productList.add(productSupplier1.get());
        }


        System.out.println("All products");
        System.out.println(productList);

        System.out.println("-------------------------------");

        List<Product> boysProducts = productList.stream().filter(product -> product.getCategory().equals("Boys")).toList();

        System.out.println("Only boys products");
        System.out.println(boysProducts);

        List<Product> discountedBoysProducts = boysProducts.stream().map(product -> {
            double discountedPrice = product.getPrice() - (product.getPrice() / 10);
            return new Product(product.getId(), product.getName(), product.getCategory(), discountedPrice);
        }).toList();


        System.out.println("Discounted boys products");
        System.out.println(discountedBoysProducts);
    }
}
