package com.mftplus.aggregationcustomer.order;

import com.mftplus.aggregationcustomer.customer.Customer;
import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "orderEntity")
@Table(name = "order_tbl")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @NotBlank(message = "{validation.orderPrice}")
    private double price; //todo-.maybe longs true!!

    //    @NotBlank(message = "{validation.orderDate}")
    private LocalDate orderDate;

//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItemList;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "orders_customers")
    private Customer customer;
    //For Dto
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    private OrderItem orderItem;

}

