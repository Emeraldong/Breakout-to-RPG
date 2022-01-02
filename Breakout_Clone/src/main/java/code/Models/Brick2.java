package code.Models;

import code.Models.Brick;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import code.Views.LoadPNG;

public class Brick2 extends Brick {

    private static final int SCORE = 100;
    private static final String NAME = "Clay Brick";
    private static final String BRICKPNG = "/brick2.png";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CLAY_STRENGTH = 1;

    private Image image;
    private Dimension size;
    private BufferedImage bi;
    private Point point;
    private LoadPNG loader;

    @Override
    public LoadPNG getLoader() {
        return loader;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    public Brick2(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
        loader = new LoadPNG(point, size);
        this.size = size;
        this.point = point;
        loader.loadImageBrick(BRICKPNG);
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    //@Override
    public Shape getBrick() {
        return getBrickFace();
    }

    public int giveScore(){
        return SCORE;
    }

    public int getPointX(){
        return (int) point.getX();
    }

    public int getPointY(){
        return (int) point.getY();
    }

}
