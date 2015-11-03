package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.controler.CReservationControler;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CTeacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by julien on 22/10/15.
 */
public class CReservationGui extends JPanel {
    private final CReservationControler reservationControler;

    private JButton button1;
    private JPanel panelReservation;
    private JTextField textField1;
    private JComboBox comboBoxPromotion;
    private JComboBox comboBoxCreneau;
    private JComboBox comboBoxSalle;
    private JCheckBox Materiel;
    private JComboBox comboBoxMateriel;

    public CReservationGui() throws PersistanceException {
        reservationControler = new CReservationControler(this);
        comboBoxMateriel.setEnabled(false);
        Materiel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationControler.changedState();
            }
        });
        add(panelReservation);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateCreneau(textField1.getText());
                } catch (PersistanceException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public JButton getButton1() {
        return button1;
    }

    public JPanel getPanelReservation() {
        return panelReservation;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JComboBox getComboBoxPromotion() {
        return comboBoxPromotion;
    }

    public JComboBox getComboBoxCreneau() {
        return comboBoxCreneau;
    }

    public JComboBox getComboBoxSalle() {
        return comboBoxSalle;
    }

    public JCheckBox getMateriel() {
        return Materiel;
    }

    public JComboBox getComboBoxMateriel() {
        return comboBoxMateriel;
    }


    public void setChoiceEnable(boolean ok)
    {
        this.comboBoxMateriel.setEnabled(ok);
    }

    public void updateCreneau(String date) throws PersistanceException {
        boolean[] listCreneau = Ihm.person.creneauDispoDay(date);
        if (listCreneau != null) {
            for (int i = 0; i < listCreneau.length - 1; i++) {
                if (listCreneau[i] && listCreneau[i + 1])
                    comboBoxCreneau.addItem((i + 16) / 2F);
            }
        }
    }
}
