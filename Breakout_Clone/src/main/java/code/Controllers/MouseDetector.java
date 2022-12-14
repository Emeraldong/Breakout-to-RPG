package code.Controllers;

import code.Views.GameBoard;
import javafx.application.Platform;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class detects certain mouse inputs during gameplay
 * and handles what to do in each case.
 */
public class MouseDetector implements MouseListener, MouseMotionListener {

    private final GameBoard gameBoard;

    /**
     * This constructs a mouse detector owned by a specific gameBoard.
     * @param whichBoard This specifies which gameBoard called and owns
     *                   the mouse detector.
     */
    public MouseDetector(GameBoard whichBoard){
        gameBoard = whichBoard;
    }

    /**
     * This method handles what to do when the mouse is clicked.
     * @param mouseEvent The event that called this method. Where the
     *                   mouse was clicked is extracted from this.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!gameBoard.isShowPauseMenu())
            return;
        if(gameBoard.getPainter().getContinueButtonRect().contains(p)){
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getPainter().getRestartButtonRect().contains(p)){
            gameBoard.setMessage("Restarting Game...");
            gameBoard.getWall().ballReset();
            gameBoard.getWall().wallReset();
            gameBoard.getWall().resetScore();
            gameBoard.setShowPauseMenu(false);
            gameBoard.repaint();
        }
        else if(gameBoard.getPainter().getExitButtonRect().contains(p)){
            gameBoard.getMyOwner().getCardLayout().show(gameBoard.getMyOwner().getContentPane(),"fxMenu");
            gameBoard.requestFocus();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    gameBoard.getMyOwner().getFxPanel().changeStartText();
                }
            });

            //System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * This method changes the cursor from its default state to the pointed
     * index finger when its detected to be over certain positions.
     * @param mouseEvent The event that called this method. Where the mouse was
     *                   moved will be extracted from this method.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(gameBoard.getPainter().getExitButtonRect() != null && gameBoard.isShowPauseMenu()) {
            if (gameBoard.getPainter().getExitButtonRect().contains(p) || gameBoard.getPainter().getContinueButtonRect().contains(p) || gameBoard.getPainter().getRestartButtonRect().contains(p))
                gameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }


}
