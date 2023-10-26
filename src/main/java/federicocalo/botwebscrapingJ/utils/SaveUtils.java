package federicocalo.botwebscrapingJ.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class SaveUtils {
    public static final String risultati = "./risultati";
    public static final String risultatiTC = "./risultati/TotalCorner";
    public static final String risultatiASB = "./risultati/AsianBet";
    public static final String risultatiExcelTC = "./risultati/TotalCorner/risultatiTC.xlsx";
    public static final String risultatiExcelASB = "./risultati/AsianBet/risultatiASB.xlsx";

    public static final String risultatiExcelTC_Filtrati = "./risultati/TotalCorner/risultatiTC_Filtrati.xlsx";
    public static final String risultatiExcelASB_Filtrati = "./risultati/AsianBet/risultatiASB_Filtrati.xlsx";


    public static void checkFolder(){
        // Crea un oggetto File con il percorso specificato
        File cartellaR = new File(risultati);
        File cartellaTC = new File(risultatiTC);
        File cartellaAB = new File(risultatiASB);

        // Verifica se la cartella esiste
        if (cartellaR.exists() && cartellaR.isDirectory()) {
            System.out.println("La cartella esiste.");
        } else {
            // La cartella non esiste, quindi proviamo a crearla
            boolean creata = cartellaR.mkdir();
            if (creata) {
                System.out.println("La cartella è stata creata con successo.");
            } else {
                System.out.println("Impossibile creare la cartella.");
            }
        }

        // Verifica se la cartella esiste
        if (cartellaTC.exists() && cartellaTC.isDirectory()) {
            System.out.println("La cartella esiste.");
        } else {
            // La cartella non esiste, quindi proviamo a crearla
            boolean creata = cartellaTC.mkdir();
            if (creata) {
                System.out.println("La cartella è stata creata con successo.");
            } else {
                System.out.println("Impossibile creare la cartella.");
            }
        }

        // Verifica se la cartella esiste
        if (cartellaAB.exists() && cartellaAB.isDirectory()) {
            System.out.println("La cartella esiste.");
        } else {
            // La cartella non esiste, quindi proviamo a crearla
            boolean creata = cartellaAB.mkdir();
            if (creata) {
                System.out.println("La cartella è stata creata con successo.");
            } else {
                System.out.println("Impossibile creare la cartella.");
            }
        }
    }


}
