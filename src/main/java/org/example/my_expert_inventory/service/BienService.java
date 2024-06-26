package org.example.my_expert_inventory.service;

import jakarta.persistence.Persistence;
import org.example.my_expert_inventory.dao.AdresseDAO;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.model.Adresse;
import org.example.my_expert_inventory.model.Bien;

import java.util.List;

public class BienService {
    public enum TypeDeBien {
        APPARTEMENT,
        MAISON,
        CHATEAU,
        STUDIO,
        ECOLE,
    }
    private BienDAO bienDAO;

    public BienService(BienDAO bienDAO) {
        this.bienDAO = bienDAO;
    }

    public List<Bien> findAllBiens() {
        return bienDAO.findAll();
    }

    public boolean isBienAlreadyUsed(Adresse adresse) {
        // Check if the address is already used
        List<Bien> biens = bienDAO.findByAdresse(adresse);
        // If the list is empty, the address is not used
        return !biens.isEmpty();
    }

    public Bien getBienById(int id) {
        return bienDAO.findById(id);
    }

    public Bien createBien(TypeDeBien typeDeBien, String proprietaire, Adresse adresse) {
        // Check if the address is already used
        if(isBienAlreadyUsed(adresse)) {
            System.out.println("Adresse already used, cannot create a new property");
            return null;
        }

        // Create a new property
        Bien bien = new Bien();
        bien.setTypeDeBien(typeDeBien.toString());
        bien.setProprietaire(proprietaire);
        bien.setIdAdresse(adresse);
        bienDAO.create(bien);
        return bien;
    }

    public Bien creatBienWithAdresse(TypeDeBien typeDeBien, String proprietaire, Integer numeroRue, String rue, Integer codePostal, String ville, String complementAdresse) {
        // Create a new address
        AdresseService adresseService = new AdresseService(new AdresseDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        Adresse adresse = adresseService.createAdresse(numeroRue, rue, codePostal, ville, complementAdresse);

        // Create a new property with the address
        Bien bien = createBien(typeDeBien, proprietaire, adresse);
        if (bien == null) {
            return null;
        }
        System.out.println(bien.toString());
        // Create a new property with the address
        return bien;
    }

    public static void main(String[] args) {
        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        bienService.creatBienWithAdresse(TypeDeBien.ECOLE, "Tom Tellier-Caloone", 4, "rue de l'IG2I", 75000, "Paris", "");
        bienService.creatBienWithAdresse(TypeDeBien.APPARTEMENT, "Anaël Delannoy", 18, "rue du Crous", 75000, "Paris", "");
    }
}
