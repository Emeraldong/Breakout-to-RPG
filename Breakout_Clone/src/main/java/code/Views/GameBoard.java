package code.Views;

import code.Controllers.KeyDetector;
import code.Controllers.MouseDetector;
import code.Models.GameLoop;
import code.Models.Wall;

import javax.swing.*;
import java.awt.*;

/**
 * This class has many detectors attached to it and owns many other classes.
 */
public class GameBoard extends JPanel { //originally used KeyListener, MouseListener and MouseMotionListener also

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private final Wall wall;
    private final KeyDetector keyDetector;
    private final MouseDetector mouseDetector;
    private final Painter painter;
    private final DebugConsole debugConsole;

    private final GameFrame myOwner;
    private GameLoop gameLoop;

    private String message;

    private boolean showPauseMenu;

    public String getMessage() {
        return message;
    }
    public Wall getWall() {
        return wall;
    }

    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }

    public Painter getPainter() {
        return painter;
    }

    public GameLoop getGameTimer() {
        return gameLoop;
    }

    public DebugConsole getDebugConsole() {
        return debugConsole;
    }

    public GameFrame getMyOwner() {
        return myOwner;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This constructs a GameBoard in which a Wall is made and detectors added to itself.
     * @param owner This is the JFrame the GameBoard is in.
     * @param myGameFrame This is the GameFrame that constructs the GameBoard.
     */
    public GameBoard(JFrame owner, GameFrame myGameFrame){
        super();

        showPauseMenu = false;
        myOwner = myGameFrame;

        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));
        message = "Press SPACE to start";
        keyDetector = new KeyDetector(this);
        mouseDetector = new MouseDetector(this);
        painter = new Painter(this,message);
        debugConsole = new DebugConsole(owner,wall,this);
        this.initialize();
        gameLoop = new GameLoop(this,myOwner);


        wall.nextLevel();
        gameLoop.loop();

    }

    /**
     * This method initializes the GameBoard by adding a painter and setting the size
     * of the window among other things.
     */
    public void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new BorderLayout());
        this.add(painter,BorderLayout.CENTER);
        painter.setWidthAndHeight();
        this.addKeyListener(keyDetector);
        this.addMouseListener(mouseDetector);
        this.addMouseMotionListener(mouseDetector);
        revalidate();
    }

    /**
     * This method pauses the game when focus is lost on the window.
     */
    public void onLostFocus(){
        gameLoop.getGameTimer().stop();
        message = "Focus Lost. Press SPACE to resume";
        painter.updater(this,message);
    }



}
