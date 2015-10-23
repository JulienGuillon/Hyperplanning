package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.ihm.CControlerNotification;
import fr.univtln.jguillon725.projet.ihm.CControlerProfile;
import fr.univtln.jguillon725.projet.ihm.IView;
import fr.univtln.jguillon725.projet.model.CModelNotification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scaltot904 on 22/10/15.
 */
public class CNotificationGui extends JFrame implements IView<CModelNotification> {

    private JPanel panelNotification;
    private JButton retourButton;
    private CModelNotification cModelNotification;
    private CControlerNotification cControlerNotification;

    public CNotificationGui(CModelNotification cModelNotification)
    {
        super("Centre de notifications");
        this.cModelNotification = cModelNotification;
        this.cControlerNotification = new CControlerNotification(this, cModelNotification);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelNotification);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cControlerNotification.retour();
            }
        });

        this.setVisible(true);
    }

    @Override
    public void createView(CModelNotification cModelNotification) throws PersistanceException {
        new CNotificationGui(cModelNotification);
    }

}
