package fr.univtln.jguillon725.projet.model.entities;

/**
 * Created by julien on 16/10/15.
 */
public class CStudent extends CPerson {
    private EDomain domain; // SI, BIO, ...
    private EPromotion promotion; // L1, ...
    private EInstitut institut; // fac, iut

    public CStudent(String nom, String role, String login) {
        super(nom, role, login);
    }


}
