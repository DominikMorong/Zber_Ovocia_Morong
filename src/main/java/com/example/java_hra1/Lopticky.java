package com.example.java_hra1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Lopticky extends Canvas {
    GraphicsContext gc;
    Scene scene;
    Group root;
    Timeline casovac;

    Kosik kosik;
    GameEngine gameEngine;
    private int speed;
    private boolean hasCollided = false;

    public Lopticky(Scene scene, Group root, Kosik kosik, GameEngine gameEngine) {
        super(40, 40);
        this.gameEngine = gameEngine;
        this.kosik = kosik;
        this.root = root;
        this.scene = scene;
        speed= (int) (6 + (Math.random() * 7));
        casovac = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> pohyb()));
        casovac.setCycleCount(Animation.INDEFINITE);
        casovac.play();
        gc = getGraphicsContext2D();
        Vykresli();
    }

    public void Vykresli() {
    int random_number = (int) (1 + Math.random()*5);
        Image apple = new Image("C:\\Users\\dodom\\Desktop\\Domko_Z\\Java - projekty\\Zber_Ovocia_Morong\\src\\main\\resources\\com\\example\\java_hra1\\apple"+random_number+".jpg");
        gc.drawImage(apple, 0, 0, 40, 40);
    }

    public void pohyb() {
        if (!hasCollided && gameEngine.checkCollision(kosik,this)) {
            Vymaz();
            kosik.Vykresli();
            gameEngine.scoreCounter(true);
            hasCollided = true;
        }
        if (!hasCollided && getLayoutY() > scene.getHeight() - 20) {
            Vymaz();
            gameEngine.scoreCounter(false);
            hasCollided = true;
        }
        if (getLayoutX() < 0 || getLayoutX() > scene.getWidth()-30) {
            Vymaz();
        }
        setLayoutY(getLayoutY() + speed);
    }

    public void Vymaz() {
        root.getChildren().remove(this);
    }
}


