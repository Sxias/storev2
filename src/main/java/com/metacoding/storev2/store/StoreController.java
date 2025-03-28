package com.metacoding.storev2.store;

import jakarta.servlet.http.HttpServletRequest;
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
        // TODO : 유저 정보 확인하기 (세션 : 부가 로직)
        return "home";
    }

    @GetMapping("/store/list")
    public String list(HttpServletRequest request) {
        // TODO : 전체 상품 리스트 받아와서 model(request)에 담고 보내기
        List<Store> storeList = storeService.전체상품보기();
        request.setAttribute("models", storeList);
        return "store/list";
    }

    @GetMapping("/store/{id}/detail")
    public String detail(@PathVariable int id) {
        // TODO : 상품 id 받아와서 상세 정보 받아와서 model(request)에 담고 보내기
        return "store/detail";
    }

    @GetMapping("/store/save-form")
    public String saveForm() {
        return "store/save-form";
    }

    // TODO : save
    @PostMapping("/store/save")
    public String save(StoreRequest.SaveDTO saveDTO) {
        storeService.상품추가(saveDTO.getName(), saveDTO.getStock(), saveDTO.getPrice());
        return "redirect:/store/list";
    }

    @GetMapping("/store/{id}/update-form")
    public String updateForm(@PathVariable int id) {
        // TODO : 상품 id 받아와서 상세 정보 받아와서 model(request)에 담고 보내기
        return "store/update-form";
    }

    // TODO : update
}
