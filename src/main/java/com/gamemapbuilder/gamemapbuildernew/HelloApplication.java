package com.gamemapbuilder.gamemapbuildernew;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Map Editor!");
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("icon.png"))));
        scene.getStylesheets().add(String.valueOf(HelloApplication.class.getResource("Scene.css")));
        stage.setScene(scene);
        HelloController.stage = stage;
        HelloController.scene = scene;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}