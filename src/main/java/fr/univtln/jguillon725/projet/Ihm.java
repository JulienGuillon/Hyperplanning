package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.ConfigImportException;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.gui.IView;
import fr.univtln.jguillon725.projet.gui.CViewAffichage;
import fr.univtln.jguillon725.projet.model.entities.CPerson;
import fr.univtln.jguillon725.projet.utils.ConfigReader;

public class Ihm
{
    public static CPerson person;
    public static void main(String[] args) throws PersistanceException {
        try {
            //Importation des paramètres de configuration (cf. src/main/resources/config.xml)
            ConfigReader.importConfig();

            //On crée le modèle et la vue. Le contrôleur est créé dans la Vue.
            IView vueLogin = new CViewAffichage();
        } catch (ConfigImportException e) {
            e.printStackTrace();
        }
    }
}
