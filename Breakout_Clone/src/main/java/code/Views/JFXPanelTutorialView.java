package code.Views;

import code.Controllers.TutorialViewControl;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * This is the class where the Tutorial is displayed.
 */
public class JFXPanelTutorialView {

    private final JFXPanel jfxPanel;
    private final GameFrame owner;

    private final TutorialViewControl controller;

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }

    public GameFrame getOwner() {
        return owner;
    }

    /**
     * This constructs the JFXPanel on which the tutorial can be viewed.
     * @param gameFrame This is the owner of the JFXPanel.
     */
    public JFXPanelTutorialView(GameFrame gameFrame){
        owner = gameFrame;
        jfxPanel = new JFXPanel();
        Platform.runLater(()->{
            jfxPanel.setScene(createScene());
        });
        controller = new TutorialViewControl(owner);
    }

    /**
     * This creates the scene on which the tutorial is displayed.
     * @return Returns the constructed Scene.
     */
    private Scene createScene(){
        VBox root = new VBox();
        initializeBackground(root);
        root.setOnKeyReleased(e->controller.backToMainMenu());
        root.setOnMouseClicked(e-> controller.backToMainMenu());


        return new Scene(root);
    }

    /**
     * This method initializes the background of the JFXPanel.
     * @param root This is the root on which the background is drawn.
     */
    private void initializeBackground(VBox root){
        BackgroundSize backgroundSize = new BackgroundSize(1,1,true,true,false,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/Images/tutorial.png")),600,450,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        root.setBackground(background);

    }

}
