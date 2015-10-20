package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;


/**
 * Created by scaltot904 on 20/10/15.
 */
public class CControlerProfile {
    private IView viewProfile;
    private CModelProfile CModelProfile;

    public CControlerProfile (CViewProfile CViewProfile, CModelProfile CModelProfile) {
        this.viewProfile = CViewProfile;
        this.CModelProfile = CModelProfile;
    }

    public void retour(){
        this.viewProfile.setVisible(false);
        CModelEdt cModelEdt = new CModelEdt();
        try {
            viewProfile = new CViewEdt(cModelEdt);
        } catch (PersistanceException e) {
            e.printStackTrace();
        }
        this.viewProfile.setVisible(true);
    }

}
