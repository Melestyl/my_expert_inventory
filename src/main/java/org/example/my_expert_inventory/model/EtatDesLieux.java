package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class EtatDesLieux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "typeEtatDesLieux", nullable = false)
    private String typeEtatDesLieux;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public String getTypeEtatDesLieux() {
        return typeEtatDesLieux;
    }

    public void setTypeEtatDesLieux(String typeEtatDesLieux) {
        this.typeEtatDesLieux = typeEtatDesLieux;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EtatDesLieux{" +
                "id=" + id +
                ", typeEtatDesLieux='" + typeEtatDesLieux + '\'' +
                ", date=" + date +
                '}';
    }

}