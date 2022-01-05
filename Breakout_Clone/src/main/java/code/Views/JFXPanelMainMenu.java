package code.Views;

import code.Controllers.JavaFXMainMenuController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * This is a class that shows the main menu.
 */
public class JFXPanelMainMenu {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private final JFXPanel jfxPanel;
    private final GameFrame owner;

    private Button start;
    private Button exit;
    private Button score;
    private Button tutor;

    private final JavaFXMainMenuController myController;

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }


    /**
     * This constructs a main menu screen in javafx, embedded in swing.
     * @param gameFrame This is the owner of the JFXPanel.
     */
    public JFXPanelMainMenu(GameFrame gameFrame){
        jfxPanel = new JFXPanel();
        owner = gameFrame;
        Platform.runLater(()->{
            jfxPanel.setScene(createScene());
        });
        myController = new JavaFXMainMenuController(owner, this);
    }

    /**
     * This method creates the scene on which buttons and the background are displayed.
     * @return Returns the constructed scene.
     */
    private Scene createScene() {

        createButtons();

        start.setOnAction(e-> myController.handle(e));
        exit.setOnAction(e-> myController.handle(e));
        tutor.setOnAction(e-> myController.handle(e));
        score.setOnAction(e-> myController.handle(e));


        VBox root = new VBox();
        initializeBackground(root);
        ImageView titleResized = loadImages(root);
        //root.setStyle("-fx-background: red;");
        root.getChildren().addAll(titleResized,start,tutor,score,exit);
        return new Scene(root);
    }

    /**
     * This method loads the main menu text.
     * @param center this is the vbox in which the text is placed.
     * @return Returns the main menu text in imageview form.
     */
    private ImageView loadImages(VBox center){
        Image title = new Image(String.valueOf(getClass().getResource("/Images/mainMenuText.png")));
        ImageView titleResized = new ImageView();
        titleResized.setImage(title);
        titleResized.setFitWidth(jfxPanel.getWidth());
        titleResized.setFitHeight(jfxPanel.getHeight()/4);
        //titleResized.fitWidthProperty().bind(center.widthProperty());
        //titleResized.fitHeightProperty().bind(center.heightProperty());
        //titleResized.setPreserveRatio(true);
        titleResized.setSmooth(true);
        return titleResized;
    }

    /**
     * This method initializes the background.
     * @param root this is the vbox to add the background to.
     */
    private void initializeBackground(VBox root){
        root.setSpacing(50);
        root.setAlignment(Pos.BASELINE_CENTER);
        BackgroundSize backgroundSize = new BackgroundSize(1,1,true,true,false,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/playfield0.png")),DEF_WIDTH,DEF_HEIGHT,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        root.setBackground(background);

    }

    /**
     * This changes the text of the start button after the game is exited once.
     */
    public void changeStartText(){
        start.setText("Continue");
    }

    /**
     * This method creates the Buttons and sets their IDs.
     */
    private void createButtons(){
        start= new Button("Start");
        exit= new Button("Quit");
        score = new Button("Scores");
        tutor = new Button("Tutorial");

        start.setId("start");
        exit.setId("quit");
        score.setId("score");
        tutor.setId("tutor");
    }

}
