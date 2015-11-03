package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by julien on 26/10/15.
 */
public interface RolePerson {

    public List<CCourse> findCourse(int numWeek, int pNumDay) throws PersistanceException;
    public List<CCourse> findCourse() throws PersistanceException;
    public void afficherPlanning() throws PersistanceException, SQLException;
    public boolean[] creneauDispoDay(String date) throws PersistanceException;
}
