package ua.shad.pizzaservice.repository;

import ua.shad.pizzaservice.domain.Pizza;

import java.util.List;

/**
 *
 * @author andrii
 */
public interface PizzaRepository {
    Pizza getPizzaById(Integer id);
    List<Pizza> getPizzas();
}
