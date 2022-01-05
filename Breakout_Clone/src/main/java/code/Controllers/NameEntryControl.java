package code.Controllers;

import code.Models.ScoreFile;
import code.Views.GameFrame;
import code.Views.NameEntry;
import javafx.event.ActionEvent;

public class NameEntryControl {

    private static final String noName = "They gave no name...";

    private GameFrame myGameFrame;
    private NameEntry myOwner;


    private ScoreFile scoreFile;

    private int score;
    private String name;

    public NameEntryControl(GameFrame gameFrame, NameEntry nameEntry){
        myGameFrame = gameFrame;
        myOwner = nameEntry;
        scoreFile = gameFrame.getJfxPanelScoreView().getScoreList();
    }

    public void submitName(int score){
        this.score = score;
        name = String.valueOf(myOwner.getTextField().getText());
        if (name.equals("")){
            name = noName;
        }

        String nameAndScore = name + " " + score;

        scoreFile.writeScore(nameAndScore);
        scoreFile.setHighScores();
        myGameFrame.getJfxPanelScoreView().resetScreen();
        myGameFrame.getJfxPanelScoreView().drawScores();

        returnToMain();

    }

    public void returnToMain(){
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
    }

}
