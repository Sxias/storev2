package com.metacoding.storev2.order;


import com.metacoding.storev2.store.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {
    private final EntityManager em;


    public List<OrderResponse.OrderDTO> findAllOrders() {
        String sql = """
        select ot.id, st.name, ot.qty, ot.total_price
        from order_tb ot inner join store_tb st
        on ot.store_id = st.id
        order by ot.id desc;
        """;
        Query q = em.createNativeQuery(sql);
        List<Object[]> orderList = q.getResultList();
        List<OrderResponse.OrderDTO> orderDTOList = new ArrayList<>();

        for(Object[] o : orderList) {
            OrderResponse.OrderDTO dto = new OrderResponse.OrderDTO(
                    ((Number) o[0]).intValue(),
                    (String) o[1],
                    ((Number) o[2]).intValue(),
                    ((Number) o[3]).intValue()
            );
            orderDTOList.add(dto);
        }

        return orderDTOList;
    }

    public void saveOrder(int storeId, int userId, int qty, int totalPrice) {
        Query q = em.createNativeQuery("insert into order_tb(store_id, user_id, qty, total_price) values(?, ?, ?, ?);");
        q.setParameter(1, storeId);
        q.setParameter(2, userId);
        q.setParameter(3, qty);
        q.setParameter(4, totalPrice);
        q.executeUpdate();
    }

    public void updateStock(int storeId, int stock) {
        Query q = em.createNativeQuery("update store_tb set stock = ? where id = ?;");
        q.setParameter(1, stock);
        q.setParameter(2, storeId);
        q.executeUpdate();
    }
}
