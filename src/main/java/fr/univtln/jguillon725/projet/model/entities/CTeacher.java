package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.model.CEntityManagerCourse;
import fr.univtln.jguillon725.projet.model.CModelEdt;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by julien on 16/10/15.
 */
public class CTeacher implements RolePerson {
    private final CPerson person;

    public CTeacher() throws PersistanceException, SQLException {
        person = null;
    }

    public CTeacher(CPerson person) throws PersistanceException, SQLException {
        super();
        this.person = person;
    }

    public CTeacher(String nom, String role, String login) throws PersistanceException, SQLException {
        this.person = null;
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
    }

    @Override
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


    public void afficherPlanning() throws PersistanceException {
        CModelEdt modelEdt = new CModelEdt();
        CViewEdt viewEdt = new CViewEdt(modelEdt);
    }

    public CPerson getPerson() {
        return person;
    }

    public boolean[] creneauDispoDay(String date) throws PersistanceException {
        List<CCourse> listCourse = CEntityManagerCourse.findCreneauDispo(date, 1, this);
        boolean[] listCreneau = new boolean[22];
        for (int i = 0; i < 22; i++)
            listCreneau[i] = true;
        for (CCourse course : listCourse) {
            for (int j = (course.getHour() - 8) * 2; j < (course.getHour() - 8) * 2 + course.getDuree() * 2; j++) {
                listCreneau[j] = false;
            }
        }
        return listCreneau;
    }
}
