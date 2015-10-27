package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.model.CEntityManagerCourse;
import fr.univtln.jguillon725.projet.model.CModelEdt;

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

    public CPerson getPerson() {
        return person;
    }
}
