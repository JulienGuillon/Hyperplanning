package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.CProfileGui;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelNotification;
import fr.univtln.jguillon725.projet.model.entities.CCourse;

import java.util.List;

/**
 * Created by julien on 15/10/15.
 */
public class CControlerEdt {
    private IView viewEdt;
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
        CModelNotification modelNotification = new CModelNotification();
        viewEdt = new CViewNotification(modelNotification);
        this.viewEdt.setVisible(true);
    }

    public void getProfile (){
        this.viewEdt.setVisible(false);
        CModelProfile cModelProfile = CModelProfile.getInstance();
        viewEdt = new CProfileGui();
        this.viewEdt.setVisible(true);
    }
}
