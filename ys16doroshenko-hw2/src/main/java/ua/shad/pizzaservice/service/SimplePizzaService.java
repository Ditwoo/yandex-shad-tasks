package ua.shad.pizzaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.infrastruct.Benchmark;
import ua.shad.pizzaservice.repository.PizzaRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmitry Doroshenko.
 *         Yandex SHAD.
 */
@Service("pizzaService")
public class SimplePizzaService implements PizzaService {
    private PizzaRepository pizzaRepository;

    public SimplePizzaService() {
    }

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    @Benchmark(active = true)
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getPizzas();
    }

    @Override
    public List<Pizza> getPizzaByType(Pizza.PizzaType type) {
        List<Pizza> pizzas = pizzaRepository.getPizzas().stream().
                filter(pizza -> pizza.getType().equals(type)).collect(Collectors.toList());
        return pizzas;
    }

    @Override
    public Pizza getPizzaById(Integer id) {
        return pizzaRepository.getPizzaById(id);
    }

    @Override
    public List<Pizza> getPizzasSortedByPrice() {
        List<Pizza> pizzas = pizzaRepository.getPizzas().stream().sorted(new Comparator<Pizza>() {
            @Override
            public int compare(Pizza o1, Pizza o2) {
                if (o1 == null || o2 == null) {
                    return -1;
                }

                if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                }
                if (o1.getPrice() == o2.getPrice()) {
                    return 0;
                }
                return -1;
            }
        }).collect(Collectors.toList());
        return pizzas;
    }
}
