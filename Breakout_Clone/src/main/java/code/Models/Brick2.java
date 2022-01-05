package code.Models;

import code.Views.LoadPNG;

import java.awt.*;

public class Brick2 extends Brick {

    private static final int SCORE = 100;
    private static final String NAME = "Clay Brick";
    private static final String BRICKPNG = "/Images/brick2.png";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CLAY_STRENGTH = 1;

    private final Point point;
    private final LoadPNG loader;

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
