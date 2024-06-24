package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.*;


import jakarta.persistence.EntityManager;
import java.util.List;

public class MinuteDAO {
    private EntityManager entityManager;

    public MinuteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Minute> findByEtatDesLieux(EtatDesLieux etatDesLieux) {
        return entityManager.createQuery("SELECT m FROM Minute m WHERE m.idEtatDesLieux = :etatDesLieux", Minute.class)
                .setParameter("etatDesLieux", etatDesLieux)
                .getResultList();
    }

    public List<Minute> findByElement(Element element) {
        return entityManager.createQuery("SELECT m FROM Minute m JOIN m.idElement e WHERE e = :element", Minute.class)
                .setParameter("element", element)
                .getResultList();
    }

    public Minute findByElementAndEtatDesLieux(Element element, EtatDesLieux etatDesLieux) {
        List<Minute> minutes = entityManager.createQuery("SELECT m FROM Minute m JOIN m.idElement e WHERE e = :element AND m.idEtatDesLieux = :etatDesLieux", Minute.class)
                .setParameter("element", element)
                .setParameter("etatDesLieux", etatDesLieux)
                .getResultList();
        if (minutes.isEmpty()) {
            return null;
        }
        return minutes.get(0);
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

    public void delete(Minute minute) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(minute) ? minute : entityManager.merge(minute));
        entityManager.getTransaction().commit();
    }
}
