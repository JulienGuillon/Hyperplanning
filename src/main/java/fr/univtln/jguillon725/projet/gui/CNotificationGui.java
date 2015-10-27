package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.controler.CControlerNotification;
import fr.univtln.jguillon725.projet.model.CModelNotification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scaltot904 on 22/10/15.
 */
public class CNotificationGui extends JPanel implements IView<CModelNotification> {

    private JPanel panelNotification;
    private JButton retourButton;
    private JTextField textField1;
    private CModelNotification cModelNotification;
    private CControlerNotification cControlerNotification;

    public CNotificationGui(CModelNotification cModelNotification)
    {
        this.cModelNotification = cModelNotification;
        this.cControlerNotification = new CControlerNotification(this, cModelNotification);
        add(panelNotification);
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cControlerNotification.retour();
                    }
                });
                hilo.start();
            }
        });

        this.setVisible(true);
    }

    @Override
    public void createView(CModelNotification cModelNotification) throws PersistanceException {
        new CNotificationGui(cModelNotification);
    }

}
