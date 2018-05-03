package com.haka.stepup.db;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    public void create(User user) {
        entityManager.persist(user);
    }


    public void update(User apiRequest) {
        entityManager.merge(apiRequest);
    }


    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getAllUsers(){
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
    }


    public void delete(long id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
