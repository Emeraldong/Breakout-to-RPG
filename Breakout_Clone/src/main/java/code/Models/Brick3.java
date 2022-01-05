package code.Models;

 import code.Views.LoadPNG;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * This class represents a child of Brick, and thus calls
 * upon Brick to create a Brick object.
 */
public class Brick3 extends Brick {

    private static final int SCORE = 100;
    private static final int CRACKED = 200;
    private static final String NAME = "Steel Brick";
    private static final String BRICKPNG = "/Images/brick3.png";
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
    @Override
    public LoadPNG getLoader() {
        return loader;
    }
    @Override
    public int giveScore(){
        return score;
    }
    @Override
    public int getPointX(){
        return (int) point.getX();
    }
    @Override
    public int getPointY(){
        return (int) point.getY();
    }

    /**
     * This constructs a Brick object at a location and with a size specified by the parameters.
     * @param point This is where the Brick will be constructed.
     * @param size This is the dimension of the Brick object.
     */
    public Brick3(Point point, Dimension size){
        super(NAME,point,size,STEEL_STRENGTH);
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

    /**
     * This method will set an impact if it was not already broken.
     * @param point This is where the Brick got hit.
     * @param dir This is the direction the ball was travelling
     *            when it hit the Brick.
     * @return This returns false if the Brick was already broken.
     * Else, it returns true.
     */
    @Override
    public  boolean setImpact(Point2D point){
        if(super.isBroken())
            return false;
        impact();
        return super.isBroken();
    }

    /**
     * This method causes the Brick to be destroyed if the random number generated
     * is lower than STEEL_PROBABILITY.
     * If it isn't destroyed, the score given out when the Brick is destroyed is increased.
     */
    @Override
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            score = SCORE + storedScore;//if it's broken
            super.impact();
        }
            storedScore += CRACKED;
    }

}
