package code.Models;

import code.Views.GameBoard;
import code.Views.GameFrame;

import javax.swing.*;

/**
 * This class represents the main gameplay loop of the program.
 */
public class GameLoop {

    private GameFrame gameFrame;
    private GameBoard gameBoard;
    private Timer gameTimer;
    private Wall wall;
    private String message;

    public Timer getGameTimer() {
        return gameTimer;
    }

    /**
     * This constructor specifies what gameBoard this GameLoop belongs to
     * and to which gameFrame the gameBoard is in.
     * @param gameBoard This is the GameBoard that calls this constructor.
     * @param gameFrame This is the GameFrame that the GameBoard resides in.
     */
    public GameLoop(GameBoard gameBoard, GameFrame gameFrame){
        this.gameFrame = gameFrame;
        this.gameBoard = gameBoard;

        wall = gameBoard.getWall();
        message = gameBoard.getMessage();
    }

    /**
     * This method contains a singular Timer which the entire game runs on.
     * Scores, Balls and Bricks left are updated as well.
     * When one of the game ending conditions are met, the Timer is stopped.
     */
    public void loop(){
        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.getImpacts().findImpacts();
            message = String.format("Bricks: %d Balls %d Score %d",wall.getBrickCount(),wall.getBallCount(),wall.getScore());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = "Game over. Your score is "+wall.getScore();
                    gameFrame.getNameEntry().compareScore(wall.getScore());
                    gameFrame.getNameEntry().setScore(wall.getScore());
                    //myOwner.getScoreFile().writeScore(String.valueOf(wall.getScore()));
                    wall.resetScore();
                    gameFrame.getCardLayout().show(gameFrame.getContentPane(),"gameOver");
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                    gameBoard.getPainter().getLoader().setLevel(wall.getLevel());
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                    gameFrame.getNameEntry().compareScore(wall.getScore());
                    gameFrame.getNameEntry().setScore(wall.getScore());
                    wall.resetScore();
                    gameFrame.getCardLayout().show(gameFrame.getContentPane(),"gameOver");
                }
            }

            gameBoard.getPainter().updater(gameBoard, message);
        });
    }

}
