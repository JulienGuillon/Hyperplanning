package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import javax.swing.*;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class CProfileGui extends JFrame implements IView {


    private JPanel panelProfile;
    private JButton retourButton;

    public CProfileGui()
    {
        super("Profil utilisateur");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelProfile);

        this.setVisible(true);
    }

    @Override
    public void createView(Object iModel) throws PersistanceException {
        new CProfileGui();
    }
}
