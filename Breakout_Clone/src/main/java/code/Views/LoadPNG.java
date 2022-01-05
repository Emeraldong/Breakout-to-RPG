package code.Views;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public LoadPNG(Point point, Dimension size){
        this.point = point;
        this.size = size;
        level = 0;
    }

    public void setGameBoard(GameBoard gameBoard){
        mastersMaster = gameBoard;
    }

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

    public Image displayBrick(BufferedImage bufferedImage,Graphics2D g2d){

        //BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(this.image, (int)point.getX(), (int)point.getY(), null);
        //g2d.dispose();

        return image;
    }

    public Image displayPlayer(BufferedImage bufferedImage, Graphics2D g2d){
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(this.playerImage,(int)point.getX(),(int)point.getY(),null);

        return playerImage;
    }

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
