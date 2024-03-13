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
        for (int i = 0; i < 20; i++) {
            orderList.add(orderSupplier.get());
        }

        List<Product> babyProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equals("Baby")) {
                babyProducts.add(product);
            }
        }

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
            LocalDate startDate = LocalDate.parse("2021-01-01");

            int rdmProduct = rdm.nextInt(2, 3);
//            products
            List<Product> rdmProductList = new ArrayList<>();
            for (int i = 0; i < rdmProduct; i++) {
                rdmProductList.add(getProductSupplier().get());
            }

            Customer rdmCustomer = getCustomerSupplier().get();

            LocalDate orderDate = startDate.plusDays(rdm.nextInt(1, 500));
            LocalDate deliveryDate = orderDate.plusDays(rdm.nextInt(14));

            List<String> statusList = List.of("Shipped", "Error");

            return new Order(rdmId, statusList.get(rdmStatus), orderDate, deliveryDate, rdmProductList, rdmCustomer);
        };
    }
}


