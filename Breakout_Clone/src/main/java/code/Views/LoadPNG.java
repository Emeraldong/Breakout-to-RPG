package code.Views;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This is the class that loads all the image files.
 */
public class LoadPNG {

    private static final int NUM_OF_BACKGROUNDS = 5;
    private static final int LAST_INDEX = 4;
    private static final int NUM_OF_LEVELS = 8;
    private static final int DEF_HEIGHT = 450;
    private static final int DEF_WIDTH = 600;

    private Image image;
    private Image playerImage;
    private Image ballImage;
    private Image[] backgrounds;
    private BufferedImage bi;

    private final Point point;
    private final Dimension size;

    private GameBoard mastersMaster;

    private int level;

    public void setLevel(int level){
        this.level = level;
    }

    /**
     * This constructs a LoadPNG object that can load images.
     * @param point This is the point on which to draw object.
     * @param size  This is the size of the object
     */
    public LoadPNG(Point point, Dimension size){
        this.point = point;
        this.size = size;
        level = 0;
    }

    /**
     * This method sets the Gameboard of this class.
     * @param gameBoard This is the GameBoard.
     */
    public void setGameBoard(GameBoard gameBoard){
        mastersMaster = gameBoard;
    }

    /**
     * This method loads images of a Brick.
     * @param path this is the path to the image.
     */
    public void loadImageBrick(String path){
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource(path));
            image = bufferedImage.getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_SMOOTH);
            bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method loads images of a Paddle.
     */
    public void loadImagePlayer() {
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/Images/paddle.png"));
            playerImage = bufferedImage.getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_SMOOTH);
            bi = new BufferedImage(playerImage.getWidth(null),playerImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *This method loads images of the Backgrounds.
     */
    public void initBackgrounds(){
        backgrounds = new Image[NUM_OF_LEVELS];
        for (int i = 0;i < NUM_OF_BACKGROUNDS; i++) {
            if(i==0) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/Images/playfield0.png"));
                    backgrounds[i] = bufferedImage.getScaledInstance(DEF_WIDTH, DEF_HEIGHT, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(i==1){
                try {
                    BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/Images/playfield1.png"));
                    backgrounds[i] = bufferedImage.getScaledInstance(DEF_WIDTH, DEF_HEIGHT, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(i==2){
                try {
                    BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/Images/playfield2.png"));
                    backgrounds[i] = bufferedImage.getScaledInstance(DEF_WIDTH, DEF_HEIGHT, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(i==3){
                try {
                    BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/Images/playfield3.png"));
                    backgrounds[i] = bufferedImage.getScaledInstance(DEF_WIDTH, DEF_HEIGHT, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(i==4){
                try {
                    BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/Images/playfield4.png"));
                    backgrounds[i] = bufferedImage.getScaledInstance(DEF_WIDTH, DEF_HEIGHT, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method displays the images of the Bricks.
     * @param bufferedImage This is the buffered image to draw on.
     * @param g2d This is the Graphics2D object to use to draw.
     * @return Returns image of Brick.
     */
    public Image displayBrick(BufferedImage bufferedImage,Graphics2D g2d){

        //BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(this.image, (int)point.getX(), (int)point.getY(), null);
        //g2d.dispose();

        return image;
    }
    /**
     * This method displays the images of the Paddle.
     * @param bufferedImage This is the buffered image to draw on.
     * @param g2d This is the Graphics2D object to use to draw.
     * @return Returns image of Paddle.
     */
    public Image displayPlayer(BufferedImage bufferedImage, Graphics2D g2d){
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(this.playerImage,(int)point.getX(),(int)point.getY(),null);

        return playerImage;
    }
    /**
     * This method displays the images of the Backgrounds.
     * @param bufferedImage This is the buffered image to draw on.
     * @param g2d This is the Graphics2D object to use to draw.
     * @return Returns image of Background.
     */
    public Image displayBackground(BufferedImage bufferedImage, Graphics2D g2d){
        g2d = bufferedImage.createGraphics();
        if(level >= NUM_OF_BACKGROUNDS){
            g2d.drawImage(this.backgrounds[LAST_INDEX],0,0,null);
            return backgrounds[LAST_INDEX];
        }
        else {
            g2d.drawImage(this.backgrounds[level], 0, 0, null);
        }

        return backgrounds[level];
    }

}
