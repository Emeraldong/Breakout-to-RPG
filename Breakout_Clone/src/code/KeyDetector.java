package code;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetector implements KeyListener {

    private GameBoard gameBoard;

    public KeyDetector(GameBoard whichBoard) {
        gameBoard = whichBoard;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_LEFT){
            gameBoard.getWall().player.moveLeft();
        }
        if(code==KeyEvent.VK_RIGHT){
            gameBoard.getWall().player.moveRight();
        }
        if(code==KeyEvent.VK_SPACE){
            if(!gameBoard.isShowPauseMenu())
                if(gameBoard.getGameTimer().isRunning())
                    gameBoard.getGameTimer().stop();
                else
                    gameBoard.getGameTimer().start();
        }
        if(code==KeyEvent.VK_ESCAPE){
            gameBoard.setShowPauseMenu(!gameBoard.isShowPauseMenu());
            gameBoard.repaint();
            gameBoard.getGameTimer().stop();
        }
        if(code==KeyEvent.VK_BACK_SPACE){ //originally f1
            //if(e.isAltDown() && e.isShiftDown())
            gameBoard.getDebugConsole().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameBoard.getWall().player.stop();
    }
}
