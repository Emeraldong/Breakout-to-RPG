package code.Models;

import code.Views.LoadPNG;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;


public class Brick1 extends Brick {

    private static final int SCORE_CRACKED = 50;
    private static final int SCORE = 200;
    private static final String NAME = "Cement Brick";
    private static final String BRICKPNG = "/brick1.png";
    private static final String CRACKEDBRICKPNG = "/brick1_cracked.png";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private final Crack crack;
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

    public Brick1(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        this.point = point;
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS,this);
        loader = new LoadPNG(point, size);
        brickFace = super.getBrickFace();
        score = SCORE_CRACKED;
        loader.loadImageBrick(BRICKPNG);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            loader.loadImageBrick(CRACKEDBRICKPNG);
            updateBrick();
            return false;
        }
        return true;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(getBrickFace(),false);
            brickFace = gp;
            score = SCORE;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = getBrickFace();
    }

    public int getPointX(){
        return (int) point.getX();
    }

    public int getPointY(){
        return (int) point.getY();
    }
}
