package code.Controllers;

import code.Views.GameFrame;

/**
 * This class represents a controller for a ScoreView panel.
 */
public class ScoreViewControl {

    GameFrame myGameFrame;

    /**
     * This constructs a controller for a ScoreView panel.
     * @param gameFrame This is the GameFrame in which the ScoreView panel is located in.
     */
    public ScoreViewControl(GameFrame gameFrame){
        myGameFrame = gameFrame;
    }

    /**
     * This method switches panels from this one to the main menu.
     */
    public void backToMainMenu(){
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
    }

}
