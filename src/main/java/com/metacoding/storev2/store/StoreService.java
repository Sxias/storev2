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

    public Store 상품수정화면(int id) {
        return storeRepository.findDetailByID(id);
    }

    public Store 상세보기(int id) {
        return storeRepository.findDetailByID(id);
    }

    @Transactional
    public void 상품수정(int id, String name, Integer stock, Integer price) {
        storeRepository.UpdateByID(id, name, stock, price);
    }

    @Transactional
    public void 상품삭제(int id) {
        storeRepository.deleteById(id);
    }
}
