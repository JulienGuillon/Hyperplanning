package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.controler.CControlerInitialisation;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelInitialisation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by julien on 20/10/15.
 */
public class CViewInitialisation extends JFrame implements IView<CModelInitialisation> {
    private final CControlerInitialisation controlerInitialisation;
    private JButton buttonVisitor = new JButton("Consultation en tant que visiteur");
    private JButton buttonUser = new JButton("Consultation en tant qu'utilisateur");
    private JPanel panelChoice = new JPanel(new GridBagLayout());

    @Override
    public void createView(CModelInitialisation modelInitialisation) throws PersistanceException {
        new CViewInitialisation(modelInitialisation);
    }

    public CViewInitialisation()
    {
        controlerInitialisation = null;
    }

    public CViewInitialisation(CModelInitialisation modelInitialisation)
    {
        super("Initialisaton de connexion");
        setSize(800,600);
        this.controlerInitialisation = new CControlerInitialisation(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonVisitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlerInitialisation.connexion();
                } catch (PersistanceException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controlerInitialisation.identification();
                } catch (PersistanceException e1) {
                    e1.printStackTrace();
                }
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        panelChoice.add(buttonVisitor, c);
        c.gridx = 1;
        c.gridy = 2;
        panelChoice.add(buttonUser, c);
        getContentPane().add(panelChoice, BorderLayout.CENTER);

        setVisible(true);
    }


}
