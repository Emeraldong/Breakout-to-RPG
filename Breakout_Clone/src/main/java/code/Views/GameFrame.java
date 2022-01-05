package code.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * This class is the main one, whereby almost all classes can trace their lineage to.
 * This is the first class to be called after the program starts.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    public static final String DEF_TITLE = "Breakout Clone     space = start/pause   ←/→ = move left/right   esc = menu";

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    private GameBoard gameBoard;

    private JFXPanelMainMenu jfxPanelMainMenu;
    private JFXPanelScoreView jfxPanelScoreView;
    private JFXPanelTutorialView jfxTutor;
    private NameEntry nameEntry;

    private final CardLayout cardLayout;
    private boolean gaming;


    public JFXPanelMainMenu getFxPanel() {
        return jfxPanelMainMenu;
    }

    public JFXPanelScoreView getJfxPanelScoreView() {
        return jfxPanelScoreView;
    }


    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public NameEntry getNameEntry(){
        return nameEntry;
    }

    /**
     * This constructs a GameFrame, and sets it a CardLayout for easy
     * transitions between JPanels and JFXPanels.
     */
    public GameFrame(){
        super();
        gaming = false;
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        addCards();
        initialize();
        this.addWindowFocusListener(this);
        cardLayout.show(this.getContentPane(),"fxMenu");

    }

    /**
     * This method initializes the GameFrame.
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * This method places the GameFrame in the middle of the screen.
     */
    public void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * This method sets gaming to true upon gaining focus.
     * @param windowEvent This is a window event.
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        // the first time the frame loses focus is because it has been disposed to install the GameBoard, so went it regains the focus it's ready to play. of course calling a method such as 'onLostFocus' is useful only if the GameBoard as been displayed at least once
        gaming = true;
    }

    /**
     * This method tells GameBoard to pause the game if focus is lost on the window.
     * @param windowEvent This is a window event.
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }

    /**
     * This is a method to add all the JPanels and JFXPanels to the GameFrame.
     */
    public void addCards(){
        gameBoard = new GameBoard(this, this);
        jfxPanelMainMenu = new JFXPanelMainMenu(this);
        jfxTutor = new JFXPanelTutorialView(this);
        jfxPanelScoreView = new JFXPanelScoreView(this);
        nameEntry = new NameEntry(this);
        this.add(gameBoard,"game"/*BorderLayout.CENTER*/);
        //this.add(scoreFile,"scores");
        this.add(jfxPanelMainMenu.getJfxPanel(),"fxMenu");
        this.add(jfxTutor.getJfxPanel(),"fxTutor");
        this.add(nameEntry.getJfxPanel(),"gameOver");
        this.add(jfxPanelScoreView.getJfxPanel(),"scoreList");
    }
}
