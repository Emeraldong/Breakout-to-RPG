package code.Views;

import code.Controllers.ScoreViewControl;
import code.Models.ScoreFile;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This is a class that shows the high scores.
 */
public class JFXPanelScoreView {

    private final GameFrame owner;

    private final JFXPanel jfxPanel;

    private final ScoreFile scoreList;

    private final ScoreViewControl controller;

    private VBox root;

    public ScoreFile getScoreList() {
        return scoreList;
    }

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }

    /**
     * This constructs the JFXPanel that shows the scores.
     * @param gameFrame This is the owner of this JFXPanel.
     */
    public JFXPanelScoreView(GameFrame gameFrame){
        owner = gameFrame;
        jfxPanel = new JFXPanel();
        scoreList = new ScoreFile();
        Platform.runLater(()->{
            jfxPanel.setScene(createScene());
        });
        controller = new ScoreViewControl(owner);
    }

    /**
     * This creates the scene on which the scores are displayed.
     * @return This returns the constructed Scene.
     */
    private Scene createScene(){
        root = new VBox();
        initializeBackground(root);
        drawScores();

        root.setOnMouseClicked(e-> controller.backToMainMenu());

        return new Scene(root);
    }
    /**
     * This method initializes the background.
     * @param root this is the vbox to add the background to.
     */
    private void initializeBackground(VBox root){
        root.setSpacing(50);
        root.setAlignment(Pos.BOTTOM_CENTER);

        BackgroundSize backgroundSize = new BackgroundSize(1,1,true,true,false,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/scores.png")),600,450,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        root.setBackground(background);

    }

    /**
     * This is the method that draws the scores on the screen.
     */
    public void drawScores(){
        String[] highScoreStrings = scoreList.getHighestScoreStrings();
        Text top1 = new Text("1. "+highScoreStrings[0]);
        styleText(top1);

        Text top2 = new Text( "2. " + highScoreStrings[1]);
        styleText(top2);

        Text top3 = new Text( "3. " + highScoreStrings[2]);
        styleText(top3);

        Text top4 = new Text("4. " + highScoreStrings[3]);
        styleText(top4);

        Text top5 = new Text( "5. " + highScoreStrings[4]);
        styleText(top5);

        root.getChildren().addAll(top1,top2,top3,top4,top5);
    }

    /**
     * This is the method that styles text.
     * @param text This is the text to style.
     */
    private void styleText(Text text){
        Font font = new Font("Serif", 25);
        text.setFont(font);
        text.setFill(Color.WHITE);

        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(8);
        shadow.setOffsetY(8);
        shadow.setColor(Color.rgb(20, 70, 70, 0.8));
        text.setEffect(shadow);

        Reflection reflection = new Reflection();
        reflection.setFraction(7);
        reflection.setTopOffset(5);
        text.setEffect(reflection);

    }

    /**
     * This resets the scores so that new ones can be displayed.
     */
    public void resetScreen(){
        root.getChildren().clear();
    }

}
