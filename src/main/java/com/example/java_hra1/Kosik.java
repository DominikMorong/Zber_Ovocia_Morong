package com.example.java_hra1;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Kosik extends Canvas {
    GraphicsContext gc;
    Group root;
    Scene scene;
    public Kosik(Scene scene,Group root){
        super(100,40);
        this.scene=scene;
        this.root=root;
        gc=getGraphicsContext2D();
        Vykresli();
        setOnKeyPressed(e->spracuj(e));
            setFocusTraversable(true);
            requestFocus();
    }
        public void spracuj(KeyEvent e){
        if (e.getCode()==KeyCode.LEFT)dolava();
        if (e.getCode()==KeyCode.RIGHT)doprava();
    }
    public void Vykresli() {
        Image basketImage = new Image("C:\\Users\\dodom\\Desktop\\Domko_Z\\Java - projekty\\Zber_Ovocia_Morong\\src\\main\\resources\\com\\example\\java_hra1\\wooden-box-for-vegetables-illustration-set-free-vector.jpg");
        gc.drawImage(basketImage, 0, 0, 100, 40);
    }
        public void dolava(){
            if (getLayoutX() <= -70) {
                setLayoutX(scene.getWidth()-30);
            }else {
                setLayoutX(getLayoutX() - 20);
            }
}
        public void doprava(){
            if (getLayoutX() >= scene.getWidth()-30) {
                setLayoutX(-70);
            }else {
                setLayoutX(getLayoutX() + 20);
            }
}
}