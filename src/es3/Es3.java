package es3;

import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static es1.Es1.getProductSupplier;

public class Es3 {
    public static void main(String[] args) {

        Supplier<Product> productSupplier = getProductSupplier();

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
        }

        System.out.println("All products");
        System.out.println(productList);

        System.out.println("-------------------------------");

        List<Product> boysProducts = productList.stream().filter(product -> product.getCategory().equals("Boys")).toList();

        System.out.println("Only boys products");
        System.out.println(boysProducts);

        List<Product> discountedBoysProducts = boysProducts.stream().map(product -> {
            return new Product(product.getId(), product.getName(), product.getCategory(), product.setPrice(product.getPrice() * 0.9));
        }).toList();


        System.out.println("Discounted boys products" + " (" + discountedBoysProducts.size() + ")");
        System.out.println(discountedBoysProducts);
    }


}
