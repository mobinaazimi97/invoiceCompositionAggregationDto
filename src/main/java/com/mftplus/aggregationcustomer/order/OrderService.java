package com.mftplus.aggregationcustomer.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Order saveOrder(Order order);
    void deleteOrder(Long id);
    Page<Order> getAllOrders(Pageable pageable);
}
