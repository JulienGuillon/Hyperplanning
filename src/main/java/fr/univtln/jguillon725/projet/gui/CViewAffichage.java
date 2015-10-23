package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelInitialisation;

/**
 * Created by julien on 15/10/15.
 */
public class CViewAffichage<T> implements IView<T> {
    private IView view;

    public CViewAffichage() throws PersistanceException {
        this.view = new CViewInitialisation();
        this.view.createView(CModelInitialisation.getInstance());
    }

    public CViewAffichage(IView view, T model) throws PersistanceException {
        this.view = view;
        this.view.createView(model);

    }
    @Override
    public void createView(Object iModel){}

    @Override
    public void setVisible(boolean b) {

    }
}
