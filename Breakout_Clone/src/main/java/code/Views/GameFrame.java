package code.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


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


    public GameFrame(){
        super();
        gaming = false;
        cardLayout = new CardLayout();
        this.setLayout(cardLayout); //originally this.setLayout(new BorderLayout());
        addCards();
        initialize();
        this.addWindowFocusListener(this);
        cardLayout.show(this.getContentPane(),"fxMenu");

    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    public void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        // the first time the frame loses focus is because it has been disposed to install the GameBoard, so went it regains the focus it's ready to play. of course calling a method such as 'onLostFocus' is useful only if the GameBoard as been displayed at least once
        gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }

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
