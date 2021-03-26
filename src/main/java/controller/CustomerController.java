package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CustomerService;

import java.util.List;

@Controller

public class CustomerController {
    private static final CustomerService customerService = new CustomerService();

    @GetMapping("/home")
    public String index(Model model) {
        List<Customer> customer = customerService.findAll();
        model.addAttribute("customers", customer);
        return "index";
    }

    @GetMapping("customer/create")
    public String Create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @PostMapping("/customer/save")
    public String save(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address) {
        Customer c = new Customer();
        c.setId((int) (Math.random() * 10000));
        c.setEmail(email);
        c.setAddress(address);
        c.setName(name);
        customerService.save(c);
        return "redirect:/home";
    }

    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit";
    }

    @PostMapping("/customer/update")
    public String update(Customer customer) {
        Customer customers = new Customer();
        customerService.update(customer.getId(),customers);
        return "redirect:/home";
    }

    @PostMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "delete";
    }

    @PostMapping("/customer/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/home";
    }

    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "view";
    }
}
