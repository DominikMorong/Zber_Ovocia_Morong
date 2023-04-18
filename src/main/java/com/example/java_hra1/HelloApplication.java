package com.example.java_hra1;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class HelloApplication extends Application {
boolean gameON = false;
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        Text menu = new Text();
        Text points = new Text();
        points.setFont(Font.font("Retro Gaming", FontWeight.BLACK, 20));
        points.setFill(Color.BLACK);
        points.setLayoutY(30);
        points.setLayoutX(10);
        points.setText("Catch 20 apples within 60 seconds to win!\nIf you lose 5 apples the game is over!");
        menu.setFont(Font.font("Retro Gaming", FontWeight.BLACK, 40));
        menu.setFill(Color.BLACK);
        menu.setLayoutX(scene.getWidth() / 2 - 210);
        menu.setLayoutY(scene.getHeight() / 2);
        menu.setText("Press Space to Begin");
        root.getChildren().addAll(menu,points);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE&&!gameON) {
                GameEngine gameEngine = new GameEngine(scene,root);
                gameON = true;
                root.getChildren().add(gameEngine);
                root.getChildren().removeAll(menu,points);
            }
        });
        stage.setTitle("Apple.app");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
