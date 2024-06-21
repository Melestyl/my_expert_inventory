package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Piece;
import jakarta.persistence.EntityManager;

public class PieceDAO {
    private EntityManager entityManager;

    public PieceDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Piece findById(int id) {
        return entityManager.find(Piece.class, id);
    }

    public Piece create(Piece piece) {
        entityManager.getTransaction().begin();
        entityManager.persist(piece);
        entityManager.getTransaction().commit();
        return piece;
    }

    public Piece update(Piece piece) {
        entityManager.getTransaction().begin();
        Piece updatedPiece = entityManager.merge(piece);
        entityManager.getTransaction().commit();
        return updatedPiece;
    }

    public void delete(int id) {
        Piece piece = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(piece) ? piece : entityManager.merge(piece));
        entityManager.getTransaction().commit();
    }
}
