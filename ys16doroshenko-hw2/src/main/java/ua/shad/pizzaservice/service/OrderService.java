package ua.shad.pizzaservice.service;

import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Customer;

import java.util.List;

/**
 *
 * @author andrii
 */
public interface OrderService {
    List<Order> getAllOrders();
    Order placeNewOrder(Customer customer, Integer... pizzasID);
    Order addPizzaToOrder(Order order, Integer pizzaId);
    Order removePizzaFromOrder(Order order, Integer pizzaId);
}
