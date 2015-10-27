package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import java.util.List;

/**
 * Created by julien on 26/10/15.
 */
public interface RolePerson {

    public List<CCourse> findCourse(int numDay) throws PersistanceException;
    public void afficherPlanning() throws PersistanceException;
}
