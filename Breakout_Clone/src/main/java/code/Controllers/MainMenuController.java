package code.Controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Views.GameFrame;

public class MainMenuController implements ActionListener {

    private GameFrame gameFrame;

    public MainMenuController(GameFrame owner){
        gameFrame = owner;
    }

    @Override
    public void actionPerformed(ActionEvent clicked) {
        switch (clicked.getActionCommand()){
            case "startGame":
                gameFrame.getCardLayout().show(gameFrame.getContentPane(),"game");
                gameFrame.getGameBoard().requestFocus();
                break;

            case "exit":
                System.exit(0);
                break;

            case "info":
                gameFrame.getCardLayout().show(gameFrame.getContentPane(),"scores");
                gameFrame.getScoreFile().readScore();
        }
    }
}
