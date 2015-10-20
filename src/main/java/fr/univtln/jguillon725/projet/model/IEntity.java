package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import java.sql.Connection;

/**
 * Created by julien on 13/10/15.
 */
public interface IEntity {
    public void persist(Connection connection) throws PersistanceException;

    public void merge(Connection connection) throws PersistanceException;

    public void update(Connection connection) throws PersistanceException;

    public void remove(Connection connection) throws PersistanceException;

}
