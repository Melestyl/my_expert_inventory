package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "numeroRue", nullable = false)
    private Integer numeroRue;

    @Column(name = "rue", nullable = false, length = 20)
    private String rue;

    @Column(name = "codePostal", nullable = false)
    private Integer codePostal;

    @Column(name = "ville", nullable = false, length = 20)
    private String ville;

    @Column(name = "complementAdresse", length = 20)
    private String complementAdresse;

    public Integer getId() {
        return id;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getComplementAdresse() {
        return complementAdresse;
    }

    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", numeroRue=" + numeroRue +
                ", rue='" + rue + '\'' +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", complementAdresse='" + complementAdresse + '\'' +
                '}';
    }
}