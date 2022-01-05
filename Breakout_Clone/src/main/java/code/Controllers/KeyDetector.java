package code.Controllers;

import code.GameBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetector implements KeyListener {

    private final GameBoard gameBoard;

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
            gameBoard.getWall().getPlayer().moveLeft();
        }
        if(code==KeyEvent.VK_RIGHT){
            gameBoard.getWall().getPlayer().moveRight();
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
        gameBoard.getWall().getPlayer().stop();
    }
}
