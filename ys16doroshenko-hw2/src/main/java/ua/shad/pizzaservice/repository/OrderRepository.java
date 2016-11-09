package ua.shad.pizzaservice.repository;

import ua.shad.pizzaservice.domain.Order;

import java.util.List;

/**
 *
 * @author andrii
 */
public interface OrderRepository {
    Integer addNewOrder(Order order);
    List<Order> getAllOrders();
}
