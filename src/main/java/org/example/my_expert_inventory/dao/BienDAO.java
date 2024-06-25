package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.Adresse;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BienDAO {
    private EntityManager entityManager;

    public BienDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Bien> findAll() {
        return entityManager.createQuery("SELECT b FROM Bien b", Bien.class).getResultList();
    }

    public Bien findById(int id) {
        return entityManager.find(Bien.class, id);
    }

    public List<Bien> findByAdresse(Adresse adresse) {
        return entityManager.createQuery("SELECT b FROM Bien b WHERE b.idAdresse = :adresse", Bien.class)
                .setParameter("adresse", adresse)
                .getResultList();
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

    public void delete(Bien bien) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(bien) ? bien : entityManager.merge(bien));
        entityManager.getTransaction().commit();
    }
}
