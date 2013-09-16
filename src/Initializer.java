
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BuckYoung
 */
public class Initializer {

    public static void initializeGame(String gametype) {
        switch (gametype) {
            case "original":
                original();
                break;
            case "1910":
                nTen();
                break;
            case "mega":
                mega();
                break;
            case "big":
                big();
                break;
        }
    }

    //initializes original
    private static void original() {
        Ticket2Score.destinationDeck = new ArrayList(32); //30 dest, longest //seperator
        
        try {
            Scanner in = new Scanner(new FileReader("resources/original.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            Ticket2Score.destinationDeck.add(" ");
            in = new Scanner(new FileReader("resources/longest.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    //initializes 1910

    private static void nTen() {
        Ticket2Score.destinationDeck = new ArrayList(37); //35 dest, globetrot //seperator
        try {
            Scanner in = new Scanner(new FileReader("resources/1910.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            Ticket2Score.destinationDeck.add(" ");
            in = new Scanner(new FileReader("resources/globetrotter.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //initializes megagame

    private static void mega() {
        Ticket2Score.destinationDeck = new ArrayList(72); //69 dest, globe, longest //seperator
        try {
            Scanner in = new Scanner(new FileReader("resources/mega.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            Ticket2Score.destinationDeck.add(" ");
            in = new Scanner(new FileReader("resources/longest.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
             in = new Scanner(new FileReader("resources/globetrotter.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //initializes bigcities

    private static void big() {
        Ticket2Score.destinationDeck = new ArrayList(35); //35 dest.
        
        try {
            Scanner in = new Scanner(new FileReader("resources/big.dsf"));
            while (in.hasNextLine()) {
                Ticket2Score.destinationDeck.add(in.nextLine());
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Initializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
