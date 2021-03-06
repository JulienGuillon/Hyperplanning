package fr.univtln.jguillon725.projet.model.entities;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by julien on 13/10/15.
 */
public class CPerson implements Comparable<CPerson>{
    private String nom;
    private String role;
    private String login;
    private RolePerson roleP;

    public CPerson() throws PersistanceException, SQLException {
       // roleP = new CVisitor(this);
    }

    public CPerson(String nom, String role, String login) throws PersistanceException, SQLException {
        this.nom = nom;
        this.role = role;
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRoleP(RolePerson roleP) {
        this.roleP = roleP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CPerson person = (CPerson) o;

        if (nom != null ? !nom.equals(person.nom) : person.nom != null) return false;
        if (role != null ? !role.equals(person.role) : person.role != null) return false;
        return !(login != null ? !login.equals(person.login) : person.login != null);

    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(CPerson o) {
        return (this.nom.compareTo(o.getNom())
                & this.role.compareTo(o.getRole())
                & this.login.compareTo(o.getLogin()));
    }

    public List<CCourse> findCourse(int numWeek, int numDay) throws PersistanceException {
        return roleP.findCourse(numWeek, numDay);
    }

    public List<CCourse> findCourse() throws PersistanceException {
        return roleP.findCourse();
    }

    public void afficherPlanning() throws PersistanceException, SQLException {
        roleP.afficherPlanning();
    }

    public boolean[] creneauDispoDay(String date) throws PersistanceException
    {
        return roleP.creneauDispoDay(date);
    }
}
