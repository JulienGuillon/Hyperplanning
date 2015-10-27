package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.model.entities.CStudent;
import fr.univtln.jguillon725.projet.model.entities.CTeacher;
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

    private static PreparedStatement findCourseStudentPromotion;
    private static PreparedStatement findCourseStudentGroup;
    private static PreparedStatement findCourseTeacherPromotion;
    private static PreparedStatement findCourseTeacherGroup;

    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findCourseStudentPromotion = connection.prepareStatement("select * from viewcoursepromotion where DAY=? AND idpromotion=?");
            findCourseStudentGroup = connection.prepareStatement("select * from viewcoursegroup where DAY=? AND idgroup=?");
            findCourseTeacherPromotion = connection.prepareStatement("select * from viewcoursepromotion where DAY=? AND teacher=?");
            findCourseTeacherGroup = connection.prepareStatement("select * from viewcoursegroup where DAY=? AND teacher=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static CCourse createFromResultSet(ResultSet pResult) throws SQLException
    {
        return new CCourse(pResult.getInt("DAY"), pResult.getInt("IDWEEK"), pResult.getString("SUBJECT"), pResult.getInt("DUREE"), pResult.getInt("HOUR"));

    }

    public static List<CCourse> findByDay(int pNumDay, CStudent person) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        try {
            findCourseStudentPromotion.setInt(1, pNumDay);
            findCourseStudentPromotion.setInt(2, person.getPromotion());

            findCourseStudentGroup.setInt(1, pNumDay);
            findCourseStudentGroup.setInt(2, 1);

            ResultSet result = findCourseStudentPromotion.executeQuery();
            while (result.next()) {
                CCourse course = createFromResultSet(result);
                    courseByDay.add(course);
                }
            result = findCourseStudentGroup.executeQuery();
            while (result.next()) {
                CCourse course = createFromResultSet(result);
                courseByDay.add(course);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return courseByDay;
    }

    public static List<CCourse> findByDay(int pNumDay, CTeacher person) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        try {
            findCourseTeacherPromotion.setInt(1, pNumDay);
            findCourseTeacherPromotion.setString(2, person.getPerson().getLogin());
            findCourseTeacherGroup.setInt(1, pNumDay);
            findCourseTeacherGroup.setString(2, person.getPerson().getLogin());
            ResultSet result = findCourseTeacherPromotion.executeQuery();
            while (result.next()) {
                CCourse course = createFromResultSet(result);
                courseByDay.add(course);
            }

            result = findCourseTeacherGroup.executeQuery();
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

