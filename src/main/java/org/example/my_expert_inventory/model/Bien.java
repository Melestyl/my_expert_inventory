package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

@Entity
public class Bien {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "typeDeBien", nullable = false)
    private String typeDeBien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idAdresse", nullable = false)
    private Adresse idAdresse;

    @Column(name = "proprietaire", nullable = false, length = 50)
    private String proprietaire;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeDeBien() {
        return typeDeBien;
    }

    public void setTypeDeBien(String typeDeBien) {
        this.typeDeBien = typeDeBien;
    }

    public Adresse getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Adresse idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

}