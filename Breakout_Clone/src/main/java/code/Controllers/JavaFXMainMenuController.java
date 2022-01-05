package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelMainMenu;
import javafx.event.Event;
import javafx.scene.control.Button;

/**This controller detects input from the JFXPanelMainMenu buttons and switches
 * panels to show them corresponding to which button was pressed.
 */
public class JavaFXMainMenuController {

    private final JFXPanelMainMenu myOwner;
    private final GameFrame myGameFrame;

    /**Creates a controller for the JavaFX main menu.
     *
     * @param gameFrame The gameFrame(JFrame) in which the JFXPanel main menu is in.
     * @param jfxPanelMainMenu  The owner of this controller.
     */
    public JavaFXMainMenuController(GameFrame gameFrame, JFXPanelMainMenu jfxPanelMainMenu) {
        myOwner = jfxPanelMainMenu;
        myGameFrame = gameFrame;
    }

    /**This method handles what needs to be done when
     * one of the buttons in the main menu is pressed.
     * @param event The buttons in the main menu send out an event,
     *              which is used to find out which button sent said event.
     */
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
