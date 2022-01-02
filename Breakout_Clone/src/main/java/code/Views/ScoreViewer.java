package code.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class ScoreViewer extends JPanel {

    private static int OFFSET = 50;
    private int offsetForPainting = 100;

    private File toReadOrWrite;
    private String scores;
    private Image image;

    private GameFrame owner;

    public ScoreViewer(GameFrame myGameFrame){
        this.owner = myGameFrame;
        try {
            File scoreFile = new File ("scores.txt");
            if (scoreFile.createNewFile()){
                System.out.println("File created.");
                toReadOrWrite = scoreFile;
            }
            else {
                System.out.println("File already exists.");
                toReadOrWrite = scoreFile;
            }
        }
        catch(IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public void readScore(){
        setBackground(Color.WHITE);
        BufferedImage bufferedImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.image = bufferedImage;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("scores.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Graphics g2d = image.getGraphics();
                g2d.setColor(Color.BLUE);
                System.out.println(line);
                scores = line;
                g2d.drawString(scores, this.getWidth()/2, offsetForPainting);
                g2d.dispose();
                offsetForPainting += OFFSET;
            }
            repaint();
        }
        catch(IOException e){
            System.out.println("couldn't read from the file.");
            e.printStackTrace();
        }
    }

    public void writeScore(int scoreToWrite){
        scores = String.valueOf(scoreToWrite);
        System.out.println(scores);
        try {
            FileWriter writer = new FileWriter(toReadOrWrite, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            writer.write(scoreToWrite+"\n");
            System.out.println("written score to file");
            writer.close();
        }
        catch (IOException e){
            System.out.println("couldn't write to file.");
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        if (image != null)
        {
            g.drawImage(image, 0, 0, null);
        }

    }
}

// TO DO 21/12/2021- UPDATE GRAPHICS BY DOODLING BRICKS AND PLAYER, FIND OUT HOW TO USE IMAGES AS BRICKS
