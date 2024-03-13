package es1;

import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Es1 {
    public static void main(String[] args) {

        Random rdm = new Random();

        Supplier<Double> rdmPrice = () -> {
            return rdm.nextDouble(101, 200);
        };

        Supplier<Long> rdmId = () -> {
            return rdm.nextLong(1, 100);
        };


        Supplier<Product> productSupplier = () -> new Product(rdmId.get(), "Random Book Title", "Book", rdmPrice.get());

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
        }

        System.out.println(productList);
    }
}
