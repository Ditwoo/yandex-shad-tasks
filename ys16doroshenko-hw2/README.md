## Домашнее задание №2

#### Часть 1

--------------------------------------------------------------------------------------------------------------

Используя **Spring**-аннотации (там где это возможно) реализовать следующие классы:

**Customer**
- id
- name
- address

**Pizza**
- id
- name
- price
- type (Enum: Vegetarian, Sea, Meat)

**PizzaService interface**
- List<Pizza> getAllPizzas()
- List<Pizza> getPizzasByType(PizzaType type)
- Pizza getPizzaById(Integer id)
- List<Pizza> getPizzasSortedByPrice()

**PizzaRepository interface <- InMemPizzaRepository** (in memory collection with predefined pizzas list)
-CRUD

**Order** - должен быть объявлен прототипом и создаваться Spring-контейнером
- id
- date
- name: Date + id
- pizzas
- totalPrice
- customer

**OrderService interface**
- List<Order> getAllOrders()
- Order placeNewOrder(Customer customer, Integer ... pizzas) (create and save Order)
- Order addPizzaToOrder(Order order, Integer pizzaId) (update Order)
- Order removePizzaFromOrder(Order order, Integer pizzaId) (update Order)

**OrderRepository interface <- InMemOrderRepository** (in memory collection for storing orders)
- CRUD

```java
class SpringPizzaApp {

    public static void main(String[] args) {

    // Spring application context for repository beans
    ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repositoryContext.xml"); 

    // Spring application context for service beans with access to repository beans context
    ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {"appContext.xml"}, repositoryContext); 

    PizzaService pizzaService = appContext.getBean("pizzaService", PizzaService.class);
    OrderService orderService = appContext.getBean("orderService", OrderService.class);
    
    Order order = orderService.placeNewOrder(customer, 0, 1);
    order = orderService.addPizzaToOrder(Order order, 2);

    System.out.println("Price: " + order.getTotalPrice());
    
    List<Order> orders = orderService.getAllOrders();
    orders.stream().forEach(System.out::println);
    }
}
```

--------------------------------------------------------------------------------------------------------------

#### Часть 2

Используя **BeanPostProcessor** подключить аннотации **@Benchmark**.
Проверить ее работоспособность для метода **OrderService.placeNewOrder**.