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
public class CPlanningTestGui extends JPanel {

    private final CModelEdt modelePlanningWeek;
    private final CControlerPlanningWeek controleurPlanningWeek;

    private final JPanel panelButton = new JPanel(new GridLayout(1, 2));
    private final JPanel panelPlanning = new JPanel(new GridBagLayout());

    private final String[] listDay = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] listHour= {"8h00", "8h30", "9h00", "9h30", "10h00", "10h30", "11h00", "11h30", "12h00", "12h30",
            "13h00", "13h30", "14h00", "14h30", "15h00", "15h30", "16h00", "16h30", "17h00", "17h30",
            "18h00", "18h30", "19h00"};

    private final JButton buttonnext = new JButton("Semaine suivante");
    private final JButton buttonprevious = new JButton("Semaine précédente");


    private final List<List<JLabel>> listPlanning = new ArrayList<List<JLabel>>();
    private List<JLabel> labelHour;

    public CPlanningTestGui(){
        modelePlanningWeek = null;
        controleurPlanningWeek = null;
    }

    public CPlanningTestGui(CModelEdt modelePlanningWeek)
    {
        setLayout(new GridBagLayout());
        this.modelePlanningWeek = modelePlanningWeek;
        this.controleurPlanningWeek = new CControlerPlanningWeek(new CPlanningWeekGui(), modelePlanningWeek);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

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

        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;

        for (String s: listDay)
        {
            c.gridx = c.gridx + 1;
            panelPlanning.add(new JLabel(s), c);
        }

        c.gridx = 0;

        for(String s: listHour)
        {
            c.gridy = c.gridy + 1;
            panelPlanning.add(new JLabel(s), c);
        }
        //panelPlanning.add(panel);

        c.gridx = 3;
        c.gridy = 6;
        c.gridheight = 5;
        JPanel labee = new JPanel();
        labee.setOpaque(true);
        labee.setBackground(Color.red);
        panelPlanning.add(labee, c);

       /** for (int j=1; j<7; j++) {
            panelPlanning.add(new JLabel(listDay[j]));
            labelHour = new ArrayList<JLabel>();
            for (int j2 = 1; j2 < 24; j2++) {
                JLabel label = new JLabel();
                panelPlanning.add(label);
                labelHour.add(label);
            }

            c.gridy = 0;
            c.gridx = j;
            panelPlanning.add(jPanel);
            listPlanning.add(labelHour);
        }

        **/

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.gridx = 1;
        c.gridheight = 1;
        add(panelPlanning, c);
        c.gridy = 2;
        panelButton.add(buttonprevious);
        panelButton.add(buttonnext);
        add(panelButton, c);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new CPlanningTestGui(new CModelEdt()));
        frame.setVisible(true);
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
