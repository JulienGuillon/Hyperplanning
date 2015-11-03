package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.controler.CControlerPlanningWeek;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelPlanningWeek;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.utils.CColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by julien on 28/10/15.
 */
public class CPlanningWeekGui extends JPanel {

    private final CModelEdt modelePlanningWeek;
    private final CControlerPlanningWeek controleurPlanningWeek;

    private final JPanel panelButton = new JPanel(new GridLayout(1, 2));
    private final JPanel panelPlanning = new JPanel(new GridLayout(1, 6));

    private final String[] listDay = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] listHour= {"8h00", "8h30", "9h00", "9h30", "10h00", "10h30", "11h00", "11h30", "12h00", "12h30",
            "13h00", "13h30", "14h00", "14h30", "15h00", "15h30", "16h00", "16h30", "17h00", "17h30",
            "18h00", "18h30", "19h00"};

    private final JButton buttonnext = new JButton("Semaine suivante");
    private final JButton buttonprevious = new JButton("Semaine précédente");


    private final List<List<JLabel>> listPlanning = new ArrayList<List<JLabel>>();
    private List<JLabel> labelHour;

    public CPlanningWeekGui(){
        modelePlanningWeek = null;
        controleurPlanningWeek = null;
    }

    public CPlanningWeekGui(CModelEdt modelePlanningWeek)
    {
        setLayout(new GridBagLayout());
        this.modelePlanningWeek = modelePlanningWeek;
        this.controleurPlanningWeek = new CControlerPlanningWeek(this, modelePlanningWeek);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        buttonprevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controleurPlanningWeek.getPlanningWeek(-1);
                } catch (PersistanceException e1) {
                    e1.printStackTrace();
                }
            }
        });
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

        JPanel jPanel = new JPanel(gridLayout);
        jPanel.setBackground(Color.white);
        for (int j=1; j<7; j++) {
            jPanel = new JPanel(gridLayout);
            jPanel.setBackground(Color.white);
            jPanel.add(new JLabel(listDay[j]));
            labelHour = new ArrayList<JLabel>();
            for (int j2 = 1; j2 < 24; j2++) {
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
        add(panelPlanning, c);
        c.gridy = 2;
        panelButton.add(buttonprevious);
        panelButton.add(buttonnext);
        add(panelButton, c);
        setVisible(true);
    }


    public void updatePlanning(List<List<CCourse>> listCourse)
    {
        int i=0;
        for(List<CCourse> courseDay: listCourse)
        {
            for(CCourse c: courseDay)
            {
                listPlanning.get(i).get((c.getHour() - 8) * 2).setText(c.getSubject());

                for(int j=(c.getHour()-8)*2; j<(c.getHour()-8)*2+c.getDuree()*2; j++)
                {
                    String type = c.getTypeCours();
                    listPlanning.get(i).get(j).setBackground(CColor.getColor(type));
                    listPlanning.get(i).get(j).setOpaque(true);
                }
            }
            i++;
        }
        revalidate();
    }}
