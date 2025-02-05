package com.mftplus.aggregationcustomer.dto.orderOrderItemDto;

import com.mftplus.aggregationcustomer.order.Order;
import com.mftplus.aggregationcustomer.order.OrderService;
import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import com.mftplus.aggregationcustomer.orderItem.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderOrderItemDtoService {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public OrderOrderItemDtoService(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @Transactional(rollbackOn = Exception.class)
    public void create(OrderOrderItemDto orderOrderItemDto) {

        Order order = orderOrderItemDto.getOrder();
        OrderItem orderItem = orderOrderItemDto.getOrderItem();
        orderService.saveOrder(order);

        orderItem.setOrder(order);
        orderItemService.saveOrderItem(orderOrderItemDto.getOrderItem());

        order.setOrderItem(orderItem);
        orderService.updateOrder(order);
    }
}
