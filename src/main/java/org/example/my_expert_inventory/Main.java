package org.example.my_expert_inventory;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.my_expert_inventory.model.Adresse;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Projet_POO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Adresse adresse = new Adresse();
        adresse.setId(3);
        adresse.setNumeroRue(1);
        adresse.setRue("rue de la paix");
        adresse.setCodePostal(75000);
        adresse.setVille("Paris");
        adresse.setComplementAdresse("appartement 1");
        ((EntityManager) entityManager).getTransaction().begin();
        entityManager.persist(adresse);
        entityManager.getTransaction().commit();

    }
}