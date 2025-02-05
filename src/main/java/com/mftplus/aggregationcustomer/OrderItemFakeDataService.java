package com.mftplus.aggregationcustomer;

import com.github.javafaker.Faker;
import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.customer.CustomerRepository;
import com.mftplus.aggregationcustomer.order.OrderRepository;
import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import com.mftplus.aggregationcustomer.orderItem.OrderItemRepository;
import com.mftplus.aggregationcustomer.product.Product;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class OrderItemFakeDataService {

    private final Faker faker1=new Faker(new Locale("fa"));
    private final Faker faker2=new Faker();

    public OrderItem createFakeOrderItem() {

      return OrderItem.builder().quantity(1).build();

//        Customer customer=new Customer();
//        customer.setFirstName(faker1.name().firstName());
//        customer.setLastName(faker1.name().lastName());
//        customer.setEmail(faker2.internet().emailAddress());
//        customer.setPhone(faker1.phoneNumber().cellPhone().replaceAll("[\\s\\-().]", ""));
//        customerRepository.save(customer);


//        Order order = new Order();
//        order.setPrice(23f);
//        order.setCustomer(customer);
//        orderRepository.save(order);
//        return OrderItem
//                .builder()
//                .product(Product.builder().name(faker1.medical().medicineName()).brand(faker2.company().name()).build())
//                .order(order)
//                .quantity(faker1.number().randomDigit())
////                .serialNumber(faker1.number().toString().replaceAll("[\\s\\-().]", ""))
//                .build();

    }
}

