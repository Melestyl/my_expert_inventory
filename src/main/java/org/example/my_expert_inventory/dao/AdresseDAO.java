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

    public Adresse findByAdresse(int numeroRue, String rue, int codePostal, String ville, String complementAdresse) {
        return entityManager.createQuery("SELECT a FROM Adresse a WHERE a.numeroRue = :numeroRue AND a.rue = :rue AND a.codePostal = :codePostal AND a.ville = :ville AND a.complementAdresse = :complementAdresse", Adresse.class)
                .setParameter("numeroRue", numeroRue)
                .setParameter("rue", rue)
                .setParameter("codePostal", codePostal)
                .setParameter("ville", ville)
                .setParameter("complementAdresse", complementAdresse)
                .getSingleResult();
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
