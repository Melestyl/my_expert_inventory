package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

@Entity
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "typeDePiece", nullable = false)
    private String typeDePiece;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idBien", nullable = false)
    private Bien idBien;

    @Column(name = "surface", nullable = false)
    private Integer surface;

    @Column(name = "ordreVisite", nullable = false)
    private Integer ordreVisite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeDePiece() {
        return typeDePiece;
    }

    public void setTypeDePiece(String typeDePiece) {
        this.typeDePiece = typeDePiece;
    }

    public Bien getIdBien() {
        return idBien;
    }

    public void setIdBien(Bien idBien) {
        this.idBien = idBien;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Integer getOrdreVisite() {
        return ordreVisite;
    }

    public void setOrdreVisite(Integer ordreVisite) {
        this.ordreVisite = ordreVisite;
    }

}