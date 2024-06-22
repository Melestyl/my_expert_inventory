package org.example.my_expert_inventory.service;

import jakarta.persistence.Persistence;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.dao.PieceDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.Piece;

import java.util.List;

public class PieceService {
    public enum TypeDePiece {
        SALON,
        SALLE_A_MANGER,
        CUISINE,
        CHAMBRE,
        SALLE_DE_BAIN,
        TOILETTES,
        BUREAU,
        BUANDERIE,
        ENTREE,
        GARAGE,
        CAVE,
        GRENIER,
        SALLE_DE_JEUX,
        SALLE_DE_SPORT,
        BIBLIOTHEQUE,
        SALLE_DE_CINEMA,
        SALLE_DE_MUSIQUE,
        SALLE_DE_DETENTE,
        DRESSING,
        VERANDA,
        ATELIER,
        SALLE_DE_RECEPTION,
        SALLE_DE_CONFERENCE,
        SALLE_DE_REUNION,
        SERRE,
        SALLE_DE_BAINS,
        CELLIER,
        PATIO,
        TERRASSE,
        BALCON,
        SALLE_D_EAU,
        BARBECUE,
        SAUNA,
        HAMMAM,
        SPA,
        PISCINE_INTERIEURE,
        COUR_INTERIEURE,
        GALERIE_D_ART,
        MEZZANINE,
    }
    private PieceDAO pieceDAO;


    public PieceService(PieceDAO pieceDAO) {
        this.pieceDAO = pieceDAO;
    }

    public Piece pieceById(int id){
        return pieceDAO.findById(id);
    }

    public List<Piece> findPiecesByBien(Bien bien){
        return pieceDAO.findByBien(bien);
    }

    public Piece createPiece(TypeDePiece typeDePiece,Integer surface,Integer ordreVisite, Bien bien) {
        if(bien == null) {
            System.out.println("Bien is null, cannot create a new piece");
            return null;
        }
        // Create a new piece
        Piece piece = new Piece();
        piece.setTypeDePiece(typeDePiece.toString());
        piece.setSurface(surface);
        piece.setOrdreVisite(ordreVisite);
        piece.setIdBien(bien);
        pieceDAO.create(piece);
        return piece;
    }

    public Piece updateSurface(Piece piece, Integer surface) {
        if(piece == null) {
            System.out.println("Piece is null, cannot update the surface");
            return null;
        }
        piece.setSurface(surface);
        return pieceDAO.update(piece);
    }

    public Piece updateOrdreVisite(Piece piece, Integer ordreVisite) {
        if(piece == null) {
            System.out.println("Piece is null, cannot update the order of visit");
            return null;
        }
        piece.setOrdreVisite(ordreVisite);
        return pieceDAO.update(piece);
    }

    public void deletePiece(Piece piece) {
        if(piece == null) {
            System.out.println("Piece is null, cannot delete the piece");
            return;
        }
        pieceDAO.delete(piece);
    }

    public static void main(String[] args) {
        // Create a new piece
        PieceService pieceService = new PieceService(new PieceDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        Bien bien = bienService.creatBienWithAdresse(BienService.TypeDeBien.MAISON, "John Doe", 1, "rue de la paix", 75000, "Paris", "");
        System.out.println("test");
        Piece piece1 = pieceService.createPiece(TypeDePiece.CUISINE, 20, 1, bien);
        Piece piece2 = pieceService.createPiece(TypeDePiece.SALON, 30, 2, bien);
        Piece piece3 = pieceService.createPiece(TypeDePiece.CHAMBRE, 15, 3, bien);
        Piece piece4 = pieceService.createPiece(TypeDePiece.SALLE_DE_BAIN, 10, 4, bien);
        Piece piece5 = pieceService.createPiece(TypeDePiece.CUISINE, 20, 1, bien);
        pieceService.findPiecesByBien(bien).forEach(System.out::println);
        System.out.println(piece2);
        pieceService.updateSurface(piece2, 35);
        pieceService.updateOrdreVisite(piece2, 1);
        System.out.println(piece2);
        pieceService.updateOrdreVisite(piece2, 3);
        System.out.println(piece2);
        pieceService.deletePiece(piece2);
        pieceService.deletePiece(piece2);
        pieceService.findPiecesByBien(bien).forEach(System.out::println);
    }
}
