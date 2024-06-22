package org.example.my_expert_inventory.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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

    public Adresse findByAdresse(int numeroRue, String rue, int codePostal, String ville, String complementAdresse) {
        TypedQuery<Adresse> query = entityManager.createQuery("SELECT a FROM Adresse a WHERE a.numeroRue = :numeroRue AND a.rue = :rue AND a.codePostal = :codePostal AND a.ville = :ville AND a.complementAdresse = :complementAdresse", Adresse.class)
                .setParameter("numeroRue", numeroRue)
                .setParameter("rue", rue)
                .setParameter("codePostal", codePostal)
                .setParameter("ville", ville)
                .setParameter("complementAdresse", complementAdresse);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
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

    public void delete(Adresse adresse) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(adresse) ? adresse : entityManager.merge(adresse));
        entityManager.getTransaction().commit();
    }
}
