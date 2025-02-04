package com.mftplus.aggregationcustomer;


import com.mftplus.aggregationcustomer.order.Order;
import com.mftplus.aggregationcustomer.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderDataInitialize implements CommandLineRunner {
    private final OrderService orderService;
    private final OrderFakeDataService orderFakeDataService;

    public OrderDataInitialize(OrderService orderService, OrderFakeDataService orderFakeDataService) {
        this.orderService = orderService;
        this.orderFakeDataService = orderFakeDataService;
    }

    @Override
    public void run(String... args) throws Exception {
        int n = 25;
        for (int i = 1; i <= n; i++) {
            Order order = orderFakeDataService.createFakeOrder();
            orderService.saveOrder(order);
        }
        log.info("{} orders Created",n);

    }
}
