package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.gui.*;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelLogin;
import fr.univtln.jguillon725.projet.model.CModelProfile;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelNotification;
import fr.univtln.jguillon725.projet.model.entities.CCourse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

    public List<CCourse> getPlanningDay(int numWeek, int numDay) throws PersistanceException {
        return this.modelEdt.getPerson().findCourse(numWeek, numDay);
    }

    public void getPlanningDay() throws PersistanceException {
        viewEdt.updateDay(this.modelEdt.getPerson().findCourse());
    }

    public void getPlanningWeek(int numWeek) throws PersistanceException {
        List<List<CCourse>> listPlanning = new ArrayList<List<CCourse>>();
        for(int i=1; i<7; i++)
        {
            listPlanning.add(getPlanningDay(numWeek, i));
        }
        viewEdt.updatePlanning(listPlanning);
    }

    public void notification()
    {
        CModelNotification modelNotification = CModelNotification.getInstance();
        viewEdt.remove(viewEdt.getPanelEdt());
        viewEdt.add(new CNotificationGui(modelNotification), BorderLayout.CENTER);
        viewEdt.revalidate();
    }

    public void getProfile (){
        //CModelProfile modelProfile = CModelProfile.getInstance();
        viewEdt.remove(viewEdt.getPanelEdt());
        viewEdt.add(new CProfileGui(), BorderLayout.CENTER);
        viewEdt.revalidate();
    }
}
