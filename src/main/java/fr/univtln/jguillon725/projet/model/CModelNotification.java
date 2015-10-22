package fr.univtln.jguillon725.projet.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by julien on 20/10/15.
 */
public class CModelNotification extends Observable implements Observer {
    private static final CModelNotification MODEL_NOTIFICATION = new CModelNotification();

    private CModelNotification(){}

    public static CModelNotification getInstance()
    {
        return MODEL_NOTIFICATION;
    }


    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }


}
