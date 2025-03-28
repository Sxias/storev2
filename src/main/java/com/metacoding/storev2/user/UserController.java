package com.metacoding.storev2.user;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        if(joinDTO.getUsername().length() > 12) throw new RuntimeException("아이디가 너무 깁니다.");
        userService.회원가입(joinDTO);
        return "user/login-form";
    }

    // TODO : login
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO, HttpSession session) {
        User userinfo = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", userinfo);
        return "redirect:/";
    }

    // TODO : logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
