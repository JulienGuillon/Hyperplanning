package fr.univtln.jguillon725.projet.controler;

import fr.univtln.jguillon725.projet.Ihm;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.CLoginGui;
import fr.univtln.jguillon725.projet.gui.CViewEdt;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.model.CModelEdt;
import fr.univtln.jguillon725.projet.model.CModelLogin;
import fr.univtln.jguillon725.projet.model.entities.CPerson;
import fr.univtln.jguillon725.projet.model.entities.CStudent;
import fr.univtln.jguillon725.projet.model.entities.CTeacher;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.SQLException;

/**
 * Created by julien on 13/10/15.
 */
public class CControleurLogin {
    private CLoginGui viewLogin;
    private CModelLogin modeleLogin;

    private Document loginModel = new PlainDocument();
    private Document passwordModel = new PlainDocument();

    public CControleurLogin(final CLoginGui viewLogin, CModelLogin modeleLogin) {
        this.viewLogin = viewLogin;
        this.modeleLogin = modeleLogin;

        DocumentListener ecouteurChangementTexte = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if ((loginModel.getLength() == 0 || passwordModel.getLength() == 0))
                    viewLogin.setConnexionOk(false);
                else
                    viewLogin.setConnexionOk(true);
            }
        };

        loginModel.addDocumentListener(ecouteurChangementTexte);
        passwordModel.addDocumentListener(ecouteurChangementTexte);
    }
    public Document getLoginModel()
    {
        return loginModel;
    }

    public Document getPasswordModel()
    {
        return passwordModel;
    }

    public void seConnecter() throws SQLException {
        try {
            CPerson person = modeleLogin.verifierIdentifiant(
                    loginModel.getText(0, loginModel.getLength()),
                    passwordModel.getText(0, passwordModel.getLength())
            );
            Ihm.person = person;
            this.viewLogin.setVisible(false);
            viewLogin.getTopLevelAncestor().setVisible(false);
            String role = person.getRole();
            switch (role){
                case "STUDENT":
                    Ihm.person.setRoleP(new CStudent(person));
                    break;
                case "TEACHER":
                    Ihm.person.setRoleP(new CTeacher(person));
                    break;
            }
            Ihm.person.afficherPlanning();

        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (PersistanceException e) {
            e.getException().printStackTrace();
        }
        clearIdentifiant();
    }

    public void clearIdentifiant() {
        try {
            loginModel.remove(0, loginModel.getLength());
            passwordModel.remove(0, passwordModel.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
