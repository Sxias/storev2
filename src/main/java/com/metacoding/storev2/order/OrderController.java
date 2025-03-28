package com.metacoding.storev2.order;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/order/list")
    public String orderList(HttpServletRequest request) {
        // TODO : 주문 내역 id 역순으로 받아서 model(request)에 담아서 보내기
        List<OrderResponse.OrderDTO> orderList = orderService.주문내역();
        request.setAttribute("models", orderList);
        return "order/list";
    }

    // TODO : order
    @PostMapping("/order/save")
    public String order(@RequestParam int storeId, @RequestParam int qty) {
        orderService.상품주문(storeId, qty);
        return "redirect:/order/list";
    }
}
