package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CPerson;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by julien on 13/10/15.
 */
public class CModelLogin extends Observable implements Observer {
    private static final CModelLogin MODEL_LOGIN = new CModelLogin();

    private CModelLogin(){}

    public static CModelLogin getInstance()
    {
        return MODEL_LOGIN;
    }


    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public CPerson verifierIdentifiant(String login, String password) throws PersistanceException
    {
        return CEntityManagerPerson.find(login, password);
    }


}
