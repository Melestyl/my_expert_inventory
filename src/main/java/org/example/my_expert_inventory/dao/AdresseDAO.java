package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Adresse;
import jakarta.persistence.EntityManager;

public class AdresseDAO {
    private EntityManager entityManager;

    public AdresseDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Adresse findById(int id) {
        return entityManager.find(Adresse.class, id);
    }

    public Adresse create(Adresse adresse) {
        entityManager.getTransaction().begin();
        entityManager.persist(adresse);
        entityManager.getTransaction().commit();
        return adresse;
    }

    public Adresse update(Adresse adresse) {
        entityManager.getTransaction().begin();
        Adresse updatedAdresse = entityManager.merge(adresse);
        entityManager.getTransaction().commit();
        return updatedAdresse;
    }

    public void delete(int id) {
        Adresse adresse = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(adresse) ? adresse : entityManager.merge(adresse));
        entityManager.getTransaction().commit();
    }
}
