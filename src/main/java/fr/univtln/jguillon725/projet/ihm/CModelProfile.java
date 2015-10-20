package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CEntityManagerPerson;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class CModelProfile extends Observable implements Observer{
    private static final CModelProfile MODEL_PROFILE = new CModelProfile();

    private CModelProfile(){}

    public static CModelProfile getInstance()
    {
        return MODEL_PROFILE;
    }


    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public List IdentResearch(String login) throws PersistanceException
    {
        return CEntityManagerPerson.research(login);
    }

}
