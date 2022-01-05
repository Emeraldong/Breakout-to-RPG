package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelTutorialView;

/**
 * This represents a controller for the TutorialView panel.
 */
public class TutorialViewControl {

    private final GameFrame myGameFrame;

    /**
     * This constructs a controller for a TutorialView panel.
     * @param gameFrame This is the GameFrame in which the TutorialView panel is located in.
     */
    public TutorialViewControl(GameFrame gameFrame){
        myGameFrame = gameFrame;
    }

    /**
     * This method switches panels from this one to the main menu.
     */
    public void backToMainMenu() {
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
        }
    }
