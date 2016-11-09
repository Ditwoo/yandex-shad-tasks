package ua.shad.pizzaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.service.OrderService;

import java.util.List;

@RestController
public class OrderRestController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("id")int id) {

        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.FOUND);
    }

    @RequestMapping(value = "order/{id}/item/", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> getItemsInOrder(@PathVariable("id")int id) {

        Order order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order.getPizzas(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "order/{orderId}/item/{pizzaId}", method = RequestMethod.PUT)
    public ResponseEntity addItemToOrder(@PathVariable("orderId")int orderId,
                                         @PathVariable("pizzaId")int pizzaId) {

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.addItemToOrder(orderId, pizzaId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "order/{orderId}/item/{pizzaId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItemToOrder(@PathVariable("orderId")int orderId,
                                            @PathVariable("pizzaId")int pizzaId) {

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.removeItemFromOrder(orderId, pizzaId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
