package com.mftplus.aggregationcustomer.dto.orderItemProductDto;


import com.mftplus.aggregationcustomer.orderItem.OrderItem;
import com.mftplus.aggregationcustomer.orderItem.OrderItemService;
import com.mftplus.aggregationcustomer.product.Product;
import com.mftplus.aggregationcustomer.product.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/itemProducts")
public class ItemProductDtoController {
    private final OrderItemService orderItemService;
    private final ProductService productService;

    public ItemProductDtoController(OrderItemService orderItemService, ProductService productService) {
        this.orderItemService = orderItemService;
        this.productService = productService;
    }

    @GetMapping
    public String itemProductForm(Model mode) {
        mode.addAttribute("orderItemProductDto", new OrderItemProductDto());
        return "itemProducts";
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public String saveItemProduct(OrderItemProductDto orderItemProductDto) {

        Product product = orderItemProductDto.getProduct();
        OrderItem orderItem = orderItemProductDto.addItem(orderItemProductDto.getOrderItem()).get(0);
        product.getOrderItemList().add(orderItem);
        productService.saveProduct(product);

        orderItem.setProduct(product);
        orderItemService.saveOrderItem(orderItemProductDto.getOrderItem());

        product.setOrderItemList(List.of(orderItem));
        productService.updateProduct(product);

        return "redirect:/itemProducts";
    }
}
