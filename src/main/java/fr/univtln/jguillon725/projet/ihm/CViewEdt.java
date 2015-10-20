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
    private final JPanel panelChoice = new JPanel(new GridLayout(1,5));
    private final JButton buttonNotification = new JButton("Notification");

    private final String[] listDay = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] listHour= {"8h00", "8h30", "9h00", "9h30", "10h00", "10h30", "11h00", "11h30", "12h00", "12h30",
            "13h00", "13h30", "14h00", "14h30", "15h00", "15h30", "16h00", "16h30", "17h00", "17h30",
            "18h00", "18h30", "19h00"};


    private final List<List<JLabel>> listPlanning = new ArrayList<List<JLabel>>();
    private List<JLabel> labelHour;

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

        this.connectionJLabel.setText(connectionJLabel.getText() + " sous le nom " + modelEdt.getPerson().getNom());
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        panelInformation.add(this.connectionJLabel, c);


        GridLayout gridLayout = new GridLayout(24,1);
        gridLayout.setHgap(5);
        JPanel panel = new JPanel(gridLayout);
        panel.setBackground(Color.white);

        panel.add(new JLabel(listDay[0]), c);
        for(String s: listHour)
        {
            panel.add(new JLabel(s));
        }
        panelPlanning.add(panel);


        for (int j=1; j<7; j++)
        {
            JPanel jPanel = new JPanel(gridLayout);
            jPanel.setBackground(Color.white);
            jPanel.add(new JLabel(listDay[j]));
            labelHour = new ArrayList<JLabel>();
            for(int j2=1; j2<24; j2++)
            {
                JLabel label = new JLabel();
                jPanel.add(label);
                labelHour.add(label);
            }

            c.gridy = 0;
            c.gridx = j;
            panelPlanning.add(jPanel);
            listPlanning.add(labelHour);
        }


        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.gridx = 1;
        panelEdt.add(panelPlanning, c);

        c.fill = GridBagConstraints.HORIZONTAL;

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
        panelChoice.add(jButton);

        getContentPane().add(panelChoice, BorderLayout.SOUTH);
        getContentPane().add(panelEdt, BorderLayout.CENTER);

        buttonNotification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleurEdt.notification();
            }
        });
        panelChoice.add(buttonNotification);

        listPlanning.get(0).get(0).setText("Math");
        listPlanning.get(0).get(0).setBackground(Color.red);
        listPlanning.get(0).get(1).setBackground(Color.red);
        listPlanning.get(0).get(2).setBackground(Color.red);
        listPlanning.get(0).get(3).setBackground(Color.red);

        listPlanning.get(0).get(0).setOpaque(true);
        listPlanning.get(0).get(1).setOpaque(true);
        listPlanning.get(0).get(2).setOpaque(true);
        listPlanning.get(0).get(3).setOpaque(true);

        setVisible(true);
    }

}