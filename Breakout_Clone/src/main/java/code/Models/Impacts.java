package code.Models;

import java.awt.geom.Point2D;

public class Impacts {

    private Wall myWall;
    private Ball ball;

    public Impacts(Wall wall){
        myWall = wall;
        ball = myWall.getBall();
    }

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

    public boolean impactWall(){
        for(Brick b : myWall.getBricks()){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getRight(),Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    myWall.scoreCalc(b);
                    return b.setImpact(ball.getLeft(),Crack.LEFT);
            }
        }
        return false;
    }

    public boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < myWall.getArea().getX()) ||(p.getX() > (myWall.getArea().getX() + myWall.getArea().getWidth())));
        //returns true if ball is detected to be outside the play area.
    }
}
