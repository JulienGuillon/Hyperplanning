package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import javax.swing.*;

/**
 * Created by julien on 15/10/15.
 */
public interface IView<T> {
    public void createView(T iModel) throws PersistanceException;

    void setVisible(boolean b);
}
