package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.ModelProfile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class ViewProfile extends JFrame implements IView <ModelProfile>{
    private final ModelProfile modelProfile;


    public ViewProfile (){
        modelProfile = null;
    }



    public ViewProfile(ModelProfile modelProfile) {
        super("Profil Utilisateur");
        this.modelProfile = modelProfile;
        setSize(800, 600);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("PROFIIIIIIIILE");

        panel.add(label);



        getContentPane().add(panel, BorderLayout.CENTER);
    }





    @Override
    public void createView(ModelProfile iModel) throws PersistanceException {
        new ViewProfile(iModel);
    }


}
