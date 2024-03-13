package es4;

import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static es1.Es1.getProductSupplier;
import static es2.Es2.getCustomerSupplier;
import static es2.Es2.getOrderSupplier;

public class Es4 {
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

        LocalDate startRangeDate = LocalDate.parse("2021-02-01");
        LocalDate endRangeDate = startRangeDate.plusMonths(2);


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
                System.out.println
                        ("Product Name: " + product.getName() +
                                ", Customer Name: " + customer.getName() +
                                ", Order Date: " + order.getOrderDate());
            }
        }

    }

    private static boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
