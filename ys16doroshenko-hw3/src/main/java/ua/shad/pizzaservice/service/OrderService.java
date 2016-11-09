package ua.shad.pizzaservice.service;

import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Customer;

/**
 *
 * @author andrii
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, Integer... pizzasID);
    Order getOrderById(Integer id);
    void addItemToOrder(Integer orderId, Integer pizzaId);
    void removeItemFromOrder(Integer orderId, Integer pizzaId);
    
}
