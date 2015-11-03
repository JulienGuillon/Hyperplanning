package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CPerson;
import fr.univtln.jguillon725.projet.model.entities.CStudent;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static fr.univtln.jguillon725.projet.utils.Password.checkPassword;

/**
 * Created by julien on 16/10/15.
 */
public class CEntityManagerPerson implements IEntity {

    private static PreparedStatement findByLogin;
    private static PreparedStatement findStudentInfo;
    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findByLogin = connection.prepareStatement("select NOM, ROLE, LOGIN, PASSWORD from USERS where LOGIN=?");
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

    public static CPerson find(String pLogin, String pPassword) throws PersistanceException {
        try {
            CPerson person = null;
            String role;
            //sinon on va le chercheur et on l'ajoute à la map
            findByLogin.setString(1, pLogin);
            ResultSet result = findByLogin.executeQuery();
            if (result.next()) {
                if ( checkPassword(pPassword, result.getString("PASSWORD"))) {
                    person = createFromResultSet(result);
                    role = person.getRole();
                    System.out.println(role);
                    switch (role)
                    {
                        case "STUDET":
                            findStudentInfo.setString(1, pLogin);

                            ResultSet resultStudent = findStudentInfo.executeQuery();
                            if (resultStudent.next()) {
                                CStudent student = new CStudent(person, resultStudent.getString("DOMAINE"), resultStudent.getInt("PROMOTION"), resultStudent.getString("INSTITUT"));
                                System.out.println(student);
                               // return student;
                            }
                            break;
                        case "TEACHER":
                            System.out.println("teacher");
                            break;
                    }
                    DatabaseManager.releaseConnection(connection);
                    return person;
                }
                return person;
            }
            else
                throw new PersistanceException("CPerson " + pLogin + " introuvable.");
        }
        catch (SQLException e) {
            throw new PersistanceException(e);
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
