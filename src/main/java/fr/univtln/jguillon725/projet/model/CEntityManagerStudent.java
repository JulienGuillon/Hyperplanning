package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CPerson;
import fr.univtln.jguillon725.projet.model.entities.CStudent;
import fr.univtln.jguillon725.projet.model.entities.EDomain;
import fr.univtln.jguillon725.projet.model.entities.EInstitut;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fr.univtln.jguillon725.projet.utils.Password.checkPassword;

/**
 * Created by julien on 26/10/15.
 */
public class CEntityManagerStudent implements IEntity {
    private static PreparedStatement findByLogin;
    private static PreparedStatement findStudentInfo;
    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findStudentInfo = connection.prepareStatement("select DOMAINE, PROMOTION, INSTITUT from STUDENT where LOGIN=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persist(Connection pConnection) throws PersistanceException {

    }

    @Override
    public void merge(Connection pConnection) throws PersistanceException {

    }

    @Override
    public void update(Connection pConnection) throws PersistanceException {

    }

    @Override
    public void remove(Connection pConnection) throws PersistanceException {

    }

    private static CPerson createFromResultSet(ResultSet pResult) throws SQLException, PersistanceException {
        return new CPerson(pResult.getString("NOM"), pResult.getString("ROLE"), pResult.getString("LOGIN"));

    }

    public static void findInfo(String pLogin, CStudent student) throws PersistanceException, SQLException {
        findStudentInfo.setString(1, pLogin);
        ResultSet resultStudent = findStudentInfo.executeQuery();
        if (resultStudent.next()) {
            student.setDomain(EDomain.valueOf(resultStudent.getString("DOMAINE")));
            student.setPromotion(resultStudent.getInt("PROMOTION"));
            student.setInstitut(EInstitut.valueOf(resultStudent.getString("INSTITUT")));
        }
    }

    public static List research(String pLogin) throws PersistanceException {
        try {
            List list = new ArrayList();
            findByLogin.setString(1, pLogin);
            ResultSet result = findByLogin.executeQuery();
            if (result.next()) {
                list.add(result.getString("NOM"));
                list.add(result.getString("ROLE"));
                list.add(result.getString("LOGIN"));
                list.add(result.getString("PASSWORD"));
                return list;
            } else
                throw new PersistanceException("Person " + pLogin + " introuvable.");
        } catch (SQLException e) {
            throw new PersistanceException(e);
        }
    }
}
