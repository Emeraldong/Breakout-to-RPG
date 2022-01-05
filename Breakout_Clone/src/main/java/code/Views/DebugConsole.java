package code.Views;

import code.Models.Ball;
import code.Models.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This is the debugging console.
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private final JFrame owner;
    private final DebugPanel debugPanel;
    private final GameBoard gameBoard;
    private final Wall wall;

    /**
     * This constructs a DebugConsole.
     * @param owner This is the JFrame in which the DebugConsole is made and added.
     * @param wall  This is the Wall in that the DebugConsole will be able to affect.
     * @param gameBoard This is the GameBoard that called this constructor.
     */
    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall,gameBoard);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    /**
     * This method initializes the DebugConsole.
     */
    public void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     *This method sets the location of the DebugConsole.
     */
    public void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * This method will call for a repaint when the window is closing.
     * @param windowEvent This is an event where the window is closing.
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * This method will allow the player to set the speed of the Ball.
     * @param windowEvent
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.getBall();
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
