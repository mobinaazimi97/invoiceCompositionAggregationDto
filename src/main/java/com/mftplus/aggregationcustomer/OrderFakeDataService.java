package com.mftplus.aggregationcustomer;

import com.github.javafaker.Faker;
import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.order.Order;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class OrderFakeDataService {
    private final Faker faker1=new Faker(new Locale("fa"));
    private final Faker faker2=new Faker();

    public Order createFakeOrder() {
        Customer customer=Customer
                .builder()
                .firstName(faker1.name().firstName())
                .lastName(faker1.name().lastName())
                .email(faker2.internet().emailAddress())
                .phone(faker1.phoneNumber().cellPhone().replaceAll("[\\s\\-().]", ""))
                .build();
        return Order
                .builder()
//                .orderDate(LocalDate.parse(faker1.date().toString()))
                .price(2f)
                .customer(customer)
                .build();
    }
}
