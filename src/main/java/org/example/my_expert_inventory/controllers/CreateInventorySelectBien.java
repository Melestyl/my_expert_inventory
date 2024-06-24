package org.example.my_expert_inventory.controllers;

import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.service.BienService;

import java.util.List;

public class CreateInventorySelectBien {

    List<Bien> biens;


    @FXML
    // when page load, take list of biens from database and display them
    protected void initialize() {
        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        biens = bienService.findAllBiens();

    }
}
