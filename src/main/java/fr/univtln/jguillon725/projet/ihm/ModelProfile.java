package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CPerson;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class ModelProfile extends Observable implements Observer{
    private static final ModelProfile MODEL_PROFILE = new ModelProfile();

    private ModelProfile(){}

    public static ModelProfile getInstance()
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
