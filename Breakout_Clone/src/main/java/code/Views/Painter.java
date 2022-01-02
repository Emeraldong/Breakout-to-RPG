package code.Views;

import code.Models.Ball;
import code.Models.Brick;
import code.GameBoard;
import code.Models.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;


public class Painter extends JPanel {

    private GameBoard myOwner;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;

    private int strLen;

    private String message;
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";

    private Font menuFont;
    private static final int TEXT_SIZE = 30;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;

    private BufferedImage wallOfBricks;

    private BufferedImage paddleImage;

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


    public Painter(GameBoard owner, String message){
        myOwner = owner;
        this.message = message;
        strLen = 0;
        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        wallOfBricks = new BufferedImage(600,450,BufferedImage.TYPE_INT_ARGB);
        paddleImage = new BufferedImage(600,450,BufferedImage.TYPE_INT_ARGB);

        this.setFocusable(true);
    }

    public void updater(GameBoard owner,String message){
        myOwner = owner;
        this.message=message;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);

        drawBall(myOwner.getWall().getBall(),g2d);

        for(Brick b : myOwner.getWall().getBricks()) {
            if (!b.isBroken()) {
                drawBrick(b,g2d);
                //b.getLoader().displayBrick(wallOfBricks, g2d);
            }
        }
        //g.drawImage(wallOfBricks,0,0,this);
        /*Image toDraw = drawBrick(g2d);
        if (toDraw != null) {
            g2d.drawImage(toDraw, 0, 0, null);
        } */

        drawPlayer(myOwner.getWall().getPlayer(),g2d);
        //g.drawImage(wallOfBricks,0,0,this);

        if(myOwner.isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    public void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    public void drawBrick(Brick brick, Graphics2D g2d) { //originally parameters are Brick brick, Graphics2D g2d
        BufferedImage aNewBrick = new BufferedImage((int)brick.getSize().getWidth(),(int)brick.getSize().getHeight(),BufferedImage.TYPE_INT_ARGB);
        g2d.drawImage(brick.getLoader().displayBrick(aNewBrick, g2d),brick.getPointX(),brick.getPointY(),this);
    }
        /*Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp); */

        /*g2d = bi.createGraphics();
        g2d.drawImage(image, brick.getPointX(), brick.getPointY(), null);
        g2d.dispose(); */
        //bi.getGraphics().drawImage(image,brick.getPointX(),brick.getPointY(),this);
        //System.out.println((int)brick.getPoint().getX());

        /*Image image;
        BufferedImage wallImage = new BufferedImage(this.getWidth(),450,BufferedImage.TYPE_INT_ARGB);
        //image = wallImage;
        for(Brick b: myOwner.getWall().getBricks()){
            if (!b.isBroken()) {
                image = b.displayBrick(g2d);

                Graphics2D gra2d;
                gra2d = wallImage.createGraphics();
                gra2d.drawImage(image, b.getPointX(), b.getPointY(), null);
                gra2d.dispose();
            }
        }
        return wallImage;
    }*/

    public void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    public void drawPlayer(Paddle p, Graphics2D g2d){
        BufferedImage thisPaddle = new BufferedImage((int)p.getSize().getWidth(),(int)p.getSize().getHeight(),BufferedImage.TYPE_INT_ARGB);
        /*Color tmp = g2d.getColor();

        Shape s = p.getPaddleFace();
        g2d.setColor(Paddle.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Paddle.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);*/
        g2d.drawImage(p.getLoader().displayPlayer(wallOfBricks, g2d),(int)(p.getBallPoint().getX() - (p.getSize().getWidth() / 2)),(int) p.getBallPoint().getY(),this);
    }

    public void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

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
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
}
