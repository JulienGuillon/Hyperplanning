package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;

/**
 * Created by julien on 15/10/15.
 */
public interface IView<T>  {
    public void createView(T iModel) throws PersistanceException;

    void setVisible(boolean b);
}
