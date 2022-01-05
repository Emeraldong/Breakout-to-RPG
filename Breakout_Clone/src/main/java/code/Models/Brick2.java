package code.Models;

import code.Views.LoadPNG;

import java.awt.*;
/**
 * This class represents a child of Brick, and thus calls
 * upon Brick to create a Brick object.
 */
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
    @Override
    public Shape getBrick() {
        return getBrickFace();
    }
    @Override
    public int giveScore(){
        return SCORE;
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
    public Brick2(Point point, Dimension size){
        super(NAME,point,size,CLAY_STRENGTH);
        loader = new LoadPNG(point, size);
        this.point = point;
        loader.loadImageBrick(BRICKPNG);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }


}
