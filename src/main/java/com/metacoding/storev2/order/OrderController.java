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
        // TODO : 주문 내역 id 역순으로 받아서 model(request)에 담아서 보내기
        return "order/list";
    }

    // TODO : order
}
