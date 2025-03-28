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

//    public StoreResponse.DetailDTO findByID(int id) {
//        Query q = em.createNativeQuery("select * from store_tb where id = ?");
//        q.setParameter(1, id);
//        Object[] o = (Object[]) q.getSingleResult();
//        StoreResponse.DetailDTO detailDTO = new StoreResponse.DetailDTO(
//                (String) o[0],
//                ((Number) o[1]).intValue(),
//                ((Number) o[2]).intValue()
//        );
//        return detailDTO;
//    }

    public Store findDetailByID(int id) {
        Query q = em.createNativeQuery("select * from store_tb where id = ?", Store.class);
        q.setParameter(1, id);
        return (Store) q.getSingleResult();
    }

    public void UpdateByID(int id, String name, Integer stock, Integer price) {
        Query q = em.createNativeQuery("update store_tb set name = ?, stock = ?, price = ? where id = ?");
        q.setParameter(1, name);
        q.setParameter(2, stock);
        q.setParameter(3, price);
        q.setParameter(4, id);
        q.executeUpdate();
    }

    public void deleteById(int id) {
        Query q = em.createNativeQuery("delete from store_tb where id = ?");
        q.setParameter(1, id);
        q.executeUpdate();
    }
}
