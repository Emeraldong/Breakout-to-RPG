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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class JFXPanelMainMenu {

    private JFXPanel jfxPanel;
    private GameFrame owner;
    private Button start;
    private Button exit;
    private Button score;
    private Button tutor;

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

        start= new Button("Start");
        exit= new Button("Quit");
        score = new Button("Scores");
        tutor = new Button("Tutorial");

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

        ImageView titleResized = loadImages();
        VBox root = new VBox(titleResized,start,tutor,score,exit);
        Background background = initializeBackground(root);
        //root.setStyle("-fx-background: red;");
        root.setBackground(background);
        Label label = new Label("hello friend");
        Label other = new Label("hello WORLD!!!");
        root.getChildren().addAll(label, other);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    private ImageView loadImages(){
        Image title = new Image(String.valueOf(getClass().getResource("/mainMenuText.png")));
        ImageView titleResized = new ImageView();
        titleResized.setImage(title);
        titleResized.setFitWidth(jfxPanel.getWidth()/2);
        titleResized.setFitHeight(jfxPanel.getHeight()/4);
        //titleResized.setPreserveRatio(true);
        titleResized.setSmooth(true);
        return titleResized;
    }

    private Background initializeBackground(VBox root){
        root.setSpacing(50);
        root.setAlignment(Pos.BASELINE_CENTER);
        BackgroundSize backgroundSize = new BackgroundSize(600,450,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/field2.png")),600,450,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        return background;
    }

    public void changeStartText(){
        start.setText("Continue");
    }

}
