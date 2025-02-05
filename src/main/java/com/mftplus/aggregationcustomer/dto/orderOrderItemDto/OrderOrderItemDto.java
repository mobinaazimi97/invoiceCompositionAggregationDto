//Composition :

package com.mftplus.aggregationcustomer.dto.orderOrderItemDto;


import com.github.mfathi91.time.PersianDate;
import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.order.Order;
import com.mftplus.aggregationcustomer.orderItem.OrderItem;

import java.time.LocalDate;

public class OrderOrderItemDto {

    private Long orderId;
    private double price;
    private LocalDate orderDate;
    private Customer customer;

    private Long customerId;
    private String firstName;

    private Long orderItemId;
    private int quantity;


    public Customer getCustomer() {
        return Customer.builder().id(customerId).firstName(firstName).build();
    }

    public Order getOrder() {
        return Order.builder()
                .id(orderId)
                .price(price)
                //todo : customer maybe should be build
                .customer(customer)
                .build();
    }

    public OrderItem getOrderItem() {
        return OrderItem.builder()
                .id(orderItemId)
                .quantity(quantity)
                .build();
    }
    public String getPersianOrderDate() {
        return PersianDate.fromGregorian(orderDate).toString();

    }

    public void setPersianOrderDate(String persianOrderDate) {
        this.orderDate = PersianDate.parse(persianOrderDate).toGregorian();
    }
}
