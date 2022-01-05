package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelMainMenu;
import javafx.event.Event;
import javafx.scene.control.Button;

public class JavaFXMainMenuController {

    private final JFXPanelMainMenu myOwner;
    private final GameFrame myGameFrame;

    public JavaFXMainMenuController(GameFrame owner, JFXPanelMainMenu jfxPanelMainMenu) {
        myOwner = jfxPanelMainMenu;
        myGameFrame = owner;
    }

    public void handle(Event event) {
        String id = ((Button) event.getSource()).getId();
        switch (id){
            case "start":
                System.out.println("Welcome!");
                myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"game");
                myGameFrame.getGameBoard().requestFocus();
                break;
            case "tutor":
                System.out.println("To the tutorial!");
                myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxTutor");
                myGameFrame.getGameBoard().requestFocus();
                break;
            case "score":
                System.out.println("Look at these scores!");
                myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"scoreList");
                myGameFrame.getGameBoard().requestFocus();
                break;
            case "quit":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }

    }

}
