package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Element;
import jakarta.persistence.EntityManager;

public class ElementDAO {
    private EntityManager entityManager;

    public ElementDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Element findById(Long id) {
        return entityManager.find(Element.class, id);
    }

    public Element create(Element element) {
        entityManager.getTransaction().begin();
        entityManager.persist(element);
        entityManager.getTransaction().commit();
        return element;
    }

    public Element update(Element element) {
        entityManager.getTransaction().begin();
        Element updatedElement = entityManager.merge(element);
        entityManager.getTransaction().commit();
        return updatedElement;
    }

    public void delete(Element element) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(element) ? element : entityManager.merge(element));
        entityManager.getTransaction().commit();
    }
}
