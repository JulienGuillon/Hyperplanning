package fr.univtln.jguillon725.projet.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * BCrypt implements OpenBSD-style Blowfish password hashing using
 * the scheme described in "A Future-Adaptable Password Scheme" by
 * Niels Provos and David Mazieres.
 * <p>
 * This password hashing system tries to thwart off-line password
 * cracking using a computationally-intensive hashing algorithm,
 * based on Bruce Schneier's Blowfish cipher. The work factor of
 * the algorithm is parameterised, so it can be increased as
 * computers get faster.
 * <p>
 * Usage is really simple. To hash a password for the first time,
 * call the hashpw method with a random salt, like this:
 * <p>
 * <code>
 * String pw_hash = BCrypt.hashpw(plain_password, BCrypt.gensalt()); <br />
 * </code>
 * <p>
 * To check whether a plaintext password matches one that has been
 * hashed previously, use the checkpw method:
 * <p>
 * <code>
 * if (BCrypt.checkpw(candidate_password, stored_hash))<br />
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println("It matches");<br />
 * else<br />
 * &nbsp;&nbsp;&nbsp;&nbsp;System.out.println("It does not match");<br />
 * </code>
 * <p>
 * The gensalt() method takes an optional parameter (log_rounds)
 * that determines the computational complexity of the hashing:
 * <p>
 * <code>
 * String strong_salt = BCrypt.gensalt(10)<br />
 * String stronger_salt = BCrypt.gensalt(12)<br />
 * </code>
 * <p>
 * The amount of work increases exponentially (2**log_rounds), so
 * each increment is twice as much work. The default log_rounds is
 * 10, and the valid range is 4 to 30.
 *
 * @author Damien Miller
 * @version 0.2
 */

public class Password {
    private static int workload = 12;

    /**
     * This method can be used to generate a string representing an account password
     * suitable for storing in a database. It will be an OpenBSD-style crypt(3) formatted
     * hash string of length=60
     * The bcrypt workload is specified in the above static variable, a value from 10 to 31.
     * A workload of 12 is a very reasonable safe default as of 2013.
     * This automatically handles secure 128-bit salt generation and storage within the hash.
     * @param password_plaintext The account's plaintext password as provided during account creation,
     *			     or when changing an account's password.
     * @return String - a string of length 60 that is the bcrypt hashed password in crypt(3) format.
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return(hashed_password);
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g. during a login
     * request) with that of a stored hash from a database. The password hash from the database
     * must be passed as the second variable.
     * @param password_plaintext The account's plaintext password, as provided during a login request
     * @param stored_hash The account's stored password hash, retrieved from the authorization database
     * @return boolean - true if the password matches the password of the stored hash, false otherwise
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }

    /**
     * A simple test case for the main method, verify that a pre-generated test hash verifies successfully
     * for the password it represents, and also generate a new hash and ensure that the new hash verifies
     * just the same.
     */
    public static void main(String[] args) {
        String test_passwd = "abcdefghijklmnopqrstuvwxyz";
        String test_hash = "$2a$06$.rCVZVOThsIa97pEDOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC";

        System.out.println("Testing BCrypt Password hashing and verification");
        System.out.println("Test password: " + test_passwd);
        System.out.println("Test stored hash: " + test_hash);
        System.out.println("Hashing test password...");
        System.out.println();

        String computed_hash = hashPassword(test_passwd);
        System.out.println("Test computed hash: " + computed_hash);
        System.out.println();
        System.out.println("Verifying that hash and stored hash both match for the test password...");
        System.out.println();

        String compare_test = checkPassword(test_passwd, test_hash)
                ? "Passwords Match" : "Passwords do not match";
        String compare_computed = checkPassword(test_passwd, computed_hash)
                ? "Passwords Match" : "Passwords do not match";

        System.out.println("Verify against stored hash:   " + compare_test);
        System.out.println("Verify against computed hash: " + compare_computed);

        System.out.println(hashPassword("jguillon725"));

    }

}
