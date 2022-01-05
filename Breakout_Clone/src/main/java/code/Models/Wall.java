package code.Models;

import java.awt.*;
import java.util.Random;

public class Wall{

    public static final int PADDLE_LENGTH = 75;
    public static final int PADDLE_HEIGHT = 10;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int INITIAL_BALLS = 3;
    private static final int INITIAL_LEVEL = -1;

    private final Random rnd;
    private final Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private final Paddle player;
    private final Brick[][] levels;
    private final Point startPoint;
    private final Impacts impacts;

    private int level;
    private int brickCount;
    private int ballCount;
    private int score;
    private boolean ballLost;

    public Impacts getImpacts() {
        return impacts;
    }

    public Rectangle getArea() {
        return area;
    }

    public int getLevel() {
        return level;
    }

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

    public boolean isBallLost(){
        return ballLost;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);
        InitializeGame gameInitializer = new InitializeGame(this);
        levels = gameInitializer.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = INITIAL_LEVEL;
        ballCount = INITIAL_BALLS;
        ballLost = false;
        rnd = new Random();
        gameInitializer.makeBall(ballPos);
        player = new Paddle((Point) ballPos.clone(),PADDLE_LENGTH,PADDLE_HEIGHT, drawArea);
        ballReset();
        area = drawArea;
        score = 0;
        impacts = new Impacts(this);
    }

    public void move(){
        player.move();
        ball.move();
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

        ball.setXSpeed(speedX);
        ball.setYSpeed(speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
        level = -1;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        level++;
        bricks = levels[level];
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
