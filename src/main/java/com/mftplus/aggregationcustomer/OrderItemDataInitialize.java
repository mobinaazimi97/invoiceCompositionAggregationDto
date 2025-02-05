package com.mftplus.aggregationcustomer;

import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import com.mftplus.aggregationcustomer.orderItem.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderItemDataInitialize implements CommandLineRunner{

    private final OrderItemFakeDataService orderItemFakeDataService;
    private final OrderItemService orderItemService;

    public OrderItemDataInitialize(OrderItemFakeDataService orderItemFakeDataService, OrderItemService orderItemService) {
        this.orderItemFakeDataService = orderItemFakeDataService;
        this.orderItemService = orderItemService;
    }


    @Override
    public void run(String... args) throws Exception {
        int n = 25;
        for (int i = 1; i <= n; i++) {
            OrderItem orderItem = orderItemFakeDataService.createFakeOrderItem();
            orderItemService.saveOrderItem(orderItem);
        }
        log.info("{} OrderItems Created", n);
    }

}
