package com.mftplus.aggregationcustomer.orderItem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/newOrderItems")
@Slf4j
public class NewOrderItemController {

    private final OrderItemService orderItemService;

    public NewOrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
//    @Autowired
//    private OrderItemService orderItemService;


    @GetMapping
    public String getOrderItems(Model model) {
        model.addAttribute("orderItem", new OrderItem());
        return "orderItem/newOrderItems";
    }


    //TODO : ERROR->415
    //RestController(JSON) :
    @PostMapping
    @ResponseBody
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
        try {
            orderItemService.saveOrderItem(orderItem);
            log.info("OrderItem added: " + orderItem);
            return orderItem;
//            return ResponseEntity.ok(
//                    Map.of(
//                                    "info", "Customer added",
//                                    "data", customer.toString())
//                            .toString());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
//            return ResponseEntity.badRequest().body(
//                    Map.of(
//                                    "error", "Customer not added",
//                                    "message", e.getMessage())
//                            .toString()
//            );
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
