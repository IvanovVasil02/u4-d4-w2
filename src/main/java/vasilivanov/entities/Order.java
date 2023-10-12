package vasilivanov.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Order {
  public List<Product> products;
  public Customer customer;
  private Long id;
  private String status;
  private LocalDate orderDate;
  private LocalDate localDate;

  public Order(String status, Date orderDate, Date localDate, List<Product> products, Customer customer) {
    this.id = (long) Math.floor(Math.random() * 101);
    this.status = status;
    this.orderDate = convertToLocalDate(orderDate);
    this.localDate = convertToLocalDate(localDate);
    this.products = products;
    this.customer = customer;
  }

  public static Boolean isBetween(Order order, String date1, String date2) {
    LocalDate a = LocalDate.parse(date1);
    LocalDate b = LocalDate.parse(date2);
    return order.orderDate.isAfter(a) && order.orderDate.isBefore(b);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public double getTotal() {
    return this.products.stream().mapToDouble(Product::getPrice).sum();
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public LocalDate convertToLocalDate(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }

  @Override
  public String toString() {
    return "Order{" +
            ", products=" + products +
            ", customer=" + customer +
            '}';
  }
}
