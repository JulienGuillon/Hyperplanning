package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 17/10/15.
 */
public class CEntityManagerCourse implements IEntity {
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

    private static PreparedStatement findByDay;
    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findByDay = connection.prepareStatement("select * from COURSE where DAY=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static CCourse createFromResultSet(ResultSet pResult) throws SQLException
    {
        return new CCourse(pResult.getInt("DAY"), pResult.getInt("IDWEEK"), pResult.getString("SUBJECT"), pResult.getInt("DUREE"), pResult.getInt("HOUR"));

    }

    public static List<CCourse> findByDay(int pNumDay) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        try {
            findByDay.setInt(1, pNumDay);
            ResultSet result = findByDay.executeQuery();
            while (result.next()) {
                CCourse course = createFromResultSet(result);
                    courseByDay.add(course);
                }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return courseByDay;
    }
}

