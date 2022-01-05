package code.Views;

import code.Models.Ball;
import code.Models.Brick;
import code.Models.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;

/**
 * This class is the painter, another very important class as it draws the objects the
 * player sees.
 */
public class Painter extends JPanel {

    private GameBoard myOwner;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;

    private int strLen;
    private int width;
    private int height;

    private String message;
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Back to Main Menu";
    private static final String PAUSE = "Game Paused";

    private final Font menuFont;
    private final Font messageFont;
    private static final int TEXT_SIZE = 30; // originally 30

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;

    private final BufferedImage wallOfBricks;
    private BufferedImage playField;

    private final LoadPNG loader;

    private static final Color MENU_COLOR = new Color(0,255,0);

    public Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    public Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }

    public Rectangle getExitButtonRect() {
        return exitButtonRect;
    }

    public LoadPNG getLoader() {
        return loader;
    }

    /**
     * This constructs a Painter object and initializes the fonts to use.
     * @param owner This is the GameBoard that owns this Painter.
     * @param message This is a String to be displayed.
     */
    public Painter(GameBoard owner, String message){
        myOwner = owner;
        this.message = message;
        strLen = 0;
        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);// originally monospaced
        messageFont = new Font("SansSerif",Font.PLAIN,TEXT_SIZE/2);
        wallOfBricks = new BufferedImage(DEF_WIDTH,DEF_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        loader = new LoadPNG(null,null);
        loader.setGameBoard(myOwner);
        loader.initBackgrounds();


        this.setFocusable(true);
    }

    /**
     * This updates the painter.
     * @param owner This is the GameBoard that owns this Painter.
     * @param message This is a String to be displayed.
     */
    public void updater(GameBoard owner,String message){
        myOwner = owner;
        this.message=message;
        repaint();
    }

    /**
     * This sets width and height.
     */
    public void setWidthAndHeight(){
        width = myOwner.getWidth();
        height = myOwner.getHeight();
    }

    /**
     * This method draws everything.
     * @param g This is a graphics object used to draw.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        drawBackground(g2d);
        g2d.setColor(Color.RED);
        g2d.setFont(messageFont);
        g2d.drawString(message,225,this.getHeight()/2);//originally 250, 225

        drawBall(myOwner.getWall().getBall(),g2d);

        for(Brick b : myOwner.getWall().getBricks()) {
            if (!b.isBroken()) {
                drawBrick(b,g2d);
            }
        }

        drawPlayer(myOwner.getWall().getPlayer(),g2d);

        if(myOwner.isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * This method clears the screen.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * This method draws the background.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void drawBackground(Graphics2D g2d){
        g2d.drawImage(loader.displayBackground(wallOfBricks,g2d),0,0,this);
    }

    /**
     * This method draws a Brick.
     * @param brick This is the brick to draw.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void drawBrick(Brick brick, Graphics2D g2d) {
        BufferedImage aNewBrick = new BufferedImage((int)brick.getSize().getWidth(),(int)brick.getSize().getHeight(),BufferedImage.TYPE_INT_ARGB);
        g2d.drawImage(brick.getLoader().displayBrick(aNewBrick, g2d),brick.getPointX(),brick.getPointY(),this);
    }

    /**
     * This method draws a Ball
     * @param ball This is the ball to draw.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
        //g2d.drawImage(ball.getLoader().displayBall(wallOfBricks,g2d),(int)ball.getPosition().getX(),(int)ball.getPosition().getY(),this );
    }

    /**
     * This method draws a Paddle.
     * @param p This is a Paddle to draw.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void drawPlayer(Paddle p, Graphics2D g2d){
        g2d.drawImage(p.getLoader().displayPlayer(wallOfBricks, g2d),(int)(p.getBallPoint().getX() - (p.getSize().getWidth() / 2)),(int) p.getBallPoint().getY(),this);
    }

    /**
     * This draws the pause menu.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * This method darkens the game and brings to focus the pause menu.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * This method is called to draw the pause menu.
     * @param g2d This is a Graphics2D object used to draw.
     */
    public void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            //exitButtonRect = (Rectangle) continueButtonRect.clone();
            FontRenderContext frc = g2d.getFontRenderContext();
            exitButtonRect = menuFont.getStringBounds(EXIT,frc).getBounds();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
}
