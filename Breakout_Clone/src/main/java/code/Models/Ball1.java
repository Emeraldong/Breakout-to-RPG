package code.Models;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * This class is a child of Ball and therefore calls upon its parent
 * to construct a Ball object.
 */
public class Ball1 extends Ball {


    private static final String NAME = "Rubber Ball";
    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 102, 0);//originally 255 219 88
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    @Override
    public Color getInnerColor(){
        return DEF_INNER_COLOR;
    }

    @Override
    public Color getBorderColor(){
        return DEF_BORDER_COLOR;
    }

    /**
     * This constructs a Ball object with the defined parameters.
     * @param center This is where the center of the Ball object will be.
     */
    public Ball1(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }

    /**
     * This method creates an ellipse that represents the Ball object.
     * @param center This is where the center of the ball is, i.e. its position.
     * @param radiusA This is the width of the ball.
     * @param radiusB This is the height of the ball.
     * @return This method returns an ellipse that represents the Ball object
     * and can be drawn.
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
