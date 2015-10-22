package fr.univtln.jguillon725.projet.model;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.ihm.CControlerProfile;
import fr.univtln.jguillon725.projet.ihm.CControlerPromotionChoice;
import fr.univtln.jguillon725.projet.ihm.IView;
import fr.univtln.jguillon725.projet.model.entities.CPromotion;

import javax.swing.*;
import java.util.List;

/**
 * Created by julien on 22/10/15.
 */
public class CPromotionChoiceGui extends JFrame implements IView {
    private final CModelPromotionChoice cModelPromotionChoice;
    private final CControlerPromotionChoice cControlerPromotionChoice;
    private List<CPromotion> promotions;

    public CPromotionChoiceGui() {
        this.cModelPromotionChoice = null;
        this.cControlerPromotionChoice = null;
    }

    private JComboBox comboBox1;
    private JButton validerButton;
    private JPanel panelPromotionChoice;

    @Override
    public void createView(Object iModel) throws PersistanceException {

    }

    public CPromotionChoiceGui(CModelPromotionChoice cModelPromotionChoice) throws PersistanceException {
        super("Choix promotion");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panelPromotionChoice);
        comboBox1.setSize(20, 50);

        this.cModelPromotionChoice = cModelPromotionChoice;
        this.cControlerPromotionChoice = new CControlerPromotionChoice(this, cModelPromotionChoice);

        promotions = cControlerPromotionChoice.getAllPromotion();
        afficherPromotion();
        this.setVisible(true);
    }

    public void afficherPromotion() {
        for (CPromotion promotion : this.promotions)
        {
            this.comboBox1.addItem(promotion.getTypePromotion()+" "+promotion.getDomainePromotion());
        }

    }
}
