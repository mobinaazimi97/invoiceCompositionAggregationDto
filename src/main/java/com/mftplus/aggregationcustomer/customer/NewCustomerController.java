package com.mftplus.aggregationcustomer.customer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/newCustomers")
@Slf4j
public class NewCustomerController {
    private final CustomerService customerService;

    public NewCustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomers(Model model) {
        model.addAttribute("customer", new Customer());
        return "newCustomers";
    }

    @PostMapping
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer) {
        try {
            customerService.saveCustomer(customer);
            log.info("Customer added: " + customer);
            return customer;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    //TODO : error-> UnSupported ContentType On UTF.8
//    @PostMapping
//    public String addCustomer(@Valid Customer customer, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()) {
//            return "newCustomers";
//        }
//
//        customerService.saveCustomer(customer);
//        log.info("Customer added: " + customer);
////        return "redirect:/ncustomers";
//        return "fragment :: customers-table";
//    }

}
