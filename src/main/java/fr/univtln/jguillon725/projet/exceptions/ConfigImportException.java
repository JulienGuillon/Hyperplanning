package fr.univtln.jguillon725.projet.exceptions;

/**
 * Cette classe représente les erreurs émise lors de l'importation des paramètes de l'application.
 * L'erreur d'origine est accessible via getException().
 */
public class ConfigImportException extends Throwable {
    private Exception exception;

    public ConfigImportException(Exception e) {
    }

    public Exception getException() {
        return exception;
    }
}
