package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Observable;

/**
 * Created by julien on 13/10/15.
 */
public class CEntityManager extends Observable{
    private Connection connection;

    private CEntityManager()
    {
        try {
            //L'entity manager utilise une seule connexion
            connection = DatabaseManager.getConnection();
            //Le modele observe tout les entity manager
            addObserver(CModelLogin.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CEntityManager getInstance()
    {
        return new CEntityManager();
    }

    public void dispose() throws PersistanceException
    {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        DatabaseManager.releaseConnection(connection);
        deleteObserver(CModelLogin.getInstance());
    }

    public void commit() throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }

    public void setAutoCommit(boolean autoCommit) throws PersistanceException {
        if (connection==null) throw new PersistanceException("Entity manager without connection.");
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }


}
