package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CViewLogin;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.model.CModelLogin;
import fr.univtln.jguillon725.projet.model.CModelPromotionChoice;
import fr.univtln.jguillon725.projet.gui.CPromotionChoiceGui;

/**
 * Created by julien on 20/10/15.
 */
public class CControlerInitialisation {
    private IView vueInitialisation;

    public CControlerInitialisation(IView vueInitialisation) {
        this.vueInitialisation = vueInitialisation;
    }

    public void connexion() throws PersistanceException {
        this.vueInitialisation.setVisible(false);
        CModelPromotionChoice cModelPromotionChoice = CModelPromotionChoice.getInstance();
        vueInitialisation = new CPromotionChoiceGui(cModelPromotionChoice);
        this.vueInitialisation.setVisible(true);
    }

    public void identification() throws PersistanceException {
        this.vueInitialisation.setVisible(false);
        CModelLogin modelLogin = CModelLogin.getInstance();
        vueInitialisation = new CViewLogin(modelLogin);
        this.vueInitialisation.setVisible(true);
    }


}
