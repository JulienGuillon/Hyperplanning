package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.model.CEntityManagerCourse;
import fr.univtln.jguillon725.projet.model.CModelEdt;

import java.util.Calendar;
import java.util.List;

/**
 * Created by julien on 29/10/15.
 */
public class CVisitor implements RolePerson {
    private CPerson person;
    private int choicePromotion;

    public CVisitor(CPerson person) {
        this.person = person;
    }

    public CVisitor(CPerson person, int choicePromotion) {
        this.person = person;
        this.choicePromotion = choicePromotion;
    }

        @Override
    public List<CCourse> findCourse(int numWeek, int numDay) throws PersistanceException {
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR) + numWeek;
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
        numDay = firstDayOfWeek + numDay - 1;
        String date = String.valueOf(year)+'-'+String.valueOf(month)+'-'+String.valueOf(numDay);
        return CEntityManagerCourse.findByDayPromotion(date, choicePromotion);
        }

    @Override
    public List<CCourse> findCourse() throws PersistanceException {
        Calendar cal = Calendar.getInstance();
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println();
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_MONTH);
        String date = String.valueOf(year)+'-'+String.valueOf(month)+'-'+String.valueOf(firstDayOfWeek);
        return CEntityManagerCourse.findByDayPromotion(date, choicePromotion);    }

    @Override
    public void afficherPlanning() throws PersistanceException {
        CModelEdt modelEdt = new CModelEdt();
        CViewEdt viewEdt = new CViewEdt(modelEdt);
    }

    @Override
    public boolean[] creneauDispoDay(String date) throws PersistanceException {
        return null;
    }

    public void setChoicePromotion(int choicePromotion) {
        this.choicePromotion = choicePromotion;
    }
}
