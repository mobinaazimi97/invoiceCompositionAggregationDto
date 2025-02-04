//Composition :

package com.mftplus.aggregationcustomer.dto.orderCustomerDto;
import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.order.Order;

import java.time.LocalDate;

public class OrderCustomerDto {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private Long orderId;
    private double price;
    private LocalDate orderDate;


    public Customer getCustomer() {
        return Customer.builder().id(customerId).lastName(lastName).firstName(firstName).phone(phone).email(email).build();
    }

    public Order getOrder() {
        return Order.builder().id(orderId).price(price).orderDate(orderDate).build();
    }

}
