package code.Controllers;

import code.Views.GameFrame;

public class ScoreViewControl {

    GameFrame myGameFrame;

    public ScoreViewControl(GameFrame gameFrame){
        myGameFrame = gameFrame;
    }

    public void backToMainMenu(){
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
    }

}
