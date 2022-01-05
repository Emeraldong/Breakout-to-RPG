package code.Models;

import code.Views.LoadPNG;

import java.awt.*;

/**
 * This class represents a Paddle object that is controlled by the player.
 * It constructs the paddle and lets it move.
 */
public class Paddle {

    public static final int DEF_MOVE_AMOUNT = 5;

    private final Rectangle paddleFace;
    private final Point ballPoint;
    private final Dimension size;

    private int moveAmount;
    private final int min;
    private final int max;

    private final LoadPNG loader;

    public Dimension getSize() {
        return size;
    }

    public Point getBallPoint() {
        return ballPoint;
    }

    public LoadPNG getLoader() {
        return loader;
    }

    /**
     * This constructs a Paddle object with the given parameters.
     * @param ballPoint This is the point where the Paddle will be built.
     * @param width This is how long horizontally the Paddle will be.
     * @param height This is how tall the Paddle will be.
     * @param container This is the area where the Paddle will be drawn.
     */
    public Paddle(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        paddleFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;
        size = new Dimension(width,height);
        loader = new LoadPNG(ballPoint,size);
        loader.loadImagePlayer();
    }

    /**
     * This method makes the invisible rectangle which is controlled by the player
     * and is responsible for the physics.
     * @param width This is how long horizontally to make the paddle.
     * @param height This is how tall to make the paddle.
     * @return This method returns the newly made rectangle.
     */
    public Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * This method checks if Ball b has hit the Paddle.
     * @param b This is the Ball that might have hit the Paddle.
     * @return Returns a boolean value; true if the Ball has indeed
     * hit the Paddle and false otherwise.
     */
    public boolean impact(Ball b){
        return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.getDown()) ;
    }

    /**
     * This method changes the position of the Paddle.
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }

    /**
     * This method changes the offset moveAmount such that the paddle will
     * end up in a position further left than it had previously been.
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }
    /**
     * This method changes the offset moveAmount such that the paddle will
     * end up in a position further right than it had previously been.
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }
    /**
     * This method sets the offset moveAmount such that the paddle will
     * stop changing position.
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * This method sets the postion of the paddle to Point p.
     * @param p This is the point at which the Paddle will be moved to.
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }
}
