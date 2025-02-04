package com.mftplus.aggregationcustomer.orderItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);
    void deleteOrderItem(Long id);
    Page<OrderItem>getAllOrderItems(Pageable pageable);
}
