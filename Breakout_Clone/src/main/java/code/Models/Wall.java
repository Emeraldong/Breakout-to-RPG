package code.Models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Wall{

    public static final int LEVELS_COUNT = 4;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private Random rnd;
    private Rectangle area;

    private Brick[] bricks;

    private Ball ball;

    private Paddle player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    private int score;

    public int getScore(){return score;}

    public Brick[] getBricks() {
        return bricks;
    }

    public int getBrickCount(){
        return brickCount;
    }

    public Ball getBall() {
        return ball;
    }

    public int getBallCount(){
        return ballCount;
    }

    public Paddle getPlayer() {
        return player;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);
        GameInitialization gameInitializer = new GameInitialization(this);
        levels = gameInitializer.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        gameInitializer.makeBall(ballPos);
        player = new Paddle((Point) ballPos.clone(),150,10, drawArea);
        ballReset();
        area = drawArea;

        score = 0;

    }

    public void move(){
        player.move();
        ball.move();
    }

    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            brickCount--;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    public boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    scoreCalc(b);
                    return b.setImpact(ball.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    scoreCalc(b);
                    return b.setImpact(ball.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    scoreCalc(b);
                    return b.setImpact(ball.getRight(),Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    scoreCalc(b);
                    return b.setImpact(ball.getLeft(),Crack.LEFT);
            }
        }
        return false;
    }

    public boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
        //returns true if ball is detected to be outside the play area.
    }

    public void ballReset(){    //note to self: try reusing code from the other one
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(3) + 2; //originally 5 with offset -2
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3) - 2; //originally without the offset
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    public void resetBallCount(){
        ballCount = 3;
    }

    public void resetScore(){
        score = 0;
    }

    public Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new Brick2(point,size);
                break;
            case STEEL:
                out = new Brick3(point,size);
                break;
            case CEMENT:
                out = new Brick1(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

    public void scoreCalc(Brick brick){
        score += brick.giveScore();
    }

}
