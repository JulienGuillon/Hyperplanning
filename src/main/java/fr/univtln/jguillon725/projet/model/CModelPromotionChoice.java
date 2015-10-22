package fr.univtln.jguillon725.projet.model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by scaltot904 on 22/10/15.
 */
public class CModelPromotionChoice extends Observable implements Observer {
    private static final CModelPromotionChoice MODEL_PROMOTION_CHOICE = new CModelPromotionChoice();

    private CModelPromotionChoice(){}

    public static CModelPromotionChoice getInstance()
    {
        return MODEL_PROMOTION_CHOICE;
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

}
