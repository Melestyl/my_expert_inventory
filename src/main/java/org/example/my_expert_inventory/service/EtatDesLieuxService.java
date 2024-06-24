package org.example.my_expert_inventory.service;

import jakarta.persistence.Persistence;
import org.example.my_expert_inventory.dao.*;
import org.example.my_expert_inventory.model.*;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class EtatDesLieuxService {
    enum TypeEtatDesLieux {
        ENTREE,
        SORTIE
    }
    enum EtatElement {
        MAUVAIS,
        PASSABLE,
        BON,
        TRES_BON,
        NEUF
    }

    private EtatDesLieuxDAO etatDesLieuxDAO;
    private MinuteDAO minuteDAO;

    public EtatDesLieuxService(EtatDesLieuxDAO etatDesLieuxDAO, MinuteDAO minuteDAO) {
        this.etatDesLieuxDAO = etatDesLieuxDAO;
        this.minuteDAO = minuteDAO;
    }

    public List<Minute> findMinutesByEtatDesLieux(EtatDesLieux etatDesLieux){
        if (etatDesLieux == null){
            System.out.println("Etat des lieux is null, cannot find minutes");
            return null;
        }
        return minuteDAO.findByEtatDesLieux(etatDesLieux);
    }

    public List<Minute> findMinutesByElement(Element element){
        if (element == null) {
            System.out.println("Element is null, cannot find minutes");
            return null;
        }
        return minuteDAO.findByElement(element);
    }

    public List<EtatDesLieux> findEtatDesLieuxByBien(Bien bien){
        if (bien == null) {
            System.out.println("Bien is null, cannot find etat des lieux");
            return null;
        }
        return etatDesLieuxDAO.findEtatDesLieuxByBien(bien);
    }

    public EtatDesLieux createEtatDesLieux( TypeEtatDesLieux typeEtatDesLieux){
        if(typeEtatDesLieux == null){
            System.out.println("Type d'élément is null, cannot create etat des lieux");
            return null;
        }
        EtatDesLieux etatDesLieux = new EtatDesLieux();
        etatDesLieux.setDate(LocalDate.now());
        etatDesLieux.setTypeEtatDesLieux(typeEtatDesLieux.toString());
        etatDesLieuxDAO.create(etatDesLieux);
        return etatDesLieux;
    }

    public Minute createMinute(String commentaire, EtatElement etatElement, Element element, EtatDesLieux etatDesLieux) throws InterruptedException {
        if(etatDesLieux == null){
            System.out.println("Etat des lieux is null, cannot create minute");
            return null;
        }
        if(element == null){
            System.out.println("Element is null, cannot create minute");
            return null;
        }
        Minute minute = new Minute();
        minute.setCommentaire(commentaire);
        minute.setEtatElement(etatElement.toString());
        minute.setIdElement(element);
        minute.setIdEtatDesLieux(etatDesLieux);
        minuteDAO.create(minute);
        return minute;
    }

    public Minute updateMinute(Minute minute, String commentaire, EtatElement etatElement){
        if(minute == null){
            System.out.println("Minute is null, cannot update minute");
            return null;
        }
        minute.setCommentaire(commentaire);
        minute.setEtatElement(etatElement.toString());
        return minuteDAO.update(minute);
    }

    public EtatDesLieux updateTypeEtatDesLieux(EtatDesLieux etatDesLieux, TypeEtatDesLieux typeEtatDesLieux){
        if(etatDesLieux == null){
            System.out.println("Etat des lieux is null, cannot update type etat des lieux");
            return null;
        }
        etatDesLieux.setTypeEtatDesLieux(typeEtatDesLieux.toString());
        return etatDesLieuxDAO.update(etatDesLieux);
    }

    public void deleteMinute(Minute minute){
        if(minute == null){
            System.out.println("Minute is null, cannot delete minute");
            return;
        }
        minuteDAO.delete(minute);
    }


    public static void main(String[] args) throws InterruptedException {

        // Create a new piece
        PieceService pieceService = new PieceService(new PieceDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        ElementService elementService = new ElementService(new ElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new TypeDElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));


        Bien bien = bienService.creatBienWithAdresse(BienService.TypeDeBien.MAISON, "John Doe", 1, "rue de la paix", 75000, "Paris", "");
        Piece piece1 = pieceService.createPiece(PieceService.TypeDePiece.SALON, 20, 1, bien);

        EtatDesLieuxService etatDesLieuxService = new EtatDesLieuxService(new EtatDesLieuxDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new MinuteDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));

        TypeDElement typeDElement = elementService.createTypeDElement("Table");
        TypeDElement typeDElement2 = elementService.createTypeDElement("Chaise");
        TypeDElement typeDElement3 = elementService.createTypeDElement("Canapé");

        Element element1 = elementService.createElement(typeDElement, piece1);
        Element element2 = elementService.createElement(typeDElement2, piece1);
        Element element3 = elementService.createElement(typeDElement3, piece1);

        EtatDesLieux etatDesLieux = etatDesLieuxService.createEtatDesLieux(TypeEtatDesLieux.ENTREE);
        Minute minute1 = etatDesLieuxService.createMinute("RAS", EtatElement.BON, element1, etatDesLieux);
        Minute minute2 = etatDesLieuxService.createMinute("RAS", EtatElement.TRES_BON, element2, etatDesLieux);
        Minute minute3 = etatDesLieuxService.createMinute("Trace blanche sur le côté", EtatElement.PASSABLE, element3, etatDesLieux);



        System.out.println(etatDesLieuxService.findMinutesByEtatDesLieux(etatDesLieux));

        etatDesLieuxService.deleteMinute(minute1);
        etatDesLieuxService.updateTypeEtatDesLieux(etatDesLieux, TypeEtatDesLieux.SORTIE);
        etatDesLieuxService.updateMinute(minute2, "Une petite usure", EtatElement.BON);
        System.out.println(etatDesLieuxService.findMinutesByEtatDesLieux(etatDesLieux));




    }


}
