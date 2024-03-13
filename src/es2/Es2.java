package es2;

import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static es1.Es1.getProductSupplier;

public class Es2 {
    public static void main(String[] args) {

        Supplier<Product> productSupplier = getProductSupplier();
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            productList.add(productSupplier.get());
        }

        Supplier<Customer> customerSupplier = getCustomerSupplier();
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            customerList.add(customerSupplier.get());
        }

        Supplier<Order> orderSupplier = getOrderSupplier();
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            orderList.add(orderSupplier.get());
        }

        List<String> babyProducts = new ArrayList<>();

        orderList.forEach(order -> order.getProducts().forEach(product -> {
            if (product.getCategory().equals("Baby")) {
                babyProducts.add(String.valueOf(order));
            }
        }));

        System.out.println(babyProducts);
    }

    public static Supplier<Customer> getCustomerSupplier() {
        Random rmd = new Random();

        return () -> {
            long rmdId = rmd.nextLong(10000, 100000);

            int rmdTier = rmd.nextInt(1, 7);

            int rmdName = rmd.nextInt(0, 5);

            List<String> nameList = List.of("Lexi", "Anna", "William", "Joe", "Adam");

            return new Customer(rmdId, nameList.get(rmdName), rmdTier);
        };
    }

    public static Supplier<Order> getOrderSupplier() {
        Random rdm = new Random();

        return () -> {
            long rdmId = rdm.nextLong(10000, 100000);

            int rdmStatus = rdm.nextInt(0, 2);

//            dates
            LocalDate startRangeDate = LocalDate.parse("2021-02-01");
            LocalDate endRangeDate = startRangeDate.plusMonths(2);


            int rdmProduct = rdm.nextInt(1, 5);
//            products
            List<Product> rdmProductList = new ArrayList<>();
            for (int i = 0; i < rdmProduct; i++) {
                rdmProductList.add(getProductSupplier().get());
            }

            int randomCustomerIndex = rdm.nextInt(0, getCustomerSupplier().size());
            Customer rdmCustomer = customerList.get(randomCustomerIndex);

            LocalDate orderDate = startRangeDate.plusDays(rdm.nextInt(1, 200));
            LocalDate deliveryDate = orderDate.plusDays(rdm.nextInt(14));

            List<String> statusList = new ArrayList<>();
            statusList.add("Shipped");
            statusList.add("Error");

            return new Order(rdmId, statusList.get(rdmStatus), orderDate, deliveryDate, rdmProductList, rdmCustomer);
        };
    }
}


