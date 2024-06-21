package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.EtatDesLieux;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EtatDesLieuxDAO {
    private EntityManager entityManager;

    public EtatDesLieuxDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EtatDesLieux findById(int id) {
        return entityManager.find(EtatDesLieux.class, id);
    }

    public List<EtatDesLieux> findByTypeEtatDesLieux(String typeEtatDesLieux) {
        return entityManager.createQuery("SELECT e FROM EtatDesLieux e WHERE e.typeEtatDesLieux = :typeEtatDesLieux", EtatDesLieux.class)
                .setParameter("typeEtatDesLieux", typeEtatDesLieux)
                .getResultList();
    }

    public EtatDesLieux create(EtatDesLieux etatDesLieux) {
        entityManager.getTransaction().begin();
        entityManager.persist(etatDesLieux);
        entityManager.getTransaction().commit();
        return etatDesLieux;
    }

    public EtatDesLieux update(EtatDesLieux etatDesLieux) {
        entityManager.getTransaction().begin();
        EtatDesLieux updatedEtatDesLieux = entityManager.merge(etatDesLieux);
        entityManager.getTransaction().commit();
        return updatedEtatDesLieux;
    }

    public void delete(EtatDesLieux etatDesLieux) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(etatDesLieux) ? etatDesLieux : entityManager.merge(etatDesLieux));
        entityManager.getTransaction().commit();
    }
}
