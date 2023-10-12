package vasilivanov;

import com.github.javafaker.Faker;
import vasilivanov.entities.Customer;
import vasilivanov.entities.Order;
import vasilivanov.entities.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

  public static void main(String[] args) throws ParseException {
    Faker faker = new Faker(new Locale("ITALY"));
    Supplier<Product> ProductSupplier = () -> new Product(faker.commerce().productName(), faker.commerce().department(), faker.commerce().price(50, 300));
    Supplier<Customer> customerSupplier = () -> new Customer(faker.name().firstName() + " " + faker.name().lastName());
    List<Product> productList = new ArrayList<>();
    List<Customer> customerList = new ArrayList<>();
    Date date = new Date();
    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2023");


    for (int i = 0; i < 5; i++) {
      productList.add(ProductSupplier.get());

    }
    for (int i = 0; i < 10; i++) {
      customerList.add(customerSupplier.get());
    }
    List<Order> orderList = customerList.stream().map(customer -> new Order("state of wainting", faker.date().between(date1, date2), date, productList, customer)).toList();
//        EX1
    Map<String, List<Order>> clientCartList = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer().getName()));
//    clientCartList.forEach((customer, cart) -> System.out.println("Cart of " + customer + ": " + cart));
//        EX2
    Map<String, Double> clientCartTotal = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer().getName(), Collectors.summingDouble(Order::getTotal)));
//    clientCartTotal.forEach((client, total) -> System.out.println(client + " total: " + total + "$"));

//        EX3

    List<Product> expensiveProductList = productList.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).limit(3).toList();
//    expensiveProductList.forEach(System.out::println);

//        EX4

    System.out.println("Average of orders: " + orderList.stream().mapToDouble(Order::getTotal).average().orElse(0.0));
//        EX4

//    System.out.println(productList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice))));
  }
}
