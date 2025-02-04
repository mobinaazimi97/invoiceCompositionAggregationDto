package com.mftplus.aggregationcustomer;

import com.github.javafaker.Faker;
import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import com.mftplus.aggregationcustomer.product.Product;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class OrderItemFakeDataService {
//    private final CustomerRepository customerRepository;
//    private final OrderRepository orderRepository;

    private final Faker faker1=new Faker(new Locale("fa"));
    private final Faker faker2=new Faker();

//    public OrderItemFakeDataService(CustomerRepository customerRepository, OrderRepository orderRepository) {
//        this.customerRepository = customerRepository;
//        this.orderRepository = orderRepository;
//    }

    public OrderItem createFakeOrderItem() {

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
        return OrderItem
                .builder()
                .product(Product.builder().name(faker1.medical().medicineName()).brand(faker2.company().name()).build())
//                .order(order)
                .quantity(faker1.number().randomDigit())
//                .serialNumber(faker1.number().toString().replaceAll("[\\s\\-().]", ""))
                .build();

    }
}

