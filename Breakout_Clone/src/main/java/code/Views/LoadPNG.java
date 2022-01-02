package code.Views;

import code.Models.Brick;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadPNG {

    private Image image;
    private Image playerImage;
    private Image ballImage;
    private BufferedImage bi;

    private Point point;
    private Dimension size;

    private Composite tmp;
    private Color tmpColor;

    public LoadPNG(Point point, Dimension size){
        this.point = point;
        this.size = size;
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

    public Image clearBrick(Point p,Graphics2D g2d){
        tmp = g2d.getComposite();
        tmpColor = g2d.getColor();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR,0.01f));
        g2d.fillRect(point.x,point.y,(int)size.getWidth(),(int)size.getHeight());
        return image;
    }

    public void normalise(Graphics2D g2d){
        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

}
