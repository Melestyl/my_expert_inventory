package org.example.my_expert_inventory.dao;

import jakarta.persistence.TypedQuery;
import org.example.my_expert_inventory.model.Adresse;
import org.example.my_expert_inventory.model.Element;
import org.example.my_expert_inventory.model.TypeDElement;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TypeDElementDAO {
    private EntityManager entityManager;

    public TypeDElementDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TypeDElement findById(int id) {
        return entityManager.find(TypeDElement.class, id);
    }

    public List<TypeDElement> findAll() {
        TypedQuery<TypeDElement> query = entityManager.createQuery("SELECT t FROM TypeDElement t", TypeDElement.class);
        return query.getResultList();
    }

    public TypeDElement findByType(String type) {
        TypedQuery<TypeDElement> query  = entityManager.createQuery("SELECT t FROM TypeDElement t WHERE t.type = :type", TypeDElement.class)
                .setParameter("type", type);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
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

    public void delete(TypeDElement typeDElement) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(typeDElement) ? typeDElement : entityManager.merge(typeDElement));
        entityManager.getTransaction().commit();
    }
}
