package code.Models;

import java.awt.*;
import java.util.Random;

/**
 *  This class represents Wall of Bricks, and handles some of the starting
 *  and resetting of the game.
 */
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

    /**
     * This constructs a Wall, which in turn constructs a game level initializer
     * that constructs all the levels.
     * The Wall also constructs a Paddle, Ball, and an Impact handler.
     * @param drawArea This is the parameter that will be passed to the game level initializer
     *                 to tell it in which area it can draw.
     * @param brickCount This is the parameter that will be passed to the game level initializer
     *                   to tell it how many Bricks each level can have.
     * @param lineCount This is the parameter that will be passed to the game level initializer
     *                  to tell it how many lines of Bricks each level can have
     * @param brickDimensionRatio This is the parameter that will be passed to the game level initializer
     *                            to tell it the length-to-height ratio of every Brick.
     * @param ballPos This is the parameter that will be passed to the game level initializer
     *                to tell it where to make the Ball object.
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);
        InitializeGameLevels gameInitializer = new InitializeGameLevels(this);
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

    /**
     * This method lets the Paddle and Ball move.
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * This method resets the Ball and Paddle to the starting position
     * while giving the Ball a random speed.
     */
    public void ballReset(){
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

    /**
     * This method resets all the Bricks in a level and also resets the amount of Balls left.
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
        level = -1;
    }

    /**
     * This method checks if the player has any Balls left.
     * @return This returns true if the player has no Balls,
     * and false if they have at least one.
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * This method checks if all the Bricks are destroyed.
     * @return This returns false if there are still Bricks left
     * undestroyed completely, and true if all Bricks are destroyed.
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * This method increments the array of levels to go to the next one,
     * and also resets the Brick count.
     */
    public void nextLevel(){
        level++;
        bricks = levels[level];
        this.brickCount = bricks.length;
    }

    /**
     * This method checks if there are any levels remaining.
     * @return This returns true if there are any levels left,
     * and returns false otherwise.
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * This method resets the number of Balls the player has.
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * This method resets the score.
     */
    public void resetScore(){
        score = 0;
    }

    /**
     * This method passes variables to make Bricks of
     * the specified type and size at the given position.
     * @param point This is where to make the Brick.
     * @param size This is the dimension of the Brick.
     * @param type This is the type of Brick to make.
     * @return This method returns the newly made Brick.
     */
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

    /**
     * This method sums up the scores given out by every Brick when impacted
     * or destroyed.
     * @param brick This is the brick to get the score from.
     */
    public void scoreCalc(Brick brick){
        score += brick.giveScore();
    }

}
