package code.Controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.GameFrame;

public class MainMenuListener implements ActionListener {

    private GameFrame gameFrame;

    public MainMenuListener(GameFrame owner){
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
        }
    }
}
