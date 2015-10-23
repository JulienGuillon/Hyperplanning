package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.gui.*;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelLogin;
import fr.univtln.jguillon725.projet.model.CModelProfile;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelNotification;
import fr.univtln.jguillon725.projet.model.entities.CCourse;

import java.awt.*;
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
        CModelNotification modelNotification = CModelNotification.getInstance();
        viewEdt.remove(viewEdt.getPanelEdt());
        viewEdt.add(new CNotificationGui(modelNotification), BorderLayout.CENTER);
        viewEdt.revalidate();
    }

    public void getProfile (){
        CModelProfile modelProfile = CModelProfile.getInstance();
        viewEdt.remove(viewEdt.getPanelEdt());
        viewEdt.add(new CProfileGui(), BorderLayout.CENTER);
        viewEdt.revalidate();
    }
}
