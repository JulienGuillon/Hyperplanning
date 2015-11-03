package fr.univtln.jguillon725.projet.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by julien on 17/10/15.
 */
public class CCourse {
    private Date numDay;
    private int hour;
    private String typeCours;
    private String subject;
    private int duree;

    public CCourse(Date pNumDay, String pSubject) {
        this.numDay = pNumDay;
        this.subject = pSubject;
    }

    public CCourse(Date pNumDay, String pSubject, int pDuree, int pHour, String typeCours) {
        this.numDay = pNumDay;
        this.subject = pSubject;
        this.duree = pDuree;
        this.hour = pHour;
        this.typeCours = typeCours;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDuree() { return duree; }

    public void setDuree(int duree) { this.duree = duree; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getNumDay() {
        return numDay;
    }

    public void setNumDay(Date numDay) {
        this.numDay = numDay;
    }

    public String getTypeCours() {
        return typeCours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCourse course = (CCourse) o;

        if (hour != course.hour) return false;
        if (duree != course.duree) return false;
        if (numDay != null ? !numDay.equals(course.numDay) : course.numDay != null) return false;
        if (typeCours != null ? !typeCours.equals(course.typeCours) : course.typeCours != null) return false;
        return !(subject != null ? !subject.equals(course.subject) : course.subject != null);

    }

    @Override
    public int hashCode() {
        int result = numDay != null ? numDay.hashCode() : 0;
        result = 31 * result + hour;
        result = 31 * result + (typeCours != null ? typeCours.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + duree;
        return result;
    }
}
