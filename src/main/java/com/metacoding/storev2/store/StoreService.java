package com.metacoding.storev2.store;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public List<Store> 전체상품보기() {
        return storeRepository.findAll();
    }

    @Transactional
    public void 상품추가(String name, int stock, int price) {
        storeRepository.save(name, stock, price);
    }
}
