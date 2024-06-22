package org.example.my_expert_inventory;


import eu.hansolo.tilesfx.Test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.my_expert_inventory.model.*;
import org.example.my_expert_inventory.dao.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Projet_POO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //TestAdresse(entityManager);
        //TestAdresse(entityManager);

        //TestBien(entityManager);
        //TestBien(entityManager);

        entityManager.close();
    }

    private static void TestAdresse(EntityManager entityManager) {
        //AddressDAO
        AdresseDAO adresseDAO = new AdresseDAO(entityManager);

        Adresse adresse = new Adresse();
            adresse.setNumeroRue(2);
            adresse.setRue("rue de la paix");
            adresse.setCodePostal(75000);
            adresse.setVille("Paris");
            adresse.setComplementAdresse("");
            adresseDAO.create(adresse);
        Adresse adresse1 = adresseDAO.findById(1);
        Adresse adresse2 = adresseDAO.findByAdresse(1, "rue de la paix", 75000, "Paris", "");
            System.out.println(adresse1);
            System.out.println(adresse2);
    }

    private static void TestBien(EntityManager entityManager){
        //BienDAO
        AdresseDAO adresseDAO = new AdresseDAO(entityManager);
        BienDAO bienDAO = new BienDAO(entityManager);

        Bien bien = new Bien();
        bien.setTypeDeBien("Maison");
        bien.setIdAdresse(adresseDAO.findById(3));
        bien.setProprietaire("Jean Dupont");
        bienDAO.create(bien);

        Bien bien2 = new Bien();
        bien2.setTypeDeBien("Appartement");
        bien2.setIdAdresse(adresseDAO.findById(3));
        bien2.setProprietaire("Jeanne Dupont");
        bienDAO.create(bien2);

        bienDAO.findByAdresse(adresseDAO.findById(3)).forEach(System.out::println)  ;


    }
}