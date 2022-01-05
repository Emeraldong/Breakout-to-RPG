package code.Models;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This class contains methods that initialize the game levels.
 */
public class InitializeGameLevels {

    private final Wall wallPrime;

    private static final int LEVELS_COUNT = 8;
    private static final int DOUBLE = 2;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    /**
     * This constructs a game level initializer owned by a Wall.
     * @param wall This is the Wall that owns the initializer.
     */
    public InitializeGameLevels(Wall wall){
        wallPrime = wall;
    }

    /**
     * This method makes levels that only have a single type of Brick.
     * @param drawArea This is the space that the Bricks can be drawn.
     * @param brickCnt This specifies how many Bricks to draw.
     * @param lineCnt This specifies how many lines of Bricks to draw.
     * @param brickSizeRatio This specifies the length-to-height ratio of the Bricks.
     * @param type This is the type of Brick to make.
     * @return This returns an array of Bricks that can be drawn by a painter and used
     * to play.
     */
    public Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        //Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            Point p = new Point();
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = wallPrime.makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            Point p = new Point();
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new Brick2(p,brickSize);
        }
        return tmp;

    }

    /**
     * This method makes levels that have two types of Bricks.
     * @param drawArea This is the space that the Bricks can be drawn.
     * @param brickCnt This specifies how many Bricks to draw.
     * @param lineCnt This specifies how many lines of Bricks to draw.
     * @param brickSizeRatio This specifies the length-to-height ratio of the Bricks.
     * @param typeA This is one of the types of Brick to make.
     * @param typeB This is another one of the types of Brick to make.
     * @returnThis returns an array of Bricks that can be drawn by a painter and used
     * to play.
     */
    public Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        // if brickCount is not divisible by line count, brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        //Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            Point p = new Point();
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  wallPrime.makeBrick(p,brickSize,typeA) : wallPrime.makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            Point p = new Point();
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = wallPrime.makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * This method makes a ball and places it in the Wall.
     * @param ballPos This is where the ball will be placed.
     */
    public void makeBall(Point2D ballPos){
        wallPrime.setBall(new Ball1(ballPos));
    }

    /**
     * This method hands out all the parameters for the other methods in this class
     * to make actual levels with Brick objects.
     * @param drawArea This is the space that the Bricks can be drawn.
     * @param brickCount This specifies how many Bricks to draw.
     * @param lineCount This specifies how many lines of Bricks to draw.
     * @param brickDimensionRatio This specifies the length-to-height ratio of the Bricks.
     * @return This returns an array of levels. Each level is an array of Bricks.
     */
    public Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeSingleTypeLevel(drawArea,brickCount*DOUBLE,lineCount*DOUBLE,brickDimensionRatio,CLAY);
        tmp[5] = makeChessboardLevel(drawArea,brickCount*DOUBLE,lineCount*DOUBLE,brickDimensionRatio,CLAY,CEMENT);
        tmp[6] = makeChessboardLevel(drawArea,brickCount*DOUBLE,lineCount*DOUBLE,brickDimensionRatio,CLAY,STEEL);
        tmp[7] = makeChessboardLevel(drawArea,brickCount*DOUBLE,lineCount*DOUBLE,brickDimensionRatio,STEEL,CEMENT);
        return tmp;
    }
}
