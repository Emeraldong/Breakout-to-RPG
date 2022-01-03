package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelTutorialView;
import javafx.event.Event;

public class TutorialViewControl {

    private JFXPanelTutorialView myScoreView;
    private GameFrame myGameFrame;

    public TutorialViewControl(GameFrame gameFrame, JFXPanelTutorialView jfxPanelScoreView){
        myGameFrame = gameFrame;
        myScoreView = jfxPanelScoreView;
    }

    public void backToMainMenu(Event event) {
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
        //gameBoard.requestFocus();
        }

        //if (source.equals(myOwner))
    }
