package fr.univtln.jguillon725.projet.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by julien on 20/10/15.
 */
public class CModelInitialisation extends Observable implements Observer {
    private static final CModelInitialisation MODEL_INITIALISATION = new CModelInitialisation();

    private CModelInitialisation() {
    }

    public static CModelInitialisation getInstance() {
        return MODEL_INITIALISATION;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
