package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class CProfileGui extends JPanel implements IView {


    private JPanel panelProfile;
    private JButton retourButton;

    public CProfileGui()
    {
        add(panelProfile);
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("retour");
            }
        });


    }

    @Override
    public void createView(Object iModel) throws PersistanceException {
        new CProfileGui();
    }
}
