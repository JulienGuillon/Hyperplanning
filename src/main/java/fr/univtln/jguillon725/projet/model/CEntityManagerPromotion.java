package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.model.entities.CPromotion;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 22/10/15.
 */
public class CEntityManagerPromotion implements IEntity {

    private static PreparedStatement findAllPromotion;
    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findAllPromotion = connection.prepareStatement("select * from PROMOTION ORDER BY TYPEPROMOTION, DOMAINEPROMOTION");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persist(Connection connection) throws PersistanceException {

    }

    @Override
    public void merge(Connection connection) throws PersistanceException {

    }

    @Override
    public void update(Connection connection) throws PersistanceException {

    }

    @Override
    public void remove(Connection connection) throws PersistanceException {

    }

    public static CPromotion createFromResultSet(ResultSet pResult) throws SQLException {
        return new CPromotion(pResult.getString("typePromotion"), pResult.getString("domainePromotion"), pResult.getInt("anneepromotion"), pResult.getInt("nombreInscrit"));
    }

    public static List<CPromotion> findAll() throws PersistanceException {
        List<CPromotion> promotions = new ArrayList<CPromotion>();
        try {
            ResultSet result = findAllPromotion.executeQuery();
            while (result.next()) {
                CPromotion promotion = createFromResultSet(result);
                promotions.add(promotion);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return promotions;
    }
}
