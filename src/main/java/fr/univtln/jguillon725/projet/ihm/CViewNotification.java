package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelNotification;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by julien on 20/10/15.
 */
public class CViewNotification extends JFrame implements IView<CModelNotification> {
    private final CModelNotification modelNotification;
    private final CControlerNotification controlerNotification;
    private final JList notificationJList;

    @Override
    public void createView(CModelNotification modelNotification) throws PersistanceException {
        new CViewNotification(modelNotification);
    }

    public CViewNotification(CModelNotification modelNotification)
    {
        super("Notification");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.modelNotification = modelNotification;
        this.controlerNotification = new CControlerNotification(this, modelNotification);
        notificationJList = new JList<>();
        notificationJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            }
        });
        getContentPane().add(notificationJList, BorderLayout.CENTER);
        this.setVisible(true);

    }

}
