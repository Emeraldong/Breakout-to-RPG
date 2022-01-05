package code.Models;

import code.Models.Ball;
import code.Views.LoadPNG;

import java.awt.*;


public class Paddle {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

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

    public Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    public boolean impact(Ball b){
        return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.getDown()) ;
    }

    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }

    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop(){
        moveAmount = 0;
    }

    public Shape getPaddleFace(){
        return paddleFace;
    }

    public void moveTo(Point p){
        ballPoint.setLocation(p);
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }
}
