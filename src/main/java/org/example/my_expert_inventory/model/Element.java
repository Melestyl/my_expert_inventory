package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

@Entity
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idTypeElement", nullable = false)
    private TypeDElement idTypeElement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idPiece", nullable = false)
    private Piece idPiece;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeDElement getIdTypeElement() {
        return idTypeElement;
    }

    public void setIdTypeElement(TypeDElement idTypeElement) {
        this.idTypeElement = idTypeElement;
    }

    public Piece getIdPiece() {
        return idPiece;
    }

    public void setIdPiece(Piece idPiece) {
        this.idPiece = idPiece;
    }

}