package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Bien;
import jakarta.persistence.EntityManager;

public class PieceDAO {
    private EntityManager entityManager;

    public PieceDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Bien fintById(int id) {
        return entityManager.find(Bien.class, id);
    }

    public Bien create(Bien bien) {
        entityManager.getTransaction().begin();
        entityManager.persist(bien);
        entityManager.getTransaction().commit();
        return bien;
    }

    public Bien update(Bien bien) {
        entityManager.getTransaction().begin();
        Bien updatedBien = entityManager.merge(bien);
        entityManager.getTransaction().commit();
        return updatedBien;
    }

    public void delete(int id) {
        Bien bien = fintById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(bien) ? bien : entityManager.merge(bien));
        entityManager.getTransaction().commit();
    }
}
