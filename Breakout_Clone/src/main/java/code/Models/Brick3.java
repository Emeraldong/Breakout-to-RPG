package code.Models;

import code.Models.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Brick3 extends Brick {

    private static final int SCORE = 100;
    private static final int CRACKED = 200;
    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    private int score;
    private int storedScore;

    public Brick3(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = getBrickFace();
        score = SCORE;
        storedScore = 0;
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

    public int giveScore(){
        return score;
    }

}
