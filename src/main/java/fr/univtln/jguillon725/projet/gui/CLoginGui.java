package fr.univtln.jguillon725.projet.gui;

import fr.univtln.jguillon725.projet.controler.CControleurLogin;
import fr.univtln.jguillon725.projet.model.CModelLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by julien on 23/10/15.
 */
public class CLoginGui extends JPanel implements IView<CModelLogin> {
    private final CModelLogin modeleLogin;
    private final CControleurLogin controleurLogin;

    private final JPanel loginPanel = new JPanel(new GridBagLayout());
    private final JPanel reinitialiserPanel = new JPanel(new GridBagLayout());

    private final JButton connexionJButton = new JButton("Se connecter");

    private final JTextField loginJTextField;
    private final JPasswordField passwordJTextField;

    private final JLabel loginJLabel = new JLabel("Login");
    private final JLabel passwordJLabel = new JLabel("Password");

    public CLoginGui(){
        loginJTextField = null;
        passwordJTextField = null;
        controleurLogin = null;
        modeleLogin = null;
    }

    public CLoginGui(CModelLogin modeleLogin)
    {

        this.modeleLogin = modeleLogin;
        this.controleurLogin = new CControleurLogin(this, modeleLogin);

        connexionJButton.setEnabled(false);
        connexionJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            controleurLogin.seConnecter();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                    }
                });
            }
        });

        //Les modèles des champs textes sont dans le contrôleur
        loginJTextField = new JTextField(controleurLogin.getLoginModel(), "", 10);
        passwordJTextField = new JPasswordField(controleurLogin.getPasswordModel(), "", 10);

        GridBagConstraints c = new GridBagConstraints();
        //L'ajout d'un auteur
        loginPanel.setBorder(BorderFactory.createTitledBorder("Ajout"));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        loginPanel.add(loginJLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        loginPanel.add(loginJTextField, c);

        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(passwordJLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        loginPanel.add(passwordJTextField, c);

        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(connexionJButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(loginPanel, BorderLayout.CENTER);
    }

    public void setConnexionOk(boolean connexionOk) {
        connexionJButton.setEnabled(connexionOk);
    }

    @Override
    public void createView(CModelLogin iModel) {
        new CLoginGui(iModel);

    }
}
