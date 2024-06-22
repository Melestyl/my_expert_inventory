package org.example.my_expert_inventory.service;

import jakarta.persistence.Persistence;
import org.example.my_expert_inventory.dao.BienDAO;
import org.example.my_expert_inventory.dao.ElementDAO;
import org.example.my_expert_inventory.dao.PieceDAO;
import org.example.my_expert_inventory.dao.TypeDElementDAO;
import org.example.my_expert_inventory.model.Bien;
import org.example.my_expert_inventory.model.TypeDElement;
import org.example.my_expert_inventory.model.Element;
import org.example.my_expert_inventory.model.Piece;

import java.util.List;


public class ElementService {
    private ElementDAO elementDao;
    private TypeDElementDAO typeDElementDAO;

    public ElementService(ElementDAO elementDao, TypeDElementDAO typeDElementDAO) {
        this.elementDao = elementDao;
        this.typeDElementDAO = typeDElementDAO;
    }

    public TypeDElement typeDElementExists(String type) {
        return typeDElementDAO.findByType(type);
    }

    public TypeDElement createTypeDElement(String type) {
        if(type == null){
            System.out.println("Type d'élément is null, cannot create type d'élément");
            return null;
        }
        TypeDElement typeDElementIfExists = typeDElementExists(type);
        if(typeDElementIfExists != null){
            System.out.println("Type d'élément already exists, return this type d'élément");
            return typeDElementIfExists;
        }
        TypeDElement typeDElement = new TypeDElement();
        typeDElement.setType(type);
        typeDElementDAO.create(typeDElement);
        return typeDElement;
    }

    public List<Element> findAllTypeDElement() {
        return typeDElementDAO.findAll();
    }

    public List<Element> findByPiece(Piece piece) {
        return elementDao.findByPiece(piece);
    }

    public Element createElement(TypeDElement typeDElement, Piece piece) {
        if (typeDElement == null) {
            System.out.println("Type d'élément is null, cannot create element");
            return null;
        }
        if (piece == null) {
            System.out.println("Piece is null, cannot create element");
            return null;
        }
        Element element = new Element();
        element.setIdTypeElement(typeDElement);
        element.setIdPiece(piece);
        elementDao.create(element);
        return element;
    }

    public void deleteElement(Element element) {
        if (element == null) {
            System.out.println("Element is null, cannot delete element");
            return;
        }
        elementDao.delete(element);
    }

    public void deleteElementsFromPiece(Piece piece) {
        if (piece == null) {
            System.out.println("Piece is null, cannot delete elements");
            return;
        }
        List<Element> elements = findByPiece(piece);
        for (Element element : elements) {
            deleteElement(element);
        }
    }

    public static void main(String[] args) {
        // Create a new piece
        PieceService pieceService = new PieceService(new PieceDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        BienService bienService = new BienService(new BienDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));
        ElementService elementService = new ElementService(new ElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()), new TypeDElementDAO(Persistence.createEntityManagerFactory("PU_Projet_POO").createEntityManager()));


        Bien bien = bienService.creatBienWithAdresse(BienService.TypeDeBien.MAISON, "John Doe", 1, "rue de la paix", 75000, "Paris", "");
        Piece piece1 = pieceService.createPiece(PieceService.TypeDePiece.SALON, 20, 1, bien);

        TypeDElement typeDElement = elementService.createTypeDElement("Table");
        TypeDElement typeDElement2 = elementService.createTypeDElement("Chaise");
        TypeDElement typeDElement3 = elementService.createTypeDElement("Canapé");

        Element element1 = elementService.createElement(typeDElement, piece1);
        Element element2 = elementService.createElement(typeDElement2, piece1);
        Element element3 = elementService.createElement(typeDElement3, piece1);


        System.out.println(elementService.findByPiece(piece1));
        elementService.deleteElement(element1);
        System.out.println(elementService.findByPiece(piece1));
        elementService.deleteElementsFromPiece(piece1);
        System.out.println(elementService.findByPiece(piece1));
    }



}
