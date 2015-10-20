package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.ConfigImportException;
import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.ihm.IView;
import fr.univtln.jguillon725.projet.ihm.CViewAffichage;
import fr.univtln.jguillon725.projet.utils.ConfigReader;

/**
 * Hello world!
 *
 */
public class Ihm
{
    public static void main(String[] args) throws PersistanceException {
        try {
            //Importation des paramètres de configuration (cf. src/main/resources/config.xml)
            ConfigReader.importConfig();

            //On crée le modèle et la vue. Le contrôleur est créé dans la Vue.
            IView vueLogin = new CViewAffichage();
        } catch (ConfigImportException e) {
            e.printStackTrace();
        }
    }}