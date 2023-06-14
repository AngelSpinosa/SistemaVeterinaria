package com.example.veterinariasistema.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VentanaLogin extends BorderPane {
    MenuPrincipal menuprincipal;
    //Contraseñas y usernames
    private Map<String, String> usuarios;
    private Stage stage; //variable para usarla como parametro en el constructor
    //Componentes
    Label iniciosesion;
    TextField txfiniciosesion;
    Label contrasena;
    PasswordField pswcontrasena;
    Button btnlogin;
    public VentanaLogin(Stage stage){ //constructo con Stage como parametro para poder cambiarlo en un evento
        InicializarComponentes();
        this.menuprincipal = new MenuPrincipal(stage);
        this.stage = stage;
        this.usuarios = new HashMap<>();
        usuarios.put("Maria Hernandez", "Mah23");
        usuarios.put("Luis Enrique Ochoa", "Lochoa79");
        usuarios.put("William Mora","Wmora13");
    }


    public void InicializarComponentes(){
        //Logo
        //InputStream inputStream = this.getClass().getResourceAsStream("/Media/Animalia.png");
        File file = new File("src/com/example/veterinariasistema/Media/Animalia.png");
        String absolutePath = file.getAbsolutePath();
        Image imagelogo = new Image(file.toURI().toString());
        ImageView imageViewlogo = new ImageView(imagelogo);

        VBox opclogin = new VBox();
        HBox ContLogo = new HBox();

        iniciosesion = new Label("Inicio de sesión");
        iniciosesion.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");
        txfiniciosesion = new TextField();
        txfiniciosesion.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfiniciosesion.setPromptText("Nombre de usuario");
        txfiniciosesion.setMaxWidth(150);
        txfiniciosesion.setMaxHeight(30);
        contrasena = new Label("Contraseña");
        contrasena.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");
        pswcontrasena = new PasswordField();
        pswcontrasena.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        pswcontrasena.setPromptText("Contraseña");
        pswcontrasena.setMaxWidth(150);
        pswcontrasena.setMaxHeight(30);
        btnlogin = new Button("Iniciar sesión");
        btnlogin.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        //Ordenar los componentes
        opclogin.getChildren().addAll(iniciosesion, txfiniciosesion, contrasena, pswcontrasena, btnlogin);
        opclogin.setAlignment(Pos.CENTER);
        opclogin.setSpacing(8);
        ContLogo.getChildren().addAll();//-----------------
        ContLogo.setAlignment(Pos.TOP_CENTER);
        setTop(ContLogo);
        setCenter(opclogin);
        setMargin(ContLogo, new Insets(10, 10, 0, 10));
        setMargin(opclogin, new Insets(10, 10, 0, 10));

        //evento cambio de ventana
        btnlogin.setOnAction(e ->{
            String nombreUsuario = txfiniciosesion.getText();
            MenuPrincipal menuprincipal = new MenuPrincipal(stage);
            Scene escenaMP = new Scene(menuprincipal, 550, 400); //nueva escena para cambiarla cuando ocurra el evento
            stage.setScene(escenaMP); //se establece la nueva escena en el stage
        });

    }
}
