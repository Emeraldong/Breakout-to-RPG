package code.Views;

import code.Models.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This class is paired with DebugConsole to allow easy debugging.
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;

    private final GameBoard gameBoard;

    private final JButton skipLevel;
    private final JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private final Wall wall;

    /**
     * This constructs a DebugPanel with buttons that can skip levels and reset the Balls left.
     * It can also let the player set the speed of the ball.
     * @param wall This is which wall the DebugPanel affects.
     * @param gameBoard This is the GameBoard in which the Debug Panel is located.
     */
    public DebugPanel(Wall wall, GameBoard gameBoard){

        this.wall = wall;
        this.gameBoard = gameBoard;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        skipLevel.addActionListener(e -> gameBoard.getPainter().getLoader().setLevel(wall.getLevel()));
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * This method initializes the background of the Debug Panel.
     */
    public void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * This method makes a JButton and attaches an actionListener to it.
     * @param title This is the title of the button.
     * @param e This is the ActionListener to attach to the button.
     * @return This returns a JButton.
     */
    public JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * This method constructs and returns a slider.
     * @param min This is the minimum value the slider can be set.
     * @param max This is the maximum value the slider can be set.
     * @param e This is the ChangeListener to attach to the button.
     * @return This returns the constructed slider.
     */
    public JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * This method sets the speed values of X and Y.
     * @param x This sets X speed of the ball to x.
     * @param y This sets Y speed of the ball to y.
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
