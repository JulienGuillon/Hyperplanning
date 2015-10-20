package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.entities.CRoom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by julien on 15/10/15.
 */
public class CViewEdt extends JFrame implements IView<CModelEdt> {
    private final CModelEdt modelEdt;
    private final CControlerEdt controleurEdt;

    private final JPanel panelInformation = new JPanel(new GridBagLayout());
    private final JPanel panelEdt = new JPanel(new GridBagLayout());
    private final JPanel panelPlanning = new JPanel(new GridLayout(1, 6));

    private final JPanel panelDay = new JPanel(new GridBagLayout());
    private final JPanel panelHour = new JPanel(new GridBagLayout());

    private final String[] listDay = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

    private final JLabel[] labelDay = new JLabel[listDay.length];
    private final JLabel[] labelHour = new JLabel[20];

    private final JLabel connectionJLabel = new JLabel("Vous etes connecté");

    public void createView(CModelEdt modelEdt) throws PersistanceException {
        new CViewEdt(modelEdt);
    }

    public CViewEdt(CModelEdt modelEdt) throws PersistanceException {
        super("EDT");
        setSize(800, 600);
        this.modelEdt = modelEdt;
        this.controleurEdt = new CControlerEdt(this, modelEdt);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        /** int i = 0;
         for(String s: listDay)
         {
         labelDay[i] = new JLabel(listDay[i]);
         c.gridx = i;
         c.gridy = 0;
         c.insets = new Insets(10,10,10,10);
         panelDay.add(labelDay[i], c);
         i++;
         }

         for(int j=8; j<19; j++)
         {
         labelHour[j-8] = new JLabel(Integer.toString(j));
         c.gridx = 0;
         c.gridy = j-8;
         panelHour.add(labelHour[j-8], c);
         }
         **/
        this.connectionJLabel.setText(connectionJLabel.getText() + " sous le nom " + modelEdt.getPerson().getNom());
        c.gridx = 0;
        c.gridy = 0;
        panelInformation.add(this.connectionJLabel, c);
/**
 c.gridwidth = 7;
 panelEdt.setPreferredSize(new Dimension(100, 100));
 panelEdt.add(panelDay, c);

 c.fill = GridBagConstraints.VERTICAL;
 c.gridy = 1;

 panelHour.setBackground(Color.red);
 c.anchor = GridBagConstraints.WEST;
 getContentPane().add(panelHour, BorderLayout.WEST);
 */

        for (int j = 0; j < 7; j++) {
            JPanel panel = new JPanel(new GridLayout(20, 1));
            panel.setBackground(Color.white);
            Border border = BorderFactory.createLineBorder(Color.black, 1);
            panel.add(new Label(listDay[j]));
            for (int j2 = 1; j2 < 20; j2++) {
                JLabel label = new JLabel();
                label.setBorder(border);
                panel.add(label);
            }
            panelPlanning.add(panel);
        }

        for (int j = 8; j < 28; j++) {

        }

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.gridx = 1;
        panelEdt.add(panelPlanning, c);

        c.fill = GridBagConstraints.HORIZONTAL;

        //controleurEdt.getPlanningWeek(panelPlanning);

        getContentPane().add(panelInformation, BorderLayout.NORTH);
        c.gridx = 0;
        c.gridy = 1;

        JButton jButton = new JButton("Profil");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurEdt.getProfile();
            }
        });
        getContentPane().add(jButton, BorderLayout.WEST);
        getContentPane().add(panelEdt, BorderLayout.CENTER);

        setVisible(true);
    }
}