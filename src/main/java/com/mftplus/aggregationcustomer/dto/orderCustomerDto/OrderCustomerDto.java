//Composition :

package com.mftplus.aggregationcustomer.dto.orderCustomerDto;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.order.Order;

import java.time.LocalDate;

public class OrderCustomerDto {

    private Long orderId;
    private double price;
    private LocalDate orderDate;

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;


    public Customer getCustomer() {
        return Customer.builder()
                .id(customerId)
                .lastName(lastName)
                .firstName(firstName)
                .phone(phone)
                .email(email)
                .build();
    }

    public Order getOrder() {
        return Order.builder()
                .id(orderId)
                .price(price)
                .orderDate(orderDate)
                .build();
    }

    public String getPersianOrderDate() {
        return PersianDate.fromGregorian(orderDate).toString();

    }

    public void setPersianOrderDate(String persianOrderDate) {
        this.orderDate = PersianDate.parse(persianOrderDate).toGregorian();
    }
}
