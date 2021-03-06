package ua.shad.pizzaservice;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.shad.pizzaservice.domain.Customer;
import ua.shad.pizzaservice.domain.Order;
import ua.shad.pizzaservice.repository.PizzaRepository;
import ua.shad.pizzaservice.service.OrderService;
import ua.shad.pizzaservice.service.PizzaService;

/**
 *
 * @author andrii
 */
public class SpringPizzaApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext("repositoryContext.xml");
        
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"}, false);
        context.setParent(repositoryContext);
        context.refresh();        
        
        PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
        System.out.println(pizzaService.getPizzaById(1));
        
        OrderService orderService = (OrderService) context.getBean("orderService");
        Customer customer = new Customer(1, "Andrii", "Some.");
        
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(orderService.getClass());
        
        System.out.println(order);
        System.out.println("Price: " + order.getTotalPrice());
        context.close();
        repositoryContext.close();
    }
}
