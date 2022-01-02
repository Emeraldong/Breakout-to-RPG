package code.Views;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class JFXPanelMainMenu {

    private JFXPanel jfxPanel;
    private GameFrame owner;

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }


    public JFXPanelMainMenu(GameFrame gameFrame){
        jfxPanel = new JFXPanel();
        owner = gameFrame;
        Platform.runLater(()->{
            jfxPanel.setScene(createScene());
        });
    }

    private Scene createScene() {

        Button start= new Button("Start");
        Button exit= new Button("Quit");
        Button score = new Button("Scores");
        Button tutor = new Button("Tutorial");

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Welcome!");
                owner.getCardLayout().show(owner.getContentPane(),"game");
                owner.getGameBoard().requestFocus();
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        });
        VBox root = new VBox(start,tutor,score,exit);
        root.setSpacing(50);
        root.setAlignment(Pos.BASELINE_CENTER);
        BackgroundSize backgroundSize = new BackgroundSize(600,450,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/field2.png")),600,450,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        //root.setStyle("-fx-background: red;");
        root.setBackground(background);
        Label label = new Label("hello friend");
        Label other = new Label("hello WORLD!!!");
        root.getChildren().addAll(label, other);
        return new Scene(root);
    }

}
