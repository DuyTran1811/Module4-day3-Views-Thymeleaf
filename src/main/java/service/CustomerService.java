package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    private static Map<Integer, Customer> listeners;

    static {
        listeners = new HashMap<>();
        listeners.put(1, new Customer(1, "John", "john@codegym.vn", "Hanoi"));
        listeners.put(2, new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        listeners.put(3, new Customer(3, "Alex", "alex@codegym.vn", "Saigon"));
        listeners.put(4, new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        listeners.put(5, new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        listeners.put(6, new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(listeners.values());
    }

    @Override
    public void save(Customer customer) {
        listeners.put(customer.getId(), customer);
    }

    @Override
    public Customer findById(int id) {
        return listeners.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        listeners.put(id, customer);

    }

    @Override
    public void remove(int id) {
        listeners.remove(id);
    }
}
