package code.Controllers;

import code.Views.GameFrame;
import code.Views.JavaFXMainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;

public class JavaFXMainMenuController {

    private JavaFXMainMenu myOwner;

    @FXML
    Button startButton;

    @FXML
    Button infoButton;

    @FXML
    Button exitButton;

    @FXML
    Button scoreButton;

    public JavaFXMainMenuController(/*JavaFXMainMenu javaFXMainMenu*/){
        //myOwner = javaFXMainMenu;
    }

    /*public void startButtonClicked(ActionEvent e){
        new GameFrame().initialize();
    }*/
}
