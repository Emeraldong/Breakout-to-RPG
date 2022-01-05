package code.Models;

import code.Views.LoadPNG;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This class represents a child of Brick, and thus calls
 * upon Brick to create a Brick object.
 */
public class Brick1 extends Brick {

    private static final int SCORE_CRACKED = 50;
    private static final int SCORE = 200;
    private static final String NAME = "Cement Brick";
    private static final String BRICKPNG = "/Images/brick1.png";
    private static final String CRACKEDBRICKPNG = "/Images/brick1_cracked.png";
    private static final int CEMENT_STRENGTH = 2;

    private Shape brickFace;
    private final Point point;

    private int score;

    private final LoadPNG loader;

    @Override
    public Shape getBrick() {
        return brickFace;
    }
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
    public Brick1(Point point, Dimension size){
        super(NAME,point,size,CEMENT_STRENGTH);
        this.point = point;
        loader = new LoadPNG(point, size);
        brickFace = super.getBrickFace();
        score = SCORE_CRACKED;
        loader.loadImageBrick(BRICKPNG);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * This method will break a Brick that is already cracked.
     * Otherwise, it will crack the Brick.
     * @param point This is where the Brick got hit.
     * @param dir This is the direction the ball was travelling
     *            when it hit the Brick.
     * @return Returns false if the Brick was already broken or if
     * the Brick was at full strength beforehand. Else, returns true.
     */
    @Override
    public boolean setImpact(Point2D point) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            loader.loadImageBrick(CRACKEDBRICKPNG);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * This method updates the score given by the brick when hit again.
     */
    private void updateBrick(){
        if(!super.isBroken()){
            score = SCORE;
        }
    }

    /**
     * This method "resets" the Brick by putting its strength back to full.
     */
    public void repair(){
        super.repair();
        loader.loadImageBrick(BRICKPNG);
        brickFace = getBrickFace();
    }
}
