package fr.univtln.jguillon725.projet.utils;

import java.awt.*;

/**
 * Created by julien on 29/10/15.
 */
public class CColor {
    static Color TP = Color.red;
    static Color TD = Color.blue;
    static Color CM = Color.green;

    public static Color getColor(String type)
    {
        switch (type)
        {
            case "TP":
                return TP;
            case "TD":
                return TD;
            case "CM":
                return CM;
            default:
                return null;
        }
    }

}
