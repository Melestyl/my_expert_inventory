package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Minute;
import jakarta.persistence.EntityManager;

public class MinuteDAO {
    private EntityManager entityManager;

    public MinuteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Minute findById(int id) {
        return entityManager.find(Minute.class, id);
    }

    public Minute create(Minute minute) {
        entityManager.getTransaction().begin();
        entityManager.persist(minute);
        entityManager.getTransaction().commit();
        return minute;
    }

    public Minute update(Minute minute) {
        entityManager.getTransaction().begin();
        Minute updatedMinute = entityManager.merge(minute);
        entityManager.getTransaction().commit();
        return updatedMinute;
    }

    public void delete(int id) {
        Minute minute = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(minute) ? minute : entityManager.merge(minute));
        entityManager.getTransaction().commit();
    }
}
