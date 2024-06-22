package org.example.my_expert_inventory.model;

import jakarta.persistence.*;

@Entity
public class TypeDElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeDElement{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

}