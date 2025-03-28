package com.metacoding.storev2.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;


    public void join(String username, String password, String fullname) {
        Query q = em.createNativeQuery("insert into user_tb(username, password, fullname, created_at) values (?, ?, ?, now())");
        q.setParameter(1, username);
        q.setParameter(2, password);
        q.setParameter(3, fullname);
        q.executeUpdate();
    }

    public User findByUsername(String username) {
        Query q = em.createNativeQuery("select * from user_tb where username = ?", User.class);
        q.setParameter(1, username);
        try {
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
