package com.mftplus.aggregationcustomer.orderItem;

import com.mftplus.aggregationcustomer.order.Order;
import com.mftplus.aggregationcustomer.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "orderItemEntity")
@Table(name = "orderItem_tbl")

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "{validation.quantity}")
    private int quantity;

    @ManyToOne
    private Product product;

    //For Dto
    @OneToOne
    private Order order;

}
