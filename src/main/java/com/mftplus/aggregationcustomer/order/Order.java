package com.mftplus.aggregationcustomer.order;

import com.mftplus.aggregationcustomer.customer.Customer;
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

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_customers")
    private Customer customer;

}

