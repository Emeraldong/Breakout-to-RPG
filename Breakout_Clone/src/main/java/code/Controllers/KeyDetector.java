package code.Controllers;

import code.Views.GameBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**This controller detects certain keyboard inputs during gameplay
 * and handles what to do in each case.
 */
public class KeyDetector implements KeyListener {

    private final GameBoard gameBoard;

    /**
     *Creates a key detector owned by specified gameBoard.
     * @param whichBoard The owner of the key detector.
     */
    public KeyDetector(GameBoard whichBoard) {
        gameBoard = whichBoard;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * This method finds out what key was pressed and acts based on that.
     * @param e The event that called this method. The key pressed is
     *          extracted from this.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            gameBoard.getWall().getPlayer().moveLeft();
        }
        if(code==KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            gameBoard.getWall().getPlayer().moveRight();
        }
        if(code==KeyEvent.VK_SPACE){
            if(!gameBoard.isShowPauseMenu())
                if(gameBoard.getGameTimer().getGameTimer().isRunning())
                    gameBoard.getGameTimer().getGameTimer().stop();
                else
                    gameBoard.getGameTimer().getGameTimer().start();
        }
        if(code==KeyEvent.VK_ESCAPE){
            gameBoard.setShowPauseMenu(!gameBoard.isShowPauseMenu());
            gameBoard.repaint();
            gameBoard.getGameTimer().getGameTimer().stop();
        }
        if(code==KeyEvent.VK_BACK_SPACE){ //originally f1
            gameBoard.getDebugConsole().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameBoard.getWall().getPlayer().stop();
    }
}
