package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.ConfigImportException;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CEntityManager;
import fr.univtln.jguillon725.projet.utils.ConfigReader;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;
import fr.univtln.jguillon725.projet.utils.Read;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by julien on 15/10/15.
 */
public class Creation {
    private String creation;

    public static void main(String[] args) throws ConfigImportException, PersistanceException, SQLException, IOException {
        Read fileCreation = new Read();
        String creation;
        creation = fileCreation.readFile("creation.sql");
        System.out.println(creation);
        ConfigReader.importConfig();
        CEntityManager entityManager = CEntityManager.getInstance();

        try {
            entityManager.setAutoCommit(false);

            Connection connection = DatabaseManager.getConnection();
            connection.createStatement().executeUpdate(creation);
            DatabaseManager.releaseConnection(connection);

            entityManager.commit();
        }
        catch (PersistanceException e)
        {
            System.out.println(e.getException());
        }
        finally {
            entityManager.dispose();
        }
    }
}
