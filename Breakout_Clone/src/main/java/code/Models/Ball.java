package code.Models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * This represents an abstract Ball that is called from its children
 * to construct a Ball object.
 */
abstract public class Ball {

    private Shape ballFace;

    private final Point2D center;

    private final Point2D up;
    private final Point2D down;
    private final Point2D left;
    private final Point2D right;

    private final Color border;
    private final Color inner;

    private int speedX;
    private int speedY;


    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public int getSpeedX() {return speedX;}

    public int getSpeedY() {return speedY;}

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }

    public Point2D getRight() {
        return right;
    }

    public void setXSpeed(int s){
        speedX = s;
    }

    public void setYSpeed(int s){
        speedY = s;
    }
    public abstract Color getInnerColor();

    public abstract Color getBorderColor();

    /**
     *This constructs a ball at a location, with a certain radius and colour.
     * @param center This is the point where the center of the ball is.
     * @param radiusA This is the width of the ball.
     * @param radiusB This is the height of the ball.
     * @param inner This is the colour of the innards of the ball.
     * @param border This is the colour of the border of the ball.
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * This method will be implemented by a child of Ball to make a shape for the Ball object.
     * @param center This is where the center of the ball is, i.e. its position.
     * @param radiusA This is the width of the ball.
     * @param radiusB This is the height of the ball.
     * @return This method will return to its caller a Ball object made with
     * the aforementioned parameters.
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * This method moves the ball according to where its speed would take it.
     * It calculates where the center will be and sets the location of the ballFace there.
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * This method reverses the horizontal speed of the ball.
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * This method reverses the vertical speed of the ball.
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * This method moves the ball to the position specified by parameter p.
     * @param p This specifies to what coordinates the Ball will move to.
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * This method sets the borders of the Ball using the parameters given.
     * @param width This is used to calculate where the horizontal borders will be.
     * @param height This is used to calculate where the vertical borders will be.
     */
    public void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

}
