package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.CNotificationGui;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelNotification;

/**
 * Created by julien on 20/10/15.
 */
public class CControlerNotification {
    private IView viewNotification;
    private CModelNotification CModelNotification;

    public CControlerNotification(CNotificationGui cNotificationGui, CModelNotification CModelNotification)
    {
        this.viewNotification = cNotificationGui;
        this.CModelNotification = CModelNotification;
    }


    public void retour(){
        this.viewNotification.setVisible(false);
        CModelEdt cModelEdt = new CModelEdt();
        try {
            viewNotification = new CViewEdt(cModelEdt);
        } catch (PersistanceException e) {
            e.printStackTrace();
        }
        this.viewNotification.setVisible(true);
    }

}