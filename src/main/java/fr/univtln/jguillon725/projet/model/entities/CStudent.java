package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.model.*;
import fr.univtln.jguillon725.projet.utils.Read;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<CCourse> findCourse(int numWeek, int numDay) throws PersistanceException {
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR) + numWeek;
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        int firtsDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
        numDay = firtsDayOfWeek + numDay - 1;
        String date = String.valueOf(year)+'-'+String.valueOf(month)+'-'+String.valueOf(numDay);
        return CEntityManagerCourse.findByDay(date, this);
    };

    public List<CCourse> findCourse() throws PersistanceException {
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        int firtsDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
        String date = String.valueOf(year)+'-'+String.valueOf(month)+'-'+String.valueOf(firtsDayOfWeek);
        return CEntityManagerCourse.findByDay(date, this);
    };

    public void afficherPlanning() throws PersistanceException, SQLException {
        CModelEdt modelEdt = new CModelEdt();
        CViewEdt viewEdt = new CViewEdt(modelEdt);
    }

    @Override
    public boolean[] creneauDispoDay(String date) throws PersistanceException {
        return null;
    }

}
