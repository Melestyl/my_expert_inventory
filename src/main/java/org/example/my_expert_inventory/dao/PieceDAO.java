package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Piece;
import org.example.my_expert_inventory.model.Bien;
import jakarta.persistence.EntityManager;

public class PieceDAO {
    private EntityManager entityManager;

    public PieceDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Piece findById(int id) {
        return entityManager.find(Piece.class, id);
    }

    public Piece findByBienId(int idBien) {
        return entityManager.createQuery("SELECT p FROM Piece p WHERE p.idBien = :idBien", Piece.class)
                .setParameter("idBien", idBien)
                .getSingleResult();
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

    public void delete(Piece piece) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(piece) ? piece : entityManager.merge(piece));
        entityManager.getTransaction().commit();
    }
}
