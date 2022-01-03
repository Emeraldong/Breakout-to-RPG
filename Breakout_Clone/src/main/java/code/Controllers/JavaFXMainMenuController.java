package code.Controllers;

import code.Views.GameFrame;
import code.Views.JFXPanelMainMenu;
import code.Views.JavaFXMainMenu;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.event.Event;

public class JavaFXMainMenuController {

    private JFXPanelMainMenu myOwner;

    public JavaFXMainMenuController(GameFrame owner, JFXPanelMainMenu jfxPanelMainMenu) {
        myOwner = jfxPanelMainMenu;
        //myOwner = javaFXMainMenu;
        /*startHandler = new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                label.setText("Accepted");
                actionEvent.consume();
            }
        };*/
    }

    public void handle(Event event) {
        String id = ((Button) event.getSource()).getId();
        switch (id){
            case "start":
                System.out.println("Welcome!");
                myOwner.getOwner().getCardLayout().show(myOwner.getOwner().getContentPane(),"game");
                myOwner.getOwner().getGameBoard().requestFocus();
                break;
            case "tutor":
            case "score":
                System.out.println("not implemented yet, soon!");
                break;
            case "exit":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }

        //if (source.equals(myOwner))
    }

    /*public void startButtonClicked(ActionEvent e){
        new GameFrame().initialize();
    }*/

    /*public void startHandler(){
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                label.setText("Accepted");
                event.consume();
            }
        };
    }*/

}
