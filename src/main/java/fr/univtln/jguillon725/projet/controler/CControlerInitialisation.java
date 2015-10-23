package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.*;
import fr.univtln.jguillon725.projet.model.CModelLogin;
import fr.univtln.jguillon725.projet.model.CModelPromotionChoice;

import javax.swing.*;
import java.awt.*;

/**
 * Created by julien on 20/10/15.
 */
public class CControlerInitialisation {
    private CViewInitialisation vueInitialisation;

    public CControlerInitialisation(CViewInitialisation vueInitialisation) {
        this.vueInitialisation = vueInitialisation;
    }

    public void connexion() throws PersistanceException {
        this.vueInitialisation.setVisible(false);
        CModelPromotionChoice cModelPromotionChoice = CModelPromotionChoice.getInstance();
        //vueInitialisation = new CPromotionChoiceGui(cModelPromotionChoice);
        this.vueInitialisation.setVisible(true);
    }

    public void identification() throws PersistanceException {
        CModelLogin modelLogin = CModelLogin.getInstance();
        vueInitialisation.remove(vueInitialisation.getPanelChoice());
        vueInitialisation.add(new CLoginGui(modelLogin), BorderLayout.CENTER);
        vueInitialisation.revalidate();
    }


}
