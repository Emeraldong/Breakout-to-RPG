package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelScoreView;

public class ScoreViewControl {

    GameFrame myGameFrame;

    public ScoreViewControl(GameFrame gameFrame){
        myGameFrame = gameFrame;
    }

    public void backToMainMenu(){
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
    }

}
