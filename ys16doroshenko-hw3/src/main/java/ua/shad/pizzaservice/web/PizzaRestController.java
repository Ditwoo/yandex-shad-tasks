package ua.shad.pizzaservice.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ua.shad.pizzaservice.domain.Pizza;
import ua.shad.pizzaservice.service.PizzaService;

/**
 *
 * @author andrii
 */
@RestController()
public class PizzaRestController {

    @Autowired
    private final PizzaService pizzaService;

    @Autowired
    public PizzaRestController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello form PizzaController";
    }

    @RequestMapping(value = "pizza", method = RequestMethod.GET)
    public List<Pizza> pizzas() {
        return pizzaService.getPizzas();
    }

    @RequestMapping(value = "pizza/{id}/price", method = RequestMethod.GET)
    public ResponseEntity<Double> getPizzaPrice(@PathVariable("id")int id) {

        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza.getPrice(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "pizza/{id}/price",
            method = RequestMethod.PUT,
            consumes = "application/json")
    public ResponseEntity updatePizzaPrice(@PathVariable("id")int id,
                                           @RequestBody Pizza updatedPizza) {

        Pizza oldPizza = pizzaService.getPizzaById(id);
        if (oldPizza == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (updatedPizza.getPrice() < 0.) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        pizzaService.updatePizzaById(id, updatedPizza);
        return new ResponseEntity(HttpStatus.FOUND);
    }

    @RequestMapping(value = "pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> pizzaById(@PathVariable("id")int id) {

        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.FOUND);
    }

    @RequestMapping(value = "pizza/{id}/price", method = RequestMethod.GET)
    public ResponseEntity<Double> pizzaPriceById(@PathVariable("id")int id) {

        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza.getPrice(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "pizza",
                    method = RequestMethod.POST,
                    consumes = "application/json")
    public ResponseEntity<Integer> add(@RequestBody Pizza pizza,
            UriComponentsBuilder builder) {
        Pizza newPizza = pizza;
        newPizza.setId(42);
        System.out.println(newPizza);
        //newPizza = pizzaService.addPizza(pizza);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/pizza/{id}").
                buildAndExpand(pizza.getId()).toUri());

        return new ResponseEntity<>(
                newPizza.getId(),
                headers,
                HttpStatus.CREATED);
    }
}
