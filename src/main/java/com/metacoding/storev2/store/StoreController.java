package com.metacoding.storev2.store;

import com.metacoding.storev2.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/store/list")
    public String list(HttpServletRequest request, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        // TODO : 전체 상품 리스트 받아와서 model(request)에 담고 보내기
        List<Store> storeList = storeService.전체상품보기();
        request.setAttribute("models", storeList);
        return "store/list";
    }

    @GetMapping("/store/{id}/detail")
    public String detail(@PathVariable int id, HttpServletRequest request, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        // TODO : 상품 id 받아와서 상세 정보 받아와서 model(request)에 담고 보내기
        Store detail = storeService.상세보기(id);
        request.setAttribute("model", detail);
        return "store/detail";
    }

    @GetMapping("/store/save-form")
    public String saveForm(HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        return "store/save-form";
    }

    // TODO : save
    @PostMapping("/store/save")
    public String save(StoreRequest.SaveDTO saveDTO, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        storeService.상품추가(saveDTO.getName(), saveDTO.getStock(), saveDTO.getPrice());
        return "redirect:/store/list";
    }

    @GetMapping("/store/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        // TODO : 상품 id 받아와서 상세 정보 받아와서 model(request)에 담고 보내기
        Store updateDTO = storeService.상품수정화면(id);
        request.setAttribute("model", updateDTO);
        return "store/update-form";
    }

    // TODO : update
    @PostMapping("/store/{id}/update")
    public String update(StoreRequest.SaveDTO saveDTO, @PathVariable int id, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        storeService.상품수정(id, saveDTO.getName(), saveDTO.getStock(), saveDTO.getPrice());
        return "redirect:/store/"+id+"/detail";
    }

    // TODO : delete
    @PostMapping("/store/{id}/delete")
    public String delete(@PathVariable int id, HttpSession session) {
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        User validatedUser = (User) session.getAttribute("sessionUser");
        if (validatedUser == null) throw new RuntimeException("로그인 세션이 만료되었습니다. 다시 로그인해 주세요.");
        storeService.상품삭제(id);
        return "redirect:/store/list";
    }
}
