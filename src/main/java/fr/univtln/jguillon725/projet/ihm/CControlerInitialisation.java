package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelLogin;

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
        CModelEdt modelEdt = new CModelEdt();
        vueInitialisation = new CViewEdt(modelEdt);
        this.vueInitialisation.setVisible(true);
    }

    public void identification() throws PersistanceException {
        this.vueInitialisation.setVisible(false);
        CModelLogin modelLogin = CModelLogin.getInstance();
        vueInitialisation = new CViewLogin(modelLogin);
        this.vueInitialisation.setVisible(true);
    }


}
