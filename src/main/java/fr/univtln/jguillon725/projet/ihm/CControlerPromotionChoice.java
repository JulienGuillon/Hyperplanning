package fr.univtln.jguillon725.projet.ihm;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelPromotionChoice;
import fr.univtln.jguillon725.projet.model.CPromotionChoiceGui;
import fr.univtln.jguillon725.projet.model.entities.CPromotion;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scaltot904 on 22/10/15.
 */
public class CControlerPromotionChoice {
    private IView cPromotionChoiceGui;
    private CModelPromotionChoice cModelPromotionChoice;

    private Document choicePromotion = new PlainDocument();

    public CControlerPromotionChoice (final CPromotionChoiceGui cPromotionChoiceGui, final CModelPromotionChoice cModelPromotionChoice) {
        this.cPromotionChoiceGui = cPromotionChoiceGui;
        this.cModelPromotionChoice = cModelPromotionChoice;
    }

    public Document getChoicePromotion()
    {
        return choicePromotion;
    }

    public List<CPromotion> getAllPromotion() throws PersistanceException {
        return cModelPromotionChoice.getAllPromotion();
    }

/*
    public void retour(){
        this.cPromotionChoiceGui.setVisible(false);
        CModelEdt cModelEdt = new CModelEdt();
        try {
            cPromotionChoiceGui = new CViewEdt(cModelEdt);
        } catch (PersistanceException e) {
            e.printStackTrace();
        }
        this.cPromotionChoiceGui.setVisible(true);
    }
*/
}
