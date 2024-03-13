package es4;

import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Es4 {
    public static void main(String[] args) {
        Random rdm = new Random();

        Supplier<Double> rdmPrice = () -> {
            return rdm.nextDouble(10, 200);
        };

        Supplier<Long> rdmId = () -> {
            return rdm.nextLong(1, 100);
        };


        Supplier<Product> productSupplier = () -> new Product(rdmId.get(), "Random Toys Product", "Toys", rdmPrice.get());

        Supplier<Product> productSupplier2 = () -> new Product(rdmId.get(), "Random Cars Product", "Cars", rdmPrice.get());

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productList.add(productSupplier.get());
            productList.add(productSupplier2.get());
        }

        Product randomProduct = productList.get(rdm.nextInt(productList.size()));
        List<Product> singleProductList = new ArrayList<>();
        singleProductList.add(randomProduct);

        Customer customerA = new Customer(12, "William", 4);

        Customer customerB = new Customer(12, "Nik", 2);

        Customer customerC = new Customer(12, "Joe", 6);

        Customer customerD = new Customer(12, "Shawn", 2);

        LocalDate startRangeDate = LocalDate.parse("2021-02-01");
        LocalDate endRangeDate = startRangeDate.plusMonths(2);

        LocalDate orderDate = startRangeDate.plusDays(rdm.nextInt(1, 60));
        LocalDate deliveryDate = orderDate.plusDays(rdm.nextInt(14));


        Supplier<Order> orderSupplierA = () -> new Order(rdmId.get(), "Shipped", orderDate, deliveryDate, singleProductList, customerA);

        Supplier<Order> orderSupplierB = () -> new Order(rdmId.get(), "Shipped", orderDate, deliveryDate, singleProductList, customerB);

        Supplier<Order> orderSupplierC = () -> new Order(rdmId.get(), "Shipped", orderDate, deliveryDate, singleProductList, customerC);

        Supplier<Order> orderSupplierD = () -> new Order(rdmId.get(), "Shipped", orderDate, deliveryDate, singleProductList, customerD);

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            orderList.add(orderSupplierA.get());
            orderList.add(orderSupplierB.get());
            orderList.add(orderSupplierC.get());
            orderList.add(orderSupplierD.get());
        }

        System.out.println(orderList);

        List<Product> productsOrderedByTier2Customers = new ArrayList<>();
        for (Order order : orderList) {
            Customer customer = order.getCustomer();
            if (customer.getTier() == 2 && isDateInRange(order.getOrderDate(), startRangeDate, endRangeDate)) {
                productsOrderedByTier2Customers.addAll(order.getProducts());
            }
        }

        System.out.println("Products ordered by Tier 2 customers between " + startRangeDate + " and " + endRangeDate + ":");
        for (Order order : orderList) {
            Customer customer = order.getCustomer();
            if (customer.getTier() == 2 && isDateInRange(order.getOrderDate(), startRangeDate, endRangeDate)) {
                Product product = order.getProducts().get(0); //
                System.out.println("Product Name: " + product.getName() +
                        ", Customer Name: " + customer.getName() +
                        ", Order Date: " + order.getOrderDate());
            }
        }

    }

    private static boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
