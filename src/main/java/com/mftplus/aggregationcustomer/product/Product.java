package com.mftplus.aggregationcustomer.product;

import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "productEntity")
@Table(name = "product_tbl")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @NotBlank(message = "{validation.name}")
    private String name;
    //    @NotBlank(message = "{validation.brand}")
    private String brand;
//    @Pattern(regexp = "^[0-9]{2,}$", message = "{validation.serialNumber}")
//    private String serialNumber;
//    @NotBlank(message = "{validation.price}")

    private double price;

    @OneToMany
    private List<OrderItem> orderItemList;


    //        public void addAllItem(OrderItem ... orderItems) {
//        if (orderItemList == null) {
//            orderItemList = new ArrayList<>();
//        }
//        Collections.addAll(orderItemList, orderItems);
//    }

//    public List<OrderItem> addItem(OrderItem orderItem) {
//        if (orderItemList == null) {
//            orderItemList = new ArrayList<>();
//        }
//        orderItemList.add(orderItem);
//        return orderItemList;
//    }
}
