package org.example.my_expert_inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TypeDElement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}