package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelTutorialView;
import javafx.event.Event;

public class TutorialViewControl {

    private final JFXPanelTutorialView myTutorialView;
    private final GameFrame myGameFrame;

    public TutorialViewControl(GameFrame gameFrame, JFXPanelTutorialView jfxPanelScoreView){
        myGameFrame = gameFrame;
        myTutorialView = jfxPanelScoreView;
    }

    public void backToMainMenu() {
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
        }
    }
