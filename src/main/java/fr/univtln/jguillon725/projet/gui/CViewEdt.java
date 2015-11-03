package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.controler.CControlerEdt;
import fr.univtln.jguillon725.projet.controler.CControlerPlanningWeek;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CEntityManagerCourse;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelPlanningWeek;
import fr.univtln.jguillon725.projet.model.entities.CCourse;
import fr.univtln.jguillon725.projet.utils.CColor;

import javax.swing.*;
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
    private final CControlerPlanningWeek controlerPlanningWeek;

    private final JPanel panelInformation = new JPanel(new GridBagLayout());
    private final CPlanningWeekGui panelPlanningWeek = new CPlanningWeekGui(new CModelEdt());
    private final CReservationGui panelReservation;

    private final JPanel panelPlanningDay = new JPanel(new GridLayout(1, 2));
    private final JPanel panelPlanning = new JPanel(new GridLayout(1, 6));
    private final JPanel panelChoice = new JPanel(new GridLayout(1,5));
    private final JButton buttonNotification = new JButton("Notification");
    private final JTabbedPane tabbedPane = new JTabbedPane();

    private final String[] listDay = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] listHour= {"8h00", "8h30", "9h00", "9h30", "10h00", "10h30", "11h00", "11h30", "12h00", "12h30",
            "13h00", "13h30", "14h00", "14h30", "15h00", "15h30", "16h00", "16h30", "17h00", "17h30",
            "18h00", "18h30", "19h00"};


    private final List<List<JLabel>> listPlanning = new ArrayList<List<JLabel>>();
    private List<JLabel> labelHour;
    private List<JLabel> labelHourDay;

    private final JLabel connectionJLabel = new JLabel("Vous etes connecté");

    public void createView(CModelEdt modelEdt) throws PersistanceException {
        new CViewEdt(modelEdt);
    }

    public CViewEdt(CModelEdt modelEdt) throws PersistanceException {
        super("EDT");
        setSize(800, 600);
        this.modelEdt = modelEdt;
        this.controleurEdt = new CControlerEdt(this, modelEdt);
        this.controlerPlanningWeek = new CControlerPlanningWeek(panelPlanningWeek, modelEdt);
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


        panel = new JPanel(gridLayout);
        panel.setBackground(Color.white);

        panel.add(new JLabel(listDay[0]), c);
        for(String s: listHour)
        {
            panel.add(new JLabel(s));
        }
        panelPlanningDay.add(panel);

        JPanel jPanel = new JPanel(gridLayout);
        jPanel.setBackground(Color.white);
        jPanel.add(new JLabel(listDay[1]));
        labelHourDay = new ArrayList<JLabel>();
        for(int j2=1; j2<24; j2++)
        {
            JLabel label = new JLabel();
            jPanel.add(label);
            labelHourDay.add(label);
        }
        panelPlanningDay.add(jPanel);


        for (int j=1; j<7; j++)
        {
            jPanel = new JPanel(gridLayout);
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
        //panelEdt.add(panelPlanning, c);

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

        buttonNotification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        controleurEdt.notification();
                    }
                });
            }
        });
        panelChoice.add(buttonNotification);

        controlerPlanningWeek.getPlanningWeek(0);
        controleurEdt.getPlanningDay();

        tabbedPane.addTab("Semaine", panelPlanningWeek);
        tabbedPane.addTab("Jour", panelPlanningDay);
        panelReservation = new CReservationGui();
        tabbedPane.addTab("Reservation", panelReservation);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JPanel getPanelEdt() {
        return panelPlanningWeek;
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
                    listPlanning.get(i).get(j).setBackground(Color.red);
                    listPlanning.get(i).get(j).setOpaque(true);
                }
            }
            i++;
        }
        revalidate();
    }

    public void updateDay(List<CCourse> listCourse)
    {
        for(CCourse c: listCourse)
        {
            labelHourDay.get((c.getHour() - 8) * 2).setText(c.getSubject());

            for(int j=(c.getHour()-8)*2; j<(c.getHour()-8)*2+c.getDuree()*2; j++)
            {
                labelHourDay.get(j).setBackground(Color.red);
                labelHourDay.get(j).setOpaque(true);
            }
        }
        revalidate();
    }
}

