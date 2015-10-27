package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CRoom;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 20/10/15.
 */
public class CEntityManagerRoom implements IEntity {
    private static PreparedStatement findAll;
    private static PreparedStatement findByBatiment;

    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findAll = connection.prepareStatement("select * from ROOM;");
            findByBatiment = connection.prepareStatement("select * from ROOM WHERE Batiment=?");

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

    private static CRoom createFromResultSet(ResultSet pResult) throws SQLException
    {
        return new CRoom(pResult.getString("batiment"),pResult.getInt("numRoom"), pResult.getString("typeRoom"), pResult.getInt("capacity"));
    }

    public static List<CRoom> findAll() throws SQLException {
        List<CRoom> listRoom = new ArrayList<CRoom>();
        ResultSet resultSet = findAll.executeQuery();
        while(resultSet.next())
        {
            CRoom room = createFromResultSet(resultSet);
            listRoom.add(room);
        }
        return listRoom;
    }

    public List<CRoom> findByBatiment(String batiment) throws SQLException {
        List<CRoom> listRoom = new ArrayList<CRoom>();
        findByBatiment.setString(1, batiment);
        ResultSet resultSet = findByBatiment.executeQuery();
        while(resultSet.next())
        {
            CRoom room = createFromResultSet(resultSet);
            listRoom.add(room);
        }
        return listRoom;
    }

}
