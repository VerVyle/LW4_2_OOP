package com.vervyle.lw5_oop;

import com.vervyle.lw5_oop.controllers.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 540, 270);
        stage.setTitle("LW5 OOP");
        stage.setScene(scene);
        stage.show();

        AppController controller = fxmlLoader.getController();
        stage.setOnCloseRequest(controller::onExit);
    }

    public static void main(String[] args) {
        launch();
    }
}