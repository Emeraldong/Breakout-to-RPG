package code.Views;

import code.GameBoard;
import code.Models.Brick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadPNG {

    private static final int numOfBackgrounds = 4;

    private Image image;
    private Image playerImage;
    private Image backgrounds[];
    private BufferedImage bi;

    private Point point;
    private Dimension size;

    private Composite tmp;
    private Color tmpColor;

    private GameBoard mastersMaster;

    public LoadPNG(Point point, Dimension size){
        this.point = point;
        this.size = size;
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
            BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/paddle.png"));
            playerImage = bufferedImage.getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_SMOOTH);
            bi = new BufferedImage(playerImage.getWidth(null),playerImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadBackgrounds(){
        backgrounds = new Image[4];
        for (int i = 0;i < numOfBackgrounds; i++) {
            try {
                BufferedImage bufferedImage = ImageIO.read(getClass().getResource("/playfield0.png"));
                backgrounds[i] = bufferedImage.getScaledInstance(600,450, Image.SCALE_SMOOTH);
            }
            catch (IOException e) {
                e.printStackTrace();
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

    public Image displayBackground(BufferedImage bufferedImage, Graphics2D g2d, int selection){
        g2d = bufferedImage.createGraphics();
        g2d.drawImage(this.backgrounds[selection],0,0,null);

        return backgrounds[selection];
    }

}
