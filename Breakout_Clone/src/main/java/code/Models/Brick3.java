package code.Models;

import code.Models.Brick;
import code.Views.LoadPNG;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Brick3 extends Brick {

    private static final int SCORE = 100;
    private static final int CRACKED = 200;
    private static final String NAME = "Steel Brick";
    private static final String BRICKPNG = "/brick3.png";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private final Random rnd;
    private final Shape brickFace;
    private final Point point;

    private int score;
    private int storedScore;

    private final LoadPNG loader;
    public LoadPNG getLoader() {
        return loader;
    }

    public Brick3(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        this.point = point;
        loader = new LoadPNG(point, size);
        rnd = new Random();
        brickFace = getBrickFace();
        score = SCORE;
        storedScore = 0;
        loader.loadImageBrick(BRICKPNG);
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    @Override
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    @Override
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            score = SCORE + storedScore;//if it's broken
            super.impact();
        }
            storedScore += CRACKED;
    }

    //@Override
    public Image displayBrick(BufferedImage bi, Graphics2D g2d){
        return null;
    }

    public int giveScore(){
        return score;
    }

    public int getPointX(){
        return (int) point.getX();
    }

    public int getPointY(){
        return (int) point.getY();
    }
}
