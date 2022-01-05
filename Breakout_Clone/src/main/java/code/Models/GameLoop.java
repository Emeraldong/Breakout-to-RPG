package code.Models;

import code.Views.GameBoard;
import code.Views.GameFrame;

import javax.swing.*;

public class GameLoop {

    private GameFrame gameFrame;
    private GameBoard gameBoard;
    private Timer gameTimer;
    private Wall wall;
    private String message;

    public Timer getGameTimer() {
        return gameTimer;
    }

    public GameLoop(GameBoard gameBoard, GameFrame gameFrame){
        this.gameFrame = gameFrame;
        this.gameBoard = gameBoard;

        wall = gameBoard.getWall();
        message = gameBoard.getMessage();
    }

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
