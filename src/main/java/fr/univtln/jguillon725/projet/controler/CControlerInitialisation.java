package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.Ihm;
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
        CModelPromotionChoice cModelPromotionChoice = CModelPromotionChoice.getInstance();
        vueInitialisation.getPanelChoice().removeAll();
        vueInitialisation.repaint();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        vueInitialisation.getPanelChoice().add(new CPromotionChoiceGui(cModelPromotionChoice), c);
        vueInitialisation.revalidate();
        //vueInitialisation = new CPromotionChoiceGui(cModelPromotionChoice);
       // this.vueInitialisation.setVisible(true);
    }

    public void identification() throws PersistanceException {
        CModelLogin modelLogin = CModelLogin.getInstance();
        vueInitialisation.getPanelChoice().removeAll();
        vueInitialisation.repaint();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        vueInitialisation.getPanelChoice().add(new CLoginGui(modelLogin), c);
        vueInitialisation.revalidate();
    }


}
