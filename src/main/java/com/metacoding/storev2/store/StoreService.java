package com.metacoding.storev2.store;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

}
