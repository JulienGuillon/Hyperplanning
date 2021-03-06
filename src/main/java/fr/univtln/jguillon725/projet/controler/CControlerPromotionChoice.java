package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CLoginGui;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.model.CModelPromotionChoice;
import fr.univtln.jguillon725.projet.gui.CPromotionChoiceGui;
import fr.univtln.jguillon725.projet.model.entities.CPerson;
import fr.univtln.jguillon725.projet.model.entities.CPromotion;
import fr.univtln.jguillon725.projet.model.entities.CVisitor;

import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by scaltot904 on 22/10/15.
 */
public class CControlerPromotionChoice {
    private CPromotionChoiceGui viewLogin;
    private CModelPromotionChoice cModelPromotionChoice;

    private Document choicePromotion = new PlainDocument();

    public CControlerPromotionChoice (final CPromotionChoiceGui viewLogin, final CModelPromotionChoice cModelPromotionChoice) {
        this.viewLogin = viewLogin;
        this.cModelPromotionChoice = cModelPromotionChoice;
    }

    public Document getChoicePromotion()
    {
        return choicePromotion;
    }

    public List<CPromotion> getAllPromotion() throws PersistanceException {
        return cModelPromotionChoice.getAllPromotion();
    }

    public void afficherPlanning() throws PersistanceException, SQLException {
        this.viewLogin.setVisible(false);
        viewLogin.getTopLevelAncestor().setVisible(false);
        CPromotion promotion = (CPromotion) viewLogin.getComboBox1().getItemAt(viewLogin.getComboBox1().getSelectedIndex());
        CPerson person = new CPerson("visiteur","visiteur","visiteur");
        Ihm.person = person;
        Ihm.person.setRoleP(new CVisitor(person, promotion.getIdPromotion()));
        Ihm.person.afficherPlanning();
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
