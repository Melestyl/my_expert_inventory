package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Minute;
import org.example.my_expert_inventory.model.EtatDesLieux;
import org.example.my_expert_inventory.model.Element;

import org.example.my_expert_inventory.model.Minute;


import jakarta.persistence.EntityManager;
import java.util.List;

public class MinuteDAO {
    private EntityManager entityManager;

    public MinuteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Minute findById(int id) {
        return entityManager.find(Minute.class, id);
    }

    public List<Minute> findByEtatDesLieux(EtatDesLieux etatDesLieux) {
        return entityManager.createQuery("SELECT m FROM Minute m WHERE m.idEtatDesLieux = :etatDesLieux", Minute.class)
                .setParameter("etatDesLieux", etatDesLieux)
                .getResultList();
    }

    public List<Element> findByElement(Element element) {
        return entityManager.createQuery("SELECT m FROM Minute m WHERE m.idElement = :element", Element.class)
                .setParameter("element", element)
                .getResultList();
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
