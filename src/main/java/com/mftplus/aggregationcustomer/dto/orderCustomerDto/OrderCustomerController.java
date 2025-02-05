package com.mftplus.aggregationcustomer.dto.orderCustomerDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderCustomers")
@Slf4j
public class OrderCustomerController {

    private final OrderCustomerDtoService orderCustomerDtoService;

    public OrderCustomerController(OrderCustomerDtoService orderCustomerDtoService) {
        this.orderCustomerDtoService = orderCustomerDtoService;
    }

    @GetMapping
    public String orderHome(Model model){
        model.addAttribute("orderCustomerDto",new OrderCustomerDto());
        return "orderCustomers";
    }

    @PostMapping
    public String saveOrderCustomer(OrderCustomerDto orderCustomerDto){
        orderCustomerDtoService.create(orderCustomerDto);
        log.info("saved order customer {}", orderCustomerDto);
        return "redirect:/orderCustomers";
    }
}
