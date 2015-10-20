package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class CViewProfile extends JFrame implements IView <CModelProfile>{
    private final CModelProfile CModelProfile;
    private final CControlerProfile cControlerProfile;

    public CViewProfile(){
        CModelProfile = null;
        cControlerProfile = null;
    }



    public CViewProfile(CModelProfile CModelProfile) {
        super("Profil Utilisateur");
        this.CModelProfile = CModelProfile;
        this.cControlerProfile = new CControlerProfile(this, CModelProfile);
        setSize(800, 600);
        JPanel panel = new JPanel();

        final JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cControlerProfile.retour();
            }
        });


        getContentPane().add(retour, BorderLayout.SOUTH);
        getContentPane().add(panel, BorderLayout.CENTER);
    }





    @Override
    public void createView(CModelProfile iModel) throws PersistanceException {
        new CViewProfile(iModel);
    }


}
