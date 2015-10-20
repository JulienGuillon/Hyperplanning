package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.ihm.IView;

import javax.swing.*;

/**
 * Created by scaltot904 on 20/10/15.
 */
public class CProfileGui extends JFrame implements IView {
    private JTextField textField;
    private JTextField textField1;
    private JPanel panelProfile;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;

    public CProfileGui()
    {
        super("Profil utilisateur");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelProfile);

        this.setVisible(true);

    }

    @Override
    public void createView(Object iModel) throws PersistanceException {
        new CProfileGui();
    }
}
