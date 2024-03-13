package es2;

import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Es2 {
    public static void main(String[] args) {
        
        Random rdm = new Random();

        Supplier<Double> rdmPrice = () -> {
            return rdm.nextDouble(10, 200);
        };

        Supplier<Long> rdmId = () -> {
            return rdm.nextLong(1, 100);
        };


        Supplier<Product> productSupplier = () -> new Product(rdmId.get(), "Random Baby Product", "Baby", rdmPrice.get());

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
        }

        Customer customerA = new Customer(12, "William", 4);

        LocalDate orderDate = LocalDate.now();
        LocalDate deliveryDate = orderDate.plusDays(rdm.nextInt(14));


        Supplier<Order> orderSupplier = () -> new Order(rdmId.get(), "Shipped", orderDate, deliveryDate, productList, customerA);

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            orderList.add(orderSupplier.get());
        }

        System.out.println(orderList);
    }
}
