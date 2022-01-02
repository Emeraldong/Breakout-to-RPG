package code.Models;

import code.Models.Ball;
import code.Views.LoadPNG;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

abstract public class Brick  {

    public Shape getBrickFace() {
        return brickFace;
    }

    public static final int MIN_CRACK = 1;
    public static final int MAX_CRACK = 3;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private String name;



    private Shape brickFace;
    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;
    private boolean broken;

    private Point point;
    private Dimension size;
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

    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        this.name = name;
        this.brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
        this.point = pos;
        this.size = size;
    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();
    public abstract int getPointX();
    public abstract int getPointY();

    public Color getBorderColor(){
        return  border;
    }
    public Color getInnerColor(){
        return inner;
    }


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

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    abstract public int giveScore();

}





