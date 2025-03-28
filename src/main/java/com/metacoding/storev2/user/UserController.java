package com.metacoding.storev2.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/user/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/user/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    // TODO : join

    // TODO : login
}
