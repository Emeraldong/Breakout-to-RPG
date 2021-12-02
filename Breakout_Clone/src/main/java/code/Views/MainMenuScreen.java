package code.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import code.*;

public class MainMenuScreen extends JPanel{

    private JButton start, settings, exit, about;
    private CardLayout cardLayout;
    private GameFrame gameFrame;
    GamePanel gamePanel;
    MenuPanel menuPanel;

    public MainMenuScreen(){
        super();

        setBackground(Color.MAGENTA);
        add(new JLabel("Menu"));

        start = new JButton("          Start Game          ");
        settings = new JButton("    Settings    ");
        exit = new JButton("    Exit    ");
        about = new JButton("    About    ");
        add(start,"start");
        add(about);
        add(settings);
        add(exit);
        //cardLayout.show(this,"bruh wtf");
        //gameFrame = new GameFrame();





        setVisible(true);

    }
}

class MenuPanel extends JPanel {

    public MenuPanel() {
        setBackground(Color.GREEN);
        add(new JLabel("Menu"));
    }

}

class GamePanel extends JPanel {

    public GamePanel() {
        setBackground(Color.BLUE);
        add(new JLabel("Game"));
    }

}
