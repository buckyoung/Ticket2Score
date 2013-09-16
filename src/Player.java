
import java.awt.Color;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author BuckYoung
 */
public class Player {

    //
    public enum PColor {

        BLUE, GREEN, YELLOW, RED, BLACK
    };
    public static ArrayList<PColor> chosenColors = new ArrayList(5);
    Color blue = new Color(100, 200, 240);
    Color green = new Color(140, 200, 100);
    Color yellow = new Color(240, 220, 100);
    Color red = new Color(255, 100, 100);
    Color black = new Color(102, 102, 102);

    public enum UndoType {

        BUILTROUTE, COMPLETEDDESTINATION, UNCOMPLETEDDESTINATION, REMOVEDESTINATION, SUBTRACTPOINTS, NULL
    };
    //
    private String name;
    private PColor color;
    private int playerNumber;
    private int score;
    private int remainingTrains;
    private int totalDestinationsCompleted;
    //
    private UndoType undoType;
    private int undoAmount;
    private String undoDestination;
    //
    public boolean hasLongest = false;
    public boolean hasGlobetrotter = false;
    //

    public Player(String name, PColor color, int playerNumber) {

        this.name = name;
        this.color = color;
        this.playerNumber = playerNumber;

        score = 0;
        remainingTrains = 45;
        totalDestinationsCompleted = 0;
        undoAmount = 0;
    }
    /*
     * player methods
     */

    public String getName() {
        return name;
    }

    public Color getColor() {
        if (color == PColor.BLUE) {
            return blue;
        }
        if (color == PColor.BLACK) {
            return black;
        }
        if (color == PColor.GREEN) {
            return green;
        }
        if (color == PColor.RED) {
            return red;
        }
        if (color == PColor.YELLOW) {
            return yellow;
        }
        return null;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
    /*
     * ACTION methods
     */

    public void addRoute(int length) {
        int score = ConvertPoints.fromTrainsToPoints(length);
        addToScore(score);
        setUndo(Player.UndoType.BUILTROUTE, score, null);
        removeFromRemainingTrains(length);
    }
    //

    public void addDestination(int score, String destination) {
        addToScore(score);
        setUndo(Player.UndoType.COMPLETEDDESTINATION, score, destination);
        addCompletedDestination();
    }

    public void removeDestination(int score, String destination) {
        removeFromScore(score);
        setUndo(Player.UndoType.REMOVEDESTINATION, score, destination);
        removeCompletedDestination();
    }

    public void uncompleteDestination(int score, String destination) {
        removeFromScore(score);
        setUndo(Player.UndoType.UNCOMPLETEDDESTINATION, score, destination);

    }
    
    public void removeUncompleted(int score, String destination){
        addToScore(score);
        setUndo(null,0,null);
    }
    //

    public void subtractPoints(int score) {
        removeFromScore(score);
        setUndo(Player.UndoType.SUBTRACTPOINTS, score, null);

    }

    public void addBonus(int score, String destination) {
        addToScore(score);
        if (score == 10) {
            hasLongest = true;
        }
        if (score == 15) {
            hasGlobetrotter = true;
        }

    }

    public void removeBonus(int score, String destination) {
        removeFromScore(score);
        if (score == 10) {
            hasLongest = false;
        }
        if (score == 15) {
            hasGlobetrotter = false;
        }


    }

    public void reset() {
        score = 0;
        remainingTrains = 45;
        totalDestinationsCompleted = 0;
        undoType = UndoType.NULL;
        undoAmount = 0;
        undoDestination = null;
        DestinationList.clear();
        hasLongest = false;
        hasGlobetrotter = false;
    }

    /*
     * score methods
     */
    private void setScore(int score) {
        this.score = score;
    }

    private void addToScore(int amount) {
        this.score += amount;
    }

    private void removeFromScore(int amount) {
        this.score -= amount;
    }

    public int getScore() {
        return score;
    }
    /*
     * undo methods
     */

    public void setUndo(UndoType undoType, int score, String destination) {
        this.undoType = undoType;
        this.undoAmount = score;
        this.undoDestination = destination;
    }

    public void undo() {
        if (undoType == UndoType.BUILTROUTE) {
            removeFromScore(undoAmount);
            addToRemainingTrains(ConvertPoints.fromPointsToTrains(undoAmount));
        } else if (undoType == UndoType.COMPLETEDDESTINATION) {
            removeFromScore(undoAmount);
            removeCompletedDestination();
            DestinationList.removeDestination(undoDestination, this);

        } else if (undoType == UndoType.SUBTRACTPOINTS) {
            addToScore(undoAmount);
        } else if (undoType == UndoType.REMOVEDESTINATION) {
            addToScore(undoAmount);
            addCompletedDestination();
            DestinationList.addDestination(undoDestination, this);
        } else if (undoType == UndoType.UNCOMPLETEDDESTINATION) {
            addToScore(undoAmount);
            DestinationList.removeUncompleted(undoDestination, this);
        }

        undoType = UndoType.NULL;
        undoAmount = 0;

    }

    public String getUndoInfo() {
        String result;
        if (undoType == UndoType.BUILTROUTE) {
            result = "(Undo Build Route: " + ConvertPoints.fromPointsToTrains(undoAmount) + ")";
        } else if (undoType == UndoType.COMPLETEDDESTINATION) {
            result = "(Undo Completed Destination or Bonus: " + undoAmount + "points)";
        } else if (undoType == UndoType.SUBTRACTPOINTS) {
            result = "(Undo Subtract: " + undoAmount + "points)";
        } else if (undoType == UndoType.UNCOMPLETEDDESTINATION) {
            result = "(Undo Subtract Destination " + undoAmount + "points)";
        } else if (undoType == UndoType.REMOVEDESTINATION) {
            result = "(Undo Remove Destination or Bonus)";
        } else {
            result = "(...)";
        }
        return result;
    }
    /*
     * completed destination methods
     */

    private void addCompletedDestination() {
        this.totalDestinationsCompleted += 1;
    }

    private void removeCompletedDestination() {
        this.totalDestinationsCompleted -= 1;
    }

    public int getCompletedDestinations() {
        return totalDestinationsCompleted;
    }

    /*
     * remaining trains methods
     */
    private void addToRemainingTrains(int amount) {
        this.remainingTrains += amount;
    }

    private void removeFromRemainingTrains(int amount) {
        this.remainingTrains -= amount;
    }

    public int getRemainingTrains() {
        return remainingTrains;
    }

    public boolean hasBonus(int score) {
        boolean result = false;
        if (score == 10 && hasLongest == true) {
            result = true;
        }
        if (score == 15 && hasGlobetrotter == true) {
            result = true;
        }
        return result;
    }

    public boolean hasGameEnd() {
        if (getRemainingTrains() < 3) {
            return true;
        } else {
            return false;
        }
    }
}
