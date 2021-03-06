package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.model.entities.CPerson;

import java.util.*;
import java.util.List;

/**
 * Created by julien on 15/10/15.
 */
public class CModelEdt extends Observable implements Observer{
    private CPerson person;
    private int week;
    private int year;
    private int month;
    private int firtsDayOfWeek;

    public CModelEdt()
    {
        this.person = Ihm.person;
    }

    public void setPerson(CPerson person) {
        this.person = person;
    }

    public CPerson getPerson() {
        return person;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public List<CCourse> getPlanningWeek(int numWeek, int numDay) throws PersistanceException {
        Calendar cal = Calendar.getInstance();
        List<CCourse> listCourseByDay;
        this.week = cal.get(Calendar.WEEK_OF_YEAR) + numWeek;
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        this.firtsDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
        numDay = this.firtsDayOfWeek + numDay - 1;
        listCourseByDay = person.findCourse(week, numDay);
        //listCourseByDay = CEntityManagerCourse.findByDay(numDay, person);
        return listCourseByDay;
    }
}
