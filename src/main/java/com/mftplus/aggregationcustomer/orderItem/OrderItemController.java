package com.mftplus.aggregationcustomer.orderItem;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orderItems")
@Slf4j
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public String listOrderItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Boolean fragment,
            Model model) {
        if (size <= 0) size = 10;

        Pageable pageable = PageRequest.of(page, size);
        Page<OrderItem> orderItems = orderItemService.getAllOrderItems(pageable);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", orderItems.getTotalPages());

        return fragment != null && fragment ?
                "orderItem/fragments/orderItems-table :: orderItems-table" : "orderItem/orderItems";
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveOrderItem(
            @Valid @RequestBody OrderItem orderItem,
            BindingResult bindingResult
    ) {
        System.out.println("save");
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            OrderItem savedOrderItem = orderItemService.saveOrderItem(orderItem);
            return ResponseEntity.ok(savedOrderItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }



    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
        try {
            System.out.println("delete");
            orderItemService.deleteOrderItem(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
