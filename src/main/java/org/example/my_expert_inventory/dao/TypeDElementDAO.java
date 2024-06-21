package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.TypeDElement;
import jakarta.persistence.EntityManager;

public class TypeDElementDAO {
    private EntityManager entityManager;

    public TypeDElementDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TypeDElement findById(int id) {
        return entityManager.find(TypeDElement.class, id);
    }

    public TypeDElement create(TypeDElement typeDElement) {
        entityManager.getTransaction().begin();
        entityManager.persist(typeDElement);
        entityManager.getTransaction().commit();
        return typeDElement;
    }

    public TypeDElement update(TypeDElement typeDElement) {
        entityManager.getTransaction().begin();
        TypeDElement updatedTypeDElement = entityManager.merge(typeDElement);
        entityManager.getTransaction().commit();
        return updatedTypeDElement;
    }

    public void delete(int id) {
        TypeDElement typeDElement = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(typeDElement) ? typeDElement : entityManager.merge(typeDElement));
        entityManager.getTransaction().commit();
    }
}
