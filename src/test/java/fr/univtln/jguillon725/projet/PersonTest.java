package fr.univtln.jguillon725.projet;

import fr.univtln.jguillon725.projet.exceptions.PersistanceException;
import fr.univtln.jguillon725.projet.model.entities.CPerson;
import junit.framework.TestCase;

import java.sql.SQLException;

/**
 * Created by julien on 15/10/15.
 */
public class PersonTest extends TestCase {

    private CPerson person;

    public PersonTest(String pName) {

        super(pName);

    }



    protected void setUp() throws Exception {

        super.setUp();

        person = new CPerson("nom", "role", "login");

    }

    protected void tearDown() throws Exception {

        super.tearDown();

        person = null;

    }



    public void testPersonne() {

        assertNotNull("L'instance est créée", person);

    }

    public void testGetNom() {

        assertEquals("Est ce que nom est correct", "nom", person.getNom());

    }



    public void testSetNom() {

        person.setNom("nom2");

        assertEquals("Est ce que nom est correct", "nom2", person.getNom());

    }

    public void testGetRole() {
        assertEquals("Est ce que le role est correct", "role", person.getRole());
    }

    public void testSetRole() {

        person.setRole("role2");

        assertEquals("Est ce que le role est correct", "role2", person.getRole());

    }

    public void testGetLogin() {
        assertEquals("Est ce que le login est correct", "login", person.getLogin());
    }

    public void testSetLogin() {

        person.setLogin("login2");

        assertEquals("Est ce que le login est correct", "login2", person.getLogin());

    }

    public void testEquals() throws PersistanceException, SQLException {
        CPerson person2 = person;
        assertTrue("Est ce que deux meme personnes sont égales", person.equals(person2));
        CPerson person3 = new CPerson(person.getNom(), person.getRole(), person.getLogin());
        assertTrue("Est ce que deux personnes de meme attributs sont égales", person.equals(person3));

    }

    public void testCompareTo()
    {
        CPerson person2 = person;
        assertEquals("Est ce que la comparaison de deux meme personnes est correcte", 0, person.compareTo(person2));
    }

    public void testHashCode() throws PersistanceException, SQLException {
        CPerson person2 = person;
        assertEquals("Est ce que les hashCode de deux memes personnes sont egaux", person.hashCode(), person2.hashCode());
        CPerson person3 = new CPerson(person.getNom(), person.getRole(), person.getLogin());
        assertEquals("Est ce que les hashCode de deux personnes de meme attributs sont egaux", person.hashCode(), person3.hashCode());

    }
}

