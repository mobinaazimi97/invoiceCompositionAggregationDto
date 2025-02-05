//Aggregation :

package com.mftplus.aggregationcustomer.dto.orderItemProductDto;

import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import com.mftplus.aggregationcustomer.product.Product;

import java.util.ArrayList;
import java.util.List;


public class OrderItemProductDto {

    private Long productId;
    private String name;
    private String brand;
    private double productPrice;


    private Long orderItemId;
    private int quantity;


    public OrderItem getOrderItem() {

        return OrderItem.builder()
                .id(orderItemId)
                .quantity(quantity)
                .build();
    }
    public Product getProduct() {
        return Product.builder()
                .id(productId)
                .name(name)
                .brand(brand)
                .price(productPrice)
                .build();
    }
    public List<OrderItem> addItem(OrderItem orderItem) {
        List<OrderItem>orderItemList=new ArrayList<>();
        orderItemList.add(orderItem);
        return orderItemList;
    }

}
