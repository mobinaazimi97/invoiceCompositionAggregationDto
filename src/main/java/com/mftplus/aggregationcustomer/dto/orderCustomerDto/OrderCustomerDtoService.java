package com.mftplus.aggregationcustomer.dto.orderCustomerDto;

import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.customer.CustomerService;
import com.mftplus.aggregationcustomer.order.Order;
import com.mftplus.aggregationcustomer.order.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;



@Service
public class OrderCustomerDtoService {

    private final OrderService orderService;
    private final CustomerService customerService;


    public OrderCustomerDtoService(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }


    @Transactional(rollbackOn = Exception.class)
    public void create(OrderCustomerDto orderCustomerDto) {

        Order order = orderCustomerDto.getOrder();
        Customer customer = orderCustomerDto.getCustomer();
        orderService.saveOrder(order);
        customer.setOrder(order);
        customerService.saveCustomer(orderCustomerDto.getCustomer());

        order.setCustomer(customer);
        orderService.updateOrder(order);

    }
}
