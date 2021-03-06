package ua.shad.pizzaservice.domain;

/**
 *
 * @author andrii
 */
public class Customer {
    private Integer id;
    private String name;
    private String address;
    

    public Customer() {
    }

    public Customer(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String adress) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
