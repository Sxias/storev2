package com.metacoding.storev2.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/order/list")
    public String orderList() {
        return "order/list";
    }

    // TODO : order
}
