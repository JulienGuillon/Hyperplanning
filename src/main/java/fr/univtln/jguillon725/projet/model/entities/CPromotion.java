package fr.univtln.jguillon725.projet.model.entities;

/**
 * Created by julien on 22/10/15.
 */
public class CPromotion {
    private int idPromotion;
    private String typePromotion;
    private String domainePromotion;
    private int anneePromotion;
    private int nombreInscrit;

    public CPromotion(int idPromotion, String typePromotion, String domainePromotion, int anneePromotion) {
        this.idPromotion = idPromotion;
        this.typePromotion = typePromotion;
        this.domainePromotion = domainePromotion;
        this.anneePromotion = anneePromotion;
    }

    public CPromotion(int idPromotion, String typePromotion, String domainePromotion, int anneePromotion, int nombreInscrit) {
        this.idPromotion = idPromotion;
        this.typePromotion = typePromotion;
        this.domainePromotion = domainePromotion;
        this.anneePromotion = anneePromotion;
        this.nombreInscrit = nombreInscrit;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getTypePromotion() {
        return typePromotion;
    }

    public void setTypePromotion(String typePromotion) {
        this.typePromotion = typePromotion;
    }

    public String getDomainePromotion() {
        return domainePromotion;
    }

    public void setDomainePromotion(String domainePromotion) {
        this.domainePromotion = domainePromotion;
    }

    public int getAnneePromotion() {
        return anneePromotion;
    }

    public void setAnneePromotion(int anneePromotion) {
        this.anneePromotion = anneePromotion;
    }

    public int getNombreInscrit() {
        return nombreInscrit;
    }

    public void setNombreInscrit(int nombreInscrit) {
        this.nombreInscrit = nombreInscrit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CPromotion promotion = (CPromotion) o;

        if (idPromotion != promotion.idPromotion) return false;
        if (anneePromotion != promotion.anneePromotion) return false;
        if (nombreInscrit != promotion.nombreInscrit) return false;
        if (typePromotion != null ? !typePromotion.equals(promotion.typePromotion) : promotion.typePromotion != null)
            return false;
        return !(domainePromotion != null ? !domainePromotion.equals(promotion.domainePromotion) : promotion.domainePromotion != null);

    }

    @Override
    public int hashCode() {
        int result = idPromotion;
        result = 31 * result + (typePromotion != null ? typePromotion.hashCode() : 0);
        result = 31 * result + (domainePromotion != null ? domainePromotion.hashCode() : 0);
        result = 31 * result + anneePromotion;
        result = 31 * result + nombreInscrit;
        return result;
    }

    @Override
    public String toString() {
        return typePromotion +" "+ domainePromotion;
    }
}
