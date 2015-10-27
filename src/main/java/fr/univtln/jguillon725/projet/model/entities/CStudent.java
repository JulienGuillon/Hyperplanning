package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.model.*;
import fr.univtln.jguillon725.projet.utils.Read;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by julien on 16/10/15.
 */
public class CStudent implements RolePerson {
    private EDomain domain; // SI, BIO, ...
    private int promotion; // L1, ...
    private EInstitut institut; // fac, iut
    private final CPerson person;

    public CStudent(CPerson person) throws PersistanceException, SQLException {
        if (person == null)
        {
            throw new IllegalArgumentException("le role d'une personne doit etre associée a une personne non nulle");
        }
        this.person = person;
        CEntityManagerStudent.findInfo(person.getLogin(), this);
    }

    public CStudent(String nom, String role, String login) throws PersistanceException, SQLException {
        //super(nom, role, login);
        person=null;
    }

    public CStudent(String nom, String role, String login, String pDomaine, int pPromotion, String pInstitut) throws PersistanceException, SQLException {
        //super(nom, role, login);
        domain = EDomain.valueOf(pDomaine);
        promotion = pPromotion;
        institut = EInstitut.valueOf(pInstitut);
        person = null;
    }

    public CStudent(CPerson pPerson, String pDomaine, int pPromotion, String pInstitut) throws PersistanceException, SQLException {
        //super(pPerson.getNom(), pPerson.getRole(), pPerson.getLogin());
        domain = EDomain.valueOf(pDomaine);
        promotion = pPromotion;
        institut = EInstitut.valueOf(pInstitut);
        person = null;
    }

    public int getPromotion()
    {
        return promotion;
    }

    public void setDomain(EDomain domain) {
        this.domain = domain;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void setInstitut(EInstitut institut) {
        this.institut = institut;
    }

    public List<CCourse> findCourse(int numDay) throws PersistanceException {
        Calendar cal = Calendar.getInstance();
        List<CCourse> listCourseByDay;
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        int firtsDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
        numDay = firtsDayOfWeek + numDay - 1;
        return CEntityManagerCourse.findByDay(numDay, this);
    };


    public void afficherPlanning() throws PersistanceException {
        CModelEdt modelEdt = new CModelEdt(person);
        CViewEdt viewEdt = new CViewEdt(modelEdt);
    }

}
