package code.Models;

import code.Views.LoadPNG;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * This represents an abstract Brick that is called from its children
 * to construct a Brick object.
 */
abstract public class Brick  {

    public Shape getBrickFace() {
        return brickFace;
    }
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private final Shape brickFace;

    private final int fullStrength;
    private int strength;
    private boolean broken;

    private final Point point;
    private final Dimension size;
    private LoadPNG loader;

    public final boolean isBroken(){
        return broken;
    }

    public Point getPoint(){
        return point;
    }

    public Dimension getSize(){
        return size;
    }

    public abstract LoadPNG getLoader();
    public abstract Shape getBrick();
    public abstract int getPointX();
    public abstract int getPointY();
    public abstract int giveScore();

    /**
     * This constructs a Brick object with the given parameters.
     * @param name This is the name of the Brick object.
     * @param pos This is the position of the Brick object.
     * @param size This is the dimension of the Brick object.
     * @param strength This is the strength of the Brick object,
     *                 or how many hits it can take before breaking.
     */
    public Brick(String name, Point pos, Dimension size, int strength){
        broken = false;
        this.brickFace = makeBrickFace(pos,size);
        this.fullStrength = this.strength = strength;
        this.point = pos;
        this.size = size;
    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * This method will break a Brick that is unbroken.
     * @param point This is where the Brick got hit.
     *
     * @return This returns broken, which if true means the Brick is broken.
     */
    public boolean setImpact(Point2D point){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * This method finds out where the Brick got hit by the ball.
     * @param b This is the ball that hit the Brick.
     * @return This returns which part of the Brick the ball hit.
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * This method returns the Brick object to full strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * This method decreases the strength of the Brick and breaks it if
     * its strength is low enough.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

}





