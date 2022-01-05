package code.Views;

import code.Controllers.NameEntryControl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * This class is used to let players input their names after finishing a game.
 */
public class NameEntry {

    private static final String question = "WHAT IS YOUR NAME?";
    private static final String announcer = "YOUR SCORE IS ";

    private final JFXPanel jfxPanel;
    private final GameFrame owner;

    private int score;
    private String displayText;

    private TextField textField;
    private Text scoreAnnouncer;

    private final NameEntryControl controller;

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }

    public TextField getTextField() {
        return textField;
    }

    /**
     * This constructs a NameEntry object.
     * @param gameFrame This is the GameFrame that owns this JFXPanel.
     */
    public NameEntry(GameFrame gameFrame){
        owner = gameFrame;
        jfxPanel = new JFXPanel();

        Platform.runLater(()->{
            jfxPanel.setScene(createScene());
        });
        controller = new NameEntryControl(owner,this);
    }

    /**
     * This method creates a Scene on which textfields are added.
     * @return
     */
    public Scene createScene(){
        VBox root = new VBox();
        initializeBackground(root);

        scoreAnnouncer = new Text(owner.getWidth()/2, owner.getHeight()/4, displayText);
        Text questionner = new Text(owner.getWidth()/2, owner.getHeight()/3, question);

        textField = new TextField();
        textField.setPromptText("Enter name here");
        textField.setFocusTraversable(false);

        Button submit = new Button("Submit");

        submit.setOnAction(e-> controller.submitName(score));

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(scoreAnnouncer, questionner, textField,submit);

        return new Scene(root);
    }

    /**
     * This method initializes the background.
     * @param root this is the VBox on which to set the background in.
     */
    private void initializeBackground(VBox root){
        BackgroundSize backgroundSize = new BackgroundSize(1,1,true,true,false,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/nameentry.png")),600,450,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        root.setBackground(background);

    }

    /**
     * This method announces the score of the player.
     * @param score This is the score of the player.
     */
    public void setScore(int score){
        this.score = score;
        displayText = announcer + "" + score;
        scoreAnnouncer.setText(displayText);
    }

    public void compareScore(int score){

    }

}
