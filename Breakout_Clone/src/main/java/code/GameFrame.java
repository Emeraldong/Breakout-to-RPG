package code;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import code.Views.*;


public class GameFrame extends JFrame implements WindowFocusListener {

    public static final String DEF_TITLE = "Breakout Clone     space = start/pause   ←/→ = move left/right   esc = menu";
    private GameBoard gameBoard;
    private MainMenuScreen menuScreen;
    private CardLayout cardLayout;
    private boolean gaming;

    public GameFrame(){
        super();
        gaming = false;
        cardLayout = new CardLayout();
        this.setLayout(cardLayout); //originally this.setLayout(new BorderLayout());
        menuScreen = new MainMenuScreen();
        gameBoard = new GameBoard(this);
        this.add(menuScreen,"menu");
        this.add(gameBoard,"game"/*BorderLayout.CENTER*/);
        initialize();
        cardLayout.show(this.getContentPane(),"menu");
        this.addWindowFocusListener(this);
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
}
