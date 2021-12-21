package code;

import code.Controllers.KeyDetector;
import code.Controllers.MouseDetector;
import code.Models.Wall;
import code.Views.DebugConsole;
import code.Views.GameFrame;
import code.Views.Painter;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel { //originally used KeyListener, MouseListener and MouseMotionListener also

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final int TEXT_SIZE = 30;

    private Timer gameTimer;

    private Wall wall;
    private KeyDetector keyDetector;
    private MouseDetector mouseDetector;
    private Painter painter;
    private DebugConsole debugConsole;
    private GameFrame myOwner;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

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

    public Timer getGameTimer() {
        return gameTimer;
    }

    public DebugConsole getDebugConsole() {
        return debugConsole;
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
        this.initialize();


        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            message = String.format("Bricks: %d Balls %d Score %d",wall.getBrickCount(),wall.getBallCount(),wall.getScore());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = "Game over. Your score is "+wall.getScore();
                    myOwner.getScoreFile().writeScore(wall.getScore());
                    wall.resetScore();
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    myOwner.getScoreFile().writeScore(wall.getScore());
                    wall.resetScore();
                }
            }

            painter.updater(this, message);
        });

    }



    public void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new BorderLayout());
        this.add(painter,BorderLayout.CENTER);
        this.addKeyListener(keyDetector);
        this.addMouseListener(mouseDetector);
        this.addMouseMotionListener(mouseDetector);
        revalidate();
    }

    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        painter.repaint();
    }



}
