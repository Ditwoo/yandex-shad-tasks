package ua.shad.pizzaservice.service;

import ua.shad.pizzaservice.domain.Pizza;

import java.util.List;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
public interface PizzaService {
    List<Pizza> getAllPizzas();
    List<Pizza> getPizzaByType(Pizza.PizzaType type);
    Pizza getPizzaById(Integer id);
    List<Pizza> getPizzasSortedByPrice();
}
