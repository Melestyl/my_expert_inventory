package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Minute {
    @Id
    @Column(name = "dateHeureMinute", nullable = false)
    private Instant id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idEtatDesLieux", nullable = false)
    private EtatDesLieux idEtatDesLieux;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idElement", nullable = false)
    private Element idElement;

    @Lob
    @Column(name = "etatElement", nullable = false)
    private String etatElement;

    @Column(name = "Commentaire", length = 128)
    private String commentaire;

    public Instant getId() {
        return id;
    }

    public void setId(Instant id) {
        this.id = id;
    }

    public EtatDesLieux getIdEtatDesLieux() {
        return idEtatDesLieux;
    }

    public void setIdEtatDesLieux(EtatDesLieux idEtatDesLieux) {
        this.idEtatDesLieux = idEtatDesLieux;
    }

    public Element getIdElement() {
        return idElement;
    }

    public void setIdElement(Element idElement) {
        this.idElement = idElement;
    }

    public String getEtatElement() {
        return etatElement;
    }

    public void setEtatElement(String etatElement) {
        this.etatElement = etatElement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}