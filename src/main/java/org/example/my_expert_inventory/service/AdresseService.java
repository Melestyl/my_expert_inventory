package org.example.my_expert_inventory.service;

import org.example.my_expert_inventory.dao.AdresseDAO;
import org.example.my_expert_inventory.model.Adresse;


public class AdresseService {
    private AdresseDAO adresseDAO;

    public AdresseService(AdresseDAO adresseDAO) {
        this.adresseDAO = adresseDAO;
    }

    public boolean isAdresseAlreadyExist(Integer numeroRue, String rue, Integer codePostal, String ville, String complementAdresse) {
        // Check if the address is already used
        Adresse adresse = adresseDAO.findByAdresse(numeroRue, rue, codePostal, ville, complementAdresse);
        // If the list is empty, the address is not used
        return adresse != null;
    }

    public Adresse createAdresse(Integer numeroRue, String rue, Integer codePostal, String ville, String complementAdresse) {
        if (isAdresseAlreadyExist(numeroRue, rue, codePostal, ville, complementAdresse)) {
            System.out.println("Adresse already exist");
            return null;
        }
        // Create a new address
        Adresse adresse = new Adresse();
        adresse.setNumeroRue(numeroRue);
        adresse.setRue(rue);
        adresse.setCodePostal(codePostal);
        adresse.setVille(ville);
        adresse.setComplementAdresse(complementAdresse);
        adresseDAO.create(adresse);
        System.out.println(adresse.toString());
        return adresse;
    }

}
