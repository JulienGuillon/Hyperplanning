package fr.univtln.jguillon725.projet.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by julien on 17/10/15.
 */
public class CCourse {
    private int numDay;
    private int hour;

    private int idWeek;
    private String subject;
    private int duree;

    public CCourse(int pNumDay, String pSubject) {
        this.numDay = pNumDay;
        this.subject = pSubject;
    }

    public CCourse(int pNumDay, int pIdWeek, String pSubject, int pDuree, int pHour) {
        this.idWeek = pIdWeek;
        this.numDay = pNumDay;
        this.subject = pSubject;
        this.duree = pDuree;
        this.hour = pHour;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDuree() { return duree; }

    public void setDuree(int duree) { this.duree = duree; }

    public int getIdWeek() {
        return idWeek;
    }

    public void setIdWeek(int idWeek) {
        this.idWeek = idWeek;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumDay() {
        return numDay;
    }

    public void setNumDay(int numDay) {
        this.numDay = numDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCourse cCourse = (CCourse) o;

        if (numDay != cCourse.numDay) return false;
        if (hour != cCourse.hour) return false;
        if (idWeek != cCourse.idWeek) return false;
        return !(subject != null ? !subject.equals(cCourse.subject) : cCourse.subject != null);

    }

    @Override
    public int hashCode() {
        int result = numDay;
        result = 31 * result + hour;
        result = 31 * result + idWeek;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
