package com.example.veterinariasistema;

import com.example.veterinariasistema.Vistas.MenuPrincipal;
import com.example.veterinariasistema.Vistas.VentanaLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VentanaLogin root = new VentanaLogin(stage);
        Scene scene = new Scene(root, 500, 450);
        stage.setTitle("Sistema de citas Animalia");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}