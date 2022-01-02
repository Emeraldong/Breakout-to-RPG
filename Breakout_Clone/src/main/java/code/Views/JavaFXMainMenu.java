package code.Views;

        import code.Controllers.JavaFXMainMenuController;
        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.embed.swing.JFXPanel;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXMLLoader;
        import javafx.geometry.Pos;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.image.Image;
        import javafx.scene.layout.*;
        import javafx.stage.Stage;

        import javax.imageio.ImageIO;
        import javax.swing.*;
        import java.awt.*;
        import java.io.IOException;
        import java.net.URL;

        import javax.swing.SwingUtilities;
        import javax.swing.SwingUtilities;

        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.layout.StackPane;
        import javafx.stage.Stage;



public class JavaFXMainMenu extends Application {

    private JavaFXMainMenuController myController;

    private int width;
    private int height;

    private Image img;

    public JavaFXMainMenu() {
        //myController = new JavaFXMainMenuController(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);

        primaryStage.setTitle("Breakout");
        Button start= new Button("Start");
        Button exit= new Button("Quit");
        Button score = new Button("Scores");
        Button tutor = new Button("Tutorial");

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Welcome!");
                new GameFrame().initialize();
                Platform.exit();
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        });

        URL url = getClass().getResource("/field.jpg");
        img = new Image(String.valueOf(url));

        BackgroundSize backgroundSize = new BackgroundSize(600,450,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(String.valueOf(getClass().getResource("/field.jpg")),600,450,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        VBox vbox = new VBox(start, tutor, score, exit);
        vbox.setSpacing(50);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        StackPane root = new StackPane();
        root.setBackground(background);
        root.getChildren().add(vbox);
        primaryStage.setScene(new Scene(root, 600,450));
        primaryStage.show();
        //myController = new JavaFXMainMenuController(this);
        /*Button launch = new Button("Launch");
        launch.setOnAction(e -> {
            SwingUtilities.invokeLater(GameFrame::new);
            primaryStage.hide();
        });*/
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainMenuFXML.fxml"));
        try {
            Parent root = fxmlLoader.load();
            //StackPane root = new StackPane(launch);
            Scene scene = new Scene(root, 600, 450);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}