package com.example.java_hra1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;



public class GameEngine extends Canvas {
    Scene scene;
    Group root;
    Kosik kosik;
    Timeline timer;
    int score;
    boolean gameOver = false;
    Text scoreText;
    public GameEngine(Scene scene, Group root) {
        this.scene = scene;
        this.root = root;
        kosik = new Kosik(scene, root);
        kosik.setLayoutY(350);
        kosik.setLayoutX((scene.getWidth() / 2) - 50);
        root.getChildren().add(kosik);
        timer = new Timeline(new KeyFrame(Duration.millis(1500), e -> {
            spawnLopticky();
        }));
        timer.setCycleCount(60);
        timer.play();
        score = 0;
        scoreText = new Text("Your Score: "+score);
        scoreText.setFont(Font.font("Retro Gaming", FontWeight.BLACK,20));
        scoreText.setFill(Color.BLACK);
        scoreText.setLayoutX(10);
        scoreText.setLayoutY(30);
        root.getChildren().add(scoreText);
    }
    private void spawnLopticky() {
        //Check if there is a ball with simmilar x position like the ball going to be spawned
        double x;
        boolean overlap;
        do {
            overlap = false;
            x = Math.random() * scene.getWidth();
            for (Node node : root.getChildren()) {
                if (node instanceof Lopticky) {
                    double otherX = node.getLayoutX();
                    double otherWidth = node.getBoundsInLocal().getWidth();
                    if (Math.abs(x - otherX) < otherWidth) {
                        overlap = true;
                        break;
                    }
                }
            }
        } while (overlap);

        Lopticky lopticky = new Lopticky(scene, root, kosik, this);
        if (x<scene.getWidth()-40) {
            lopticky.setLayoutX(x);
        }
        lopticky.setLayoutY(30);
        root.getChildren().add(lopticky);
    }
    public boolean checkCollision(Kosik kosik, Lopticky lopticky) {
        Bounds kosik1 = kosik.getBoundsInParent();
        Bounds lopticky1 = lopticky.getBoundsInParent();
        return kosik1.intersects(lopticky1);
    }
    public void scoreCounter(boolean ToF) {
        if(ToF){
            score += 10;
            updateScore(score);
        }else {
            score-=10;
        updateScore(score);
        }
    }
    public void updateScore(int score) {
        if (score >= 200) {
            gameOver=true;
            scoreText.setFont(Font.font("Retro Gaming", FontWeight.BLACK, 40));
            scoreText.setFill(Color.BLACK);
            scoreText.setLayoutX(scene.getWidth() / 2 - 220);
            scoreText.setLayoutY(scene.getHeight() / 2 - 100);
            scoreText.setText("CONGRATULATIONS!!");
            timer.stop();
        } else if (score < -40) {
            gameOver=true;
            scoreText.setFont(Font.font("Retro Gaming", FontWeight.BLACK, 40));
            scoreText.setFill(Color.BLACK);
            scoreText.setLayoutX(scene.getWidth() / 2 - 140);
            scoreText.setLayoutY(scene.getHeight() / 2 - 100);
            scoreText.setText("GAME OVER!!");
            timer.stop();
            root.getChildren().remove(kosik);
        } else if(!gameOver){
            scoreText.setText("Your Score: " + score);
        }
    }
}
