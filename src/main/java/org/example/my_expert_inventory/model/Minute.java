package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

@Entity
public class Minute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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