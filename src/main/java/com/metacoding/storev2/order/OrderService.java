package com.metacoding.storev2.order;


import com.metacoding.storev2.store.Store;
import com.metacoding.storev2.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    public List<OrderResponse.OrderDTO> 주문내역() {
        return orderRepository.findAllOrders();
    }

    @Transactional
    public void 상품주문(int storeId, int qty, int userId) {
        // 1. 상품 조회
        Store product = storeRepository.findDetailByID(storeId);
        // 1-1. 상품의 남은 재고가 qty보다 작을 경우 break
        if(product.getStock() < qty) throw new RuntimeException("재고가 부족합니다.");
        // 2. 계산
        int remainStock = product.getStock() - qty;
        int totalPrice = product.getPrice() * qty;
        // 3. 구매
        orderRepository.updateStock(storeId, remainStock);
        orderRepository.saveOrder(storeId, userId, qty, totalPrice);

    }
}
