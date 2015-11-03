package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CReservationGui;
import fr.univtln.jguillon725.projet.model.CEntityManager;
import fr.univtln.jguillon725.projet.model.CEntityManagerCourse;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.model.entities.CTeacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 29/10/15.
 */
public class CReservationControler {
    private final CReservationGui reservationGui;
    private final CModelEdt modelEdt;

    public CReservationControler(CReservationGui reservationGui) {
        this.reservationGui = reservationGui;
        this.modelEdt = new CModelEdt();
    }

    public void changedState()
    {
        if (reservationGui.getMateriel().isSelected())
        {
            reservationGui.setChoiceEnable(true);
        }

        else
            reservationGui.setChoiceEnable(false);
    }
}
