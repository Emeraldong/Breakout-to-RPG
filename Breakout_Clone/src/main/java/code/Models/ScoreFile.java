package code.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class handles the reading and writing of Files, namely the score file.
 */
public class ScoreFile {

    private File toReadOrWrite;

    private static final int numberOfHighScores = 5;

    private final int[] highestScores;
    private final String[] highestScoreStrings;

    private String scores;

    public String[] getHighestScoreStrings() {
        return highestScoreStrings;
    }

    /**
     * This constructs a new ScoreFile object that tries to write a file,
     * and opens one up if it already existed beforehand.
     * It also initializes the array of scores.
     */
    public ScoreFile(){
        highestScores = new int[] {0,0,0,0,0};
        highestScoreStrings = new String[] {"","","","",""};
        try {
            File scoreFile = new File ("scores.txt"); //(String.valueOf(getClass().getResource("/scores.txt")));
            if (scoreFile.createNewFile()){
                System.out.println("File created.");
            }
            else {
                System.out.println("File already exists.");
            }
            toReadOrWrite = scoreFile;
            setHighScores();
        }
        catch(IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    /**
     * This method writes Strings to the score file.
     * @param scoreToWrite
     */
    public void writeScore(String scoreToWrite){
        scores = scoreToWrite;
        System.out.println(scores);
        try {
            FileWriter writer = new FileWriter(toReadOrWrite, true);
            //BufferedWriter bufferedWriter = new BufferedWriter(writer);

            writer.write(scoreToWrite+"\n");
            System.out.println("written score to file");
            writer.close();
        }
        catch (IOException e){
            System.out.println("couldn't write to file.");
            e.printStackTrace();
        }
    }

    /**
     * This method sets the top 5 scores by reading every String in the score file,
     * isolating the score and then putting the highest ones in an array.
     */
    public void setHighScores(){
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("scores.txt"))) {
            String line;
            String newline;
            while ((line = bufferedReader.readLine()) != null) {
                newline = line.replaceAll("\\D+","");
                int numbers = Integer.valueOf(newline);
                for (int i = 0;i < numberOfHighScores; i ++){
                    if(numbers>=highestScores[i]){
                        highestScores[i] = numbers;
                        highestScoreStrings[i] = line;
                        break;
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
