package code.Models;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScoreFile {

    private File toReadOrWrite;

    private static final int numberOfHighScores = 5;

    private int[] highestScores;
    private String[] highestScoreStrings;

    private String scores;


    public String[] getHighestScoreStrings() {
        return highestScoreStrings;
    }


    public ScoreFile(){
        highestScores = new int[] {0,0,0,0,0};
        highestScoreStrings = new String[] {"","","","",""};
        try {
            File scoreFile = new File ("scores.txt"); //(String.valueOf(getClass().getResource("/scores.txt")));
            if (scoreFile.createNewFile()){
                System.out.println("File created.");
                toReadOrWrite = scoreFile;
            }
            else {
                System.out.println("File already exists.");
                toReadOrWrite = scoreFile;
            }
            setHighScores();
        }
        catch(IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

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

    public void setHighScores(){
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("scores.txt"))) {
            String line;
            String newline;
            while ((line = bufferedReader.readLine()) != null) {
                newline = line.replaceAll("\\D+","");
                int numbers = Integer.valueOf(newline);
                for (int i = 0;i < numberOfHighScores; i ++){
                    if(numbers>highestScores[i]){
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

    public void compareScore() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(String.valueOf(getClass().getResource("/scores.txt"))))) {
            String line;
            String newline;
            while ((line = bufferedReader.readLine()) != null) {
                newline = line.replaceAll("\\D+","");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String readScore(){
        String line = new String();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(String.valueOf(getClass().getResource("/scores.txt"))))) {
            while ((line = bufferedReader.readLine()) != null) {

            }
        }
        catch(IOException e){
            System.out.println("couldn't read from the file.");
            e.printStackTrace();
        }
        finally {
            return line;
        }
    }


}
