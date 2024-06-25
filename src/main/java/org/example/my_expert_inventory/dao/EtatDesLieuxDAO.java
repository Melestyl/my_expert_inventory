package org.example.my_expert_inventory.dao;

import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.EtatDesLieux;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EtatDesLieuxDAO {
    private EntityManager entityManager;

    public EtatDesLieuxDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EtatDesLieux findById(int id) {
        return entityManager.find(EtatDesLieux.class, id);
    }

    public List<EtatDesLieux> findEtatDesLieuxByBien(Bien bien) {
        return entityManager.createQuery("SELECT DISTINCT e FROM Minute m JOIN EtatDesLieux e on m.idEtatDesLieux.id = e.id JOIN Element el ON m.idElement.id = el.id JOIN Piece p ON el.idPiece.id = p.id JOIN Bien b ON p.idBien.id = b.id WHERE b.id = :id", EtatDesLieux.class)
                .setParameter("id", bien.getId())
                .getResultList();
    }

    public EtatDesLieux create(EtatDesLieux etatDesLieux) {
        entityManager.getTransaction().begin();
        entityManager.persist(etatDesLieux);
        entityManager.getTransaction().commit();
        return etatDesLieux;
    }

    public EtatDesLieux update(EtatDesLieux etatDesLieux) {
        entityManager.getTransaction().begin();
        EtatDesLieux updatedEtatDesLieux = entityManager.merge(etatDesLieux);
        entityManager.getTransaction().commit();
        return updatedEtatDesLieux;
    }

    public void delete(EtatDesLieux etatDesLieux) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(etatDesLieux) ? etatDesLieux : entityManager.merge(etatDesLieux));
        entityManager.getTransaction().commit();
    }
}
