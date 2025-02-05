package com.mftplus.aggregationcustomer.dto.orderOrderItemDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderOrderItems")
@Slf4j
public class OrderOrderItemController {

    private final OrderOrderItemDtoService orderOrderItemDtoService;

    public OrderOrderItemController(OrderOrderItemDtoService orderOrderItemDtoService) {
        this.orderOrderItemDtoService = orderOrderItemDtoService;
    }

    @GetMapping
    public String orderHome(Model model) {
        model.addAttribute("orderOrderItemDto", new OrderOrderItemDto());
        return "orderOrderItems";

    }

    @PostMapping
    public String saveOrder(OrderOrderItemDto orderOrderItemDto) {
        orderOrderItemDtoService.create(orderOrderItemDto);
        log.info("Saved Order: {}", orderOrderItemDto);
        return "redirect:/orderOrderItems";

    }

}
