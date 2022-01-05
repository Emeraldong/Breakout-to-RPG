package code.Views;

import code.Controllers.KeyDetector;
import code.Controllers.MouseDetector;
import code.Models.GameLoop;
import code.Models.Wall;
import code.Views.DebugConsole;
import code.Views.GameFrame;
import code.Views.Painter;

import javax.swing.*;
import java.awt.*;

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


        //menuFont = new Font("SansSerif",Font.PLAIN,TEXT_SIZE);
        //initialize the first level
        wall.nextLevel();
        gameLoop.loop();

    }

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

    public void onLostFocus(){
        gameLoop.getGameTimer().stop();
        message = "Focus Lost. Press SPACE to resume";
        painter.updater(this,message);
    }



}
