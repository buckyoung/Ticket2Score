
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/*
 *
 * BuckYoung
 * Apr 30, 2013
 *
 */
public class Ticket2Score {

    public static int numberOfPlayers;
    public static Player[] players;
    public static int playerSet = 0;
    public static ArrayList<String> destinationDeck;

    public static void main(String[] args) {
        new HowManyPlayersFrame();
    }

    public static void setPlayerInfo(int numberOfPlayers) {
        Ticket2Score.numberOfPlayers = numberOfPlayers;
        Ticket2Score.players = new Player[numberOfPlayers];
        for (int player = numberOfPlayers; player > 0; player--) {
            new SetPlayerInfoFrame(player);
        }
    }

    public static void createPlayWindow() {
        

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));
        mainPanel.setBackground(new Color(160, 200, 240));
        JScrollPane scrollPanel = new JScrollPane(mainPanel);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(16);

        JFrame theWindow = new JFrame("Ticket2Score");
        theWindow.setSize(1300,800);
        //set properties
        theWindow.setResizable(true);
        theWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //create container..........................?
        Container c = theWindow.getContentPane();
        c.setLayout(new GridLayout(0, 1));
        c.add(scrollPanel);
        
        for (Player player : players) {
            JPanel panel = new PlayerPanel(player);
            mainPanel.add(panel);
        }
        theWindow.setExtendedState(Frame.MAXIMIZED_HORIZ); //todo
        
        
        theWindow.setVisible(true);
    }
    
    public static DefaultComboBoxModel getDeckModel(){
        DefaultComboBoxModel result;
        Object[] deck = destinationDeck.toArray();
        result = new DefaultComboBoxModel(deck);
        return result;
    }
}
