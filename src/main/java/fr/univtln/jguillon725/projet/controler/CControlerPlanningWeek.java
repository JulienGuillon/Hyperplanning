package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CPlanningWeekGui;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelNotification;
import fr.univtln.jguillon725.projet.model.CModelPlanningWeek;
import fr.univtln.jguillon725.projet.model.entities.CCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 28/10/15.
 */
public class CControlerPlanningWeek {
    private CPlanningWeekGui planningWeekGui;
    private CModelEdt modelEdt;

    public CControlerPlanningWeek(CPlanningWeekGui planningWeekGui, CModelEdt modelEdt) {
        this.planningWeekGui = planningWeekGui;
        this.modelEdt = modelEdt;
    }

    public List<CCourse> getPlanningDay(int numWeek, int numDay) throws PersistanceException {
        return Ihm.person.findCourse(numWeek, numDay);
    }

    public void getPlanningWeek(int numWeek) throws PersistanceException {
        List<List<CCourse>> listPlanning = new ArrayList<List<CCourse>>();
        for(int i=1; i<7; i++)
        {
            listPlanning.add(getPlanningDay(numWeek, i));
        }
        planningWeekGui.updatePlanning(listPlanning);
    }
}
