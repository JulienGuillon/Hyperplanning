package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.controler.CControlerPromotionChoice;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.model.CModelPromotionChoice;
import fr.univtln.jguillon725.projet.model.entities.CPromotion;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 * Created by julien on 22/10/15.
 */
public class CPromotionChoiceGui extends JPanel implements IView {
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

        comboBox1.setSize(20, 50);

        this.cModelPromotionChoice = cModelPromotionChoice;
        this.cControlerPromotionChoice = new CControlerPromotionChoice(this, cModelPromotionChoice);

        promotions = cControlerPromotionChoice.getAllPromotion();
        afficherPromotion();
        comboBox1.setEditable(true);
        comboBox1.setMaximumRowCount(10);


        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        System.out.println(comboBox1.getItemAt(comboBox1.getSelectedIndex()));
                        break;
                    case ItemEvent.SELECTED:
                        System.out.println(comboBox1.getItemAt(comboBox1.getSelectedIndex()));
                        break;
                }
            }
        });
    }

    public void afficherPromotion() {
        for (CPromotion promotion : this.promotions)
        {
            this.comboBox1.addItem(promotion.getTypePromotion()+" "+promotion.getDomainePromotion());
        }

    }
}
