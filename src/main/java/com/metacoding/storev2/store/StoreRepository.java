package com.metacoding.storev2.store;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class StoreRepository {
    private final EntityManager em;


    public List<Store> findAll() {
        Query q = em.createNativeQuery("select * from store_tb order by id desc", Store.class);
        List<Store> storeList = q.getResultList();
        return storeList;
    }

    public void save(String name, int stock, int price) {
        Query q = em.createNativeQuery("insert into store_tb(name, stock, price) values(?, ?, ?)");
        q.setParameter(1, name);
        q.setParameter(2, stock);
        q.setParameter(3, price);
        q.executeUpdate();
    }
}
