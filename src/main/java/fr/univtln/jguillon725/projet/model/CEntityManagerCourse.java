package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.model.entities.CStudent;
import fr.univtln.jguillon725.projet.model.entities.CTeacher;
import fr.univtln.jguillon725.projet.utils.DatabaseManager;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private static PreparedStatement findCoursePromotion;
    private static PreparedStatement findCoursePromotionGroup;

    private static Connection connection;

    //L'initialisation des preparedstatments.
    static {
        try {
            Connection connection = DatabaseManager.getConnection();
            findCourseStudentPromotion = connection.prepareStatement("select * from viewcoursepromotion where date=? AND idpromotion=?");
            findCourseStudentGroup = connection.prepareStatement("select * from viewcoursegroup where date=? AND idgroup=?");
            findCourseTeacherPromotion = connection.prepareStatement("select * from viewcoursepromotion where date=? AND teacher=?");
            findCourseTeacherGroup = connection.prepareStatement("select * from viewcoursegroup where date=? AND teacher=?");
            findCoursePromotion = connection.prepareStatement("select * from viewcoursepromotion where date=? AND idpromotion=?");
            findCoursePromotionGroup = connection.prepareStatement("select * from viewcoursegroup where date=? AND idgroup=?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static CCourse createFromResultSet(ResultSet pResult) throws SQLException
    {
        return new CCourse(pResult.getDate("date"), pResult.getString("SUBJECT"),
                pResult.getInt("DUREE"), pResult.getInt("HOUR"), pResult.getString("typeCourse"));

    }

    public static List<CCourse> findByDay(String date, CStudent person) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = null;
            try {
                parsed = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            findCourseStudentPromotion.setDate(1, sql);
            findCourseStudentPromotion.setInt(2, person.getPromotion());

            findCourseStudentGroup.setDate(1, sql);
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

    public static List<CCourse> findByDay(String date, CTeacher person) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = null;
            try {
                parsed = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());

            findCourseTeacherPromotion.setDate(1, sql);
            findCourseTeacherPromotion.setString(2, person.getPerson().getLogin());
            findCourseTeacherGroup.setDate(1, sql);
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

    public static List<CCourse> findByDayPromotion(String date, int promotion) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        List<Integer> listGroup= CEntityManagerPromotion.findGroup(promotion);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = null;
            try {
                parsed = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());

            findCoursePromotion.setDate(1, sql);
            findCoursePromotion.setInt(2, promotion);
            findCoursePromotionGroup.setDate(1, sql);

            ResultSet result = findCoursePromotion.executeQuery();
            while (result.next()) {
                CCourse course = createFromResultSet(result);
                courseByDay.add(course);
            }

            for (int groupe:listGroup)
            {
                findCoursePromotionGroup.setInt(2, groupe);
                result = findCoursePromotionGroup.executeQuery();
                while (result.next()) {
                    CCourse course = createFromResultSet(result);
                    courseByDay.add(course);
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return courseByDay;
    }

    public static List<CCourse> findCreneauDispo(String date, int promotion, CTeacher teacher) throws PersistanceException {
        List<CCourse> courseByDay = new ArrayList<CCourse>();
        courseByDay.addAll(findByDay(date, teacher));
        courseByDay.addAll(findByDayPromotion(date,promotion));
        return courseByDay;
    }
}

