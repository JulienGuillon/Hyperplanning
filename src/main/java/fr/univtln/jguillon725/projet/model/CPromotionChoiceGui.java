package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.ihm.IView;

import javax.swing.*;

/**
 * Created by julien on 22/10/15.
 */
public class CPromotionChoiceGui extends JFrame implements IView{
    private JComboBox comboBox1;
    private JButton validerButton;
    private JPanel panelPromotionChoice;

    @Override
    public void createView(Object iModel) throws PersistanceException {

    }

    public CPromotionChoiceGui()
    {
        super("Choix promotion");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelPromotionChoice);
        validerButton.setSize(20, 50);
        this.setVisible(true);
    }
}
