package com.metacoding.storev2.order;


import com.metacoding.storev2.user.User;
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
    public String orderList(HttpServletRequest request, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        // TODO : 주문 내역 id 역순으로 받아서 model(request)에 담아서 보내기
        List<OrderResponse.OrderDTO> orderList = orderService.주문내역();
        request.setAttribute("models", orderList);
        return "order/list";
    }

    // TODO : order
    @PostMapping("/order/save")
    public String order(@RequestParam int storeId, @RequestParam int qty, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        orderService.상품주문(storeId, qty, validatedUser.getId());
        return "redirect:/order/list";
    }
}
