package code.Controllers;

import code.Models.ScoreFile;
import code.Views.GameFrame;
import code.Views.NameEntry;

/**
 * This class handles what happens upon various actions in the NameEntry View.
 */
public class NameEntryControl {

    private static final String noName = "They gave no name...";

    private final GameFrame myGameFrame;
    private final NameEntry myOwner;


    private final ScoreFile scoreFile;

    private String name;

    /**
     * This constructs a controller for the NameEntry object that called it.
     * @param gameFrame This is the GameFrame in which the NameEntry View is located.
     * @param nameEntry This is the NameEntry that called and owns the controller.
     */
    public NameEntryControl(GameFrame gameFrame, NameEntry nameEntry){
        myGameFrame = gameFrame;
        myOwner = nameEntry;
        scoreFile = gameFrame.getJfxPanelScoreView().getScoreList();
    }

    /**
     * A method that takes in the score from the recently finished game,
     * appends the entered name to it, and then writes it to a file by
     * calling the ScoreFile object.
     * @param score
     */
    public void submitName(int score){
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

    /**
     * This method switches panels from this one to the main menu.
     */
    public void returnToMain(){
        myGameFrame.getCardLayout().show(myGameFrame.getContentPane(),"fxMenu");
    }

}
