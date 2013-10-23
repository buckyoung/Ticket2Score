
import java.util.ArrayList;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BuckYoung
 * Holds a destination list
 */
public class DestinationList {

    private static ArrayList<String> completedDestinations = new ArrayList(31); //30 destinations and longest route
    private static ArrayList<String> uncompletedDestinations = new ArrayList(31);
    private static ArrayList<Player> uncompletedPlayer = new ArrayList(31);
    private static ArrayList<Player> completedPlayer = new ArrayList(31);
    //For resetting

    /**
     * For resetting
     */
    public static void clear() {
        completedDestinations.clear();
        completedPlayer.clear();
        uncompletedDestinations.clear();
        uncompletedPlayer.clear();
    }

    /**
     * 
     * @param destination the destination string (to, from, and points)
     * @param player the player who is adding the destination
     * @return the point value of the destination just added
     */
    public static int addDestination(String destination, Player player) {
        completedDestinations.add(destination);
        completedPlayer.add(player);
        //update players destination count
        int score = parsePoints(destination);
        return score;

        //FIGURE OUT WHERE THIS GOES:
        /*
         if (isLongestRoute(destination, value)) {
         player.addLongest(value);
         } else {
         player.addDestination(value);
         }
         */
    }

    public static int addUncompleted(String destination, Player player) {
        uncompletedDestinations.add(destination);
        uncompletedPlayer.add(player);
        int score = parsePoints(destination);
        return score;
    }

    public static int removeDestination(String destination, Player player) {
        int index = completedDestinations.indexOf(destination);
        completedDestinations.remove(index);
        completedPlayer.remove(index);
        int score = parsePoints(destination);
        return score;
    }

    public static int removeUncompleted(String destination, Player player) {
        int index = uncompletedDestinations.indexOf(destination);
        uncompletedDestinations.remove(index);
        uncompletedPlayer.remove(index);
        int score = parsePoints(destination);
        return score;
    }

    public static boolean isCompleted(String destination) {
        boolean result = false;
        if (completedDestinations.contains(destination)) {
            result = true;
        }

        return result;
    }

    public static boolean isUncompleted(String destination) {
        boolean result = false;
        if (uncompletedDestinations.contains(destination)) {
            result = true;
        }
        return result;
    }
    public static boolean playerCompleted(String destination, Player player){
        boolean result = false;
        int index = completedDestinations.indexOf(destination);
        if (completedPlayer.get(index) == player){
            result = true;
        }
        return result;
    }
    public static boolean playerUncompleted(String destination, Player player){
         boolean result = false;
        int index = uncompletedDestinations.indexOf(destination);
        if (uncompletedPlayer.get(index) == player){
            result = true;
        }
        return result;
    }
    public static String getPlayerCompleted(String destination) {
        String result = "";
        int index = completedDestinations.indexOf(destination);
        result = completedPlayer.get(index).getName();
        return result;
    }

    public static String getPlayerUncompleted(String destination) {
        String result = "";
        int index = uncompletedDestinations.indexOf(destination);
        result = uncompletedPlayer.get(index).getName();
        return result;
    }

    public static int parsePoints(String text) {
        int result;
        int indexOf = text.indexOf(":") + 1;
        String substring = text.substring(indexOf);
        substring = substring.trim();
        result = Integer.parseInt(substring);
        return result;
    }

    public static boolean isBonus(String text) {
        boolean result = false;
        if (text.contains("Longest Route") || text.contains("Globetrotter")) {
            result = true;
        }
        return result;
    }

    /*
     * for more button frame
     */
    public static void printPlayerCompleted(JTextArea completedArea, Player player) {
        String found;
        int index = 0;
        int totalValue = 0;
        for (Player p : completedPlayer){
            if (p == player){
                found = completedDestinations.get(index);
                completedArea.append("+ " + found + "\n");
                totalValue+=parsePoints(found);
            } 
            index++;
        }
        completedArea.append("\nTotal: +"+ totalValue);
        double average = (double)totalValue/player.getCompletedDestinations();
        average = average*10;
        average = (int)average;
        average = average/10;
        completedArea.append("\n\n[Average Points per Destination: " + average +"]");
        
    }

    static void printPlayerUncompleted(JTextArea uncompletedArea, Player player) {
        String found;
        int index = 0;
        int totalValue = 0;
        for (Player p : uncompletedPlayer){
            if (p == player){
                found = uncompletedDestinations.get(index);
                uncompletedArea.append("- (" + found + ")\n");
                totalValue+=parsePoints(found);
            } 
            index++;
        }
        uncompletedArea.append("\nTotal: -" + totalValue);
    }
}
