package ua.shad.pizzaservice.service;

import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Customer;
import ua.shad.pizzaservice.repository.OrderRepository;
import ua.shad.pizzaservice.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ua.shad.pizzaservice.infrastruct.Benchmark;

/**
 *
 * @author andrii
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    private PizzaService pizzaService;
    private OrderRepository orderRepository;

    public SimpleOrderService() {
    }

    @Autowired
    public SimpleOrderService(
            PizzaService pizzaService,
            OrderRepository orderRepository) {
        this.pizzaService = pizzaService;
        this.orderRepository = orderRepository;
    }

    public void setPizzaService(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    public PizzaService getPizzaService() {
        return pizzaService;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public  OrderRepository getOrderRepository() {
        return orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    @Benchmark(active = true)
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = pizzasListFromArrayOfIds(pizzasID);
        Order newOrder = getNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        orderRepository.addNewOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    @Override
    public Order addPizzaToOrder(Order order, Integer pizzaId) {
        List<Pizza> pizzas = order.getPizzas();
        pizzas.add(pizzaService.getPizzaById(pizzaId));

        Order newOrder = getNewOrder();
        newOrder.setCustomer(order.getCustomer());
        newOrder.setPizzas(pizzas);

        orderRepository.addNewOrder(newOrder);
        return newOrder;
    }

    @Override
    public Order removePizzaFromOrder(Order order, Integer pizzaId) {
        List<Pizza> pizzas = order.getPizzas();
        Pizza pizza = pizzaService.getPizzaById(pizzaId);

        // if pizza exist in current order
        // than remove it and return updated order
        if (pizzas.remove(pizza)){
            order.setPizzas(pizzas);
            return order;
        }

        // otherwise return not changed order
        return order;
    }

    @Lookup
    protected Order getNewOrder() {
        return null;
    }

    private List<Pizza> pizzasListFromArrayOfIds(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Integer id : pizzasID) {
            pizzas.add(pizzaService.getPizzaById(id));  // get Pizza from predifined in-memory list
        }
        return pizzas;
    }

}
