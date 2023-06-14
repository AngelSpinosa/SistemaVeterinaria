package com.example.veterinariasistema.Vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal extends BorderPane {
    RegistroDuenos registroDuenos;
    RegistroMascotas registroMascotas;
    RegistroCitas registroCitas;
    RegistroVet registroVet;
    private Stage stage;
    Label labelmenu;
    Label labelescoge;
    Label labelduenos;
    Label labelmascotas;
    Label labelcitas;
    Label labelvet;
    Button btnduenos;
    Button btnmascotas;
    Button btncitas;
    Button btnveterinarios;


    public MenuPrincipal(){
        inicializarComponentes();
    }
    public MenuPrincipal(Stage stage){
        inicializarComponentes();
        this.registroDuenos = new RegistroDuenos(stage);
        this.registroMascotas = new RegistroMascotas(stage);
        this.registroCitas = new RegistroCitas(stage);
        this.registroVet = new RegistroVet(stage);
        this.stage = stage;
    }

    private void inicializarComponentes() {
        labelmenu = new Label("MENÚ PRINCIPAL");
        labelmenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
        labelescoge = new Label("Escoge a que vista deseas acceder");
        labelescoge.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 16;");
        labelduenos = new Label(" Dueños");
        labelduenos.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 14;");
        labelmascotas = new Label("Mascotas");
        labelmascotas.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 14");
        labelcitas = new Label(" Citas");
        labelcitas.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 14");
        labelvet = new Label("Veterinarios");
        labelvet.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 14;");
        btnduenos = new Button();
        btnduenos.setStyle("-fx-background-color: transparent;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 3px;" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-pref-width: 70px; " +
                "-fx-pref-height: 70px;");
        btnmascotas = new Button();
        btnmascotas.setStyle("-fx-background-color: transparent;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 3px;" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-pref-width: 70px; " +
                "-fx-pref-height: 70px;");
        btncitas = new Button();
        btncitas.setStyle("-fx-background-color: transparent;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 3px;" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-pref-width: 70px; " +
                "-fx-pref-height: 70px;");
        btnveterinarios = new Button();
        btnveterinarios.setStyle("-fx-background-color: transparent;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 3px;" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-pref-width: 70px; " +
                "-fx-pref-height: 70px;");
        //Contenedores
        GridPane gridPaneopciones = new GridPane();
        gridPaneopciones.setAlignment(Pos.CENTER);
        gridPaneopciones.setHgap(7);
        gridPaneopciones.setVgap(7);
        //Añadir los botones y los labels al grid
        gridPaneopciones.add(btnduenos,0,0);
        gridPaneopciones.add(labelduenos,0,1);
        gridPaneopciones.add(btnmascotas,1,0);
        gridPaneopciones.add(labelmascotas,1,1);
        gridPaneopciones.add(btncitas,2,0);
        gridPaneopciones.add(labelcitas,2,1);
        gridPaneopciones.add(btnveterinarios,3,0);
        gridPaneopciones.add(labelvet,3,1);

        VBox vBoxmenu = new VBox(labelmenu,labelescoge);
        vBoxmenu.setAlignment(Pos.BASELINE_CENTER);
        vBoxmenu.setPadding(new Insets(25));

        //Eventos de cambios de ventana
        //Ventana RegistroDuenos
        btnduenos.setOnAction(evt ->{
            RegistroDuenos registroDuenos = new RegistroDuenos(stage);
            Scene escenaRegistroDuenos = new Scene(registroDuenos, 600, 450);
            stage.setScene(escenaRegistroDuenos);
        });
        //Ventana RegistroMacotas
        btnmascotas.setOnAction(evt ->{
            RegistroMascotas registroMascotas = new RegistroMascotas(stage);
            Scene escenaRegistroMascotas = new Scene(registroMascotas, 600,450);
            stage.setScene(escenaRegistroMascotas);
        });
        //Ventana RegistroCitas
        btncitas.setOnAction(evt ->{
            RegistroCitas registroCitas = new RegistroCitas(stage);
            Scene escenaRegistroCitas = new Scene(registroCitas,600,450);
            stage.setScene(escenaRegistroCitas);
        });
        //Ventana RegistroVet
        btnveterinarios.setOnAction(evt ->{
            RegistroVet registroVet = new RegistroVet(stage);
            Scene escenaRegistroVet = new Scene(registroVet,600,450);
            stage.setScene(escenaRegistroVet);
        });

        setTop(vBoxmenu);
        setCenter(gridPaneopciones);

    }
}