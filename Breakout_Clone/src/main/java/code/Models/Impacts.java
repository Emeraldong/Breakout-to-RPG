package code.Models;

import java.awt.geom.Point2D;

/**
 * This class contains methods that handle all the impacts in the game.
 */
public class Impacts {

    private final Wall myWall;
    private final Ball ball;

    /**
     * This constructor specifies which Wall called it, and also
     * gets the Ball used in the Wall.
     * @param wall
     */
    public Impacts(Wall wall){
        myWall = wall;
        ball = myWall.getBall();
    }

    /**
     * This method checks for impacts between the Ball and Paddle,
     * the Ball and the borders of the GameFrame,
     * and if there are any, handles them.
     */
    public void findImpacts(){
        if(myWall.getPlayer().impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            myWall.setBrickCount(myWall.getBrickCount()-1);
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < myWall.getArea().getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > myWall.getArea().getY() + myWall.getArea().getHeight()){
            myWall.setBallCount(myWall.getBallCount()-1);
            myWall.setBallLost(true);
        }
    }

    /**
     * This method checks for impacts between the Ball and any Brick.
     * @return This method returns a boolean which if true decreases
     * Brick count by 1 and does nothing otherwise.
     */
    public boolean impactWall(){
        for(Brick b : myWall.getBricks()){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getDown());
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getUp());

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getRight());
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getLeft());
            }
        }
        return false;
    }

    /**
     * This method checks for impacts between the left and right edges of the GameFrame and the Ball.
     * @return Returns true if ball is detected to be outside the play area,
     * else returns false.
     */
    public boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < myWall.getArea().getX()) ||(p.getX() > (myWall.getArea().getX() + myWall.getArea().getWidth())));
        //returns true if ball is detected to be outside the play area.
    }
}
