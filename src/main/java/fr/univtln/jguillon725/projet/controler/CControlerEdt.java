package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.gui.CNotificationGui;
import fr.univtln.jguillon725.projet.gui.CProfileGui;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelProfile;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelNotification;
import fr.univtln.jguillon725.projet.model.entities.CCourse;

import java.util.List;

/**
 * Created by julien on 15/10/15.
 */
public class CControlerEdt {
    private CViewEdt viewEdt;
    private CModelEdt modelEdt;

    public CControlerEdt(CViewEdt viewEdt, CModelEdt modelEdt) {
        this.viewEdt = viewEdt;
        this.modelEdt = modelEdt;
    }

    public List<CCourse> getPlanningWeek(int numDay) throws PersistanceException {
        return this.modelEdt.getPlanningWeek(numDay);
    }

    public void notification()
    {
        this.viewEdt.setVisible(false);
        CModelNotification cModelNotification = CModelNotification.getInstance();
        //viewEdt = new CNotificationGui(cModelNotification);
        this.viewEdt.setVisible(true);
    }

    public void getProfile (){
        int index = viewEdt.getTabbedPane().indexOfTab("Profil");
        viewEdt.getTabbedPane().setSelectedIndex(index);
    }
}
