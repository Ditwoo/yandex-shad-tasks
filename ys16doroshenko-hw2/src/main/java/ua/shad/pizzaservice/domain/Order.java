package ua.shad.pizzaservice.domain;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author andrii
 */
@Component
@Scope(scopeName = "prototype")
public class Order {
    private static int count;
    
    private Integer id;
    private String date;
    private String name; // Date + id
    private List<Pizza> pizzas;
    private Double totalPrice;
    private Customer customer;

    //private double totalPrice;
    
    public Order() {
        id = ++count;        
    }
    
    public Order(Customer customer, String date,List<Pizza> pizzas) {
        this();
        this.customer = customer;
        this.pizzas = pizzas;
        this.date = date;
        this.totalPrice = pizzas.stream().mapToDouble(Pizza::getPrice).sum();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        totalPrice = pizzas.stream().mapToDouble(Pizza::getPrice).sum();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", pizzas=" + pizzas + '}';
    }
    
    
}
