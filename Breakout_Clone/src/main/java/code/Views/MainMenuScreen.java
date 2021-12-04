package code.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import code.*;
import code.Controllers.MainMenuListener;

public class MainMenuScreen extends JPanel{

    private JButton start, settings, exit, about;
    Image img, mainText;

    private MainMenuListener menuListener;

    public MainMenuScreen(GameFrame owner){
        super();

        this.menuListener = new MainMenuListener(owner);
        add(new JLabel("BRICK BREAKER"));

        try {
            img = ImageIO.read(getClass().getResource("/bricks.jpg"));
            mainText = ImageIO.read(getClass().getResource("/mainmenu.png"));
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        buttonAdder();
    }

    public void paintComponent (Graphics g){
        g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),null);
        g.drawImage(mainText, 0, this.getHeight()/4, this.getWidth(),200,null);
    }

    public void buttonAdder(){
        start = new JButton("          Start Game          ");
        start.setActionCommand("startGame");

        settings = new JButton("    Settings    ");
        settings.setActionCommand("settings");

        exit = new JButton("    Exit    ");
        exit.setActionCommand("exit");

        about = new JButton("    About    ");
        about.setActionCommand("info");

        add(start);
        add(about);
        add(settings);
        add(exit);

        setVisible(true);

        start.addActionListener(menuListener);
        settings.addActionListener(menuListener);
        exit.addActionListener(menuListener);
        about.addActionListener(menuListener);
    }
}
