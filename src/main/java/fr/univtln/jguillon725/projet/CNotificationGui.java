package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.ihm.IView;

import javax.swing.*;

/**
 * Created by scaltot904 on 22/10/15.
 */
public class CNotificationGui extends JFrame implements IView {


    private JPanel panelNotification;
    private JButton retourButton;


    public CNotificationGui()
    {
        super("Centre de notifications");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelNotification);

        this.setVisible(true);
    }

    @Override
    public void createView(Object iModel) throws PersistanceException {
        new CNotificationGui();
    }

}
