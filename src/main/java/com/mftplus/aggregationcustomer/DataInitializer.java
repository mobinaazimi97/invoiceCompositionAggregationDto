package com.mftplus.aggregationcustomer;



import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner  {//implements CommandLineRunner
    private final FakeDataService fakeDataService;
    private final CustomerService customerService;


    public DataInitializer(FakeDataService fakeDataService, CustomerService customerService) {
        this.fakeDataService = fakeDataService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        int n=25;
        for (int i = 1; i <= n; i++) {
            Customer customer = fakeDataService.createFakeCustomer();
            customerService.saveCustomer(customer);
        }
        log.info("{} Customers Created",n);
    }
}
