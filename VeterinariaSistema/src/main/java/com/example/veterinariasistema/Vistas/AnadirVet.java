package com.example.veterinariasistema.Vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnadirVet extends BorderPane {
    private Stage stageVentanaRvet;
    //Variable del nombre del usuario
    String nombreUsuario;
    Label labelregistrodecitas;
    Label labelusuario;
    Label labelID;
    TextField txfID;
    Label labelNombre;
    TextField txfNombre;
    Label labelTelefono;
    TextField txfTel;
    Label labelEspecialidad;
    TextField txfEspecialidad;
    Button btnregistrarcita;
    Button btnvisualizarcitas;

    public AnadirVet(Stage stageVentanaRvet) { //Constructor por defecto
        this.stageVentanaRvet = stageVentanaRvet;
        InciarComponentes();
    }


    public AnadirVet(String nombreUsuario, Stage stageVentanaRduenos, Stage stage){ //Constructor con el nombre de usuario como parametro
        this.nombreUsuario = nombreUsuario;
        this.stageVentanaRvet = stageVentanaRvet;
        InciarComponentes();
    }


    public void InciarComponentes() {
        VBox vboxtituloregistro = new VBox();
        vboxtituloregistro.setAlignment(Pos.TOP_CENTER);

        VBox vboxregistroconsultas = new VBox();
        vboxregistroconsultas.setSpacing(3);
        vboxregistroconsultas.setAlignment(Pos.CENTER);

        HBox hboxbotonescitas = new HBox();
        hboxbotonescitas.setAlignment(Pos.CENTER);
        hboxbotonescitas.setSpacing(5);

        labelregistrodecitas = new Label("Registro de citas");
        labelregistrodecitas.setStyle("-fx-font-size: 25; -fx-font-family: 'Times New Roman';");

        labelusuario = new Label("Usuario: " + nombreUsuario);
        labelusuario.setStyle("-fx-font-size: 15; -fx-font-family: 'Times New Roman';");

        labelID = new Label("Introduce el ID a asignar al Veterinario");
        labelID.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfID = new TextField();
        txfID.setPromptText("ID");
        txfID.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfID.setMinHeight(25);
        txfID.setMaxWidth(250);

        labelNombre = new Label("Introduce el nombre del veterinario");
        labelNombre.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfNombre = new TextField();
        txfNombre.setPromptText("Nombre");
        txfNombre.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfNombre.setMaxWidth(250);
        txfNombre.setMinHeight(25);

        labelTelefono = new Label("Introduce el telefono del veterinario");
        labelTelefono.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfTel = new TextField();
        txfTel.setPromptText("Numero de celular");
        txfTel.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfTel.setMaxWidth(250);
        txfTel.setMinHeight(50);

        labelEspecialidad = new Label("Introduce la especialidad del veterinario");
        labelEspecialidad.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfEspecialidad = new TextField();
        txfEspecialidad.setPromptText("Especialidad");
        txfEspecialidad.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfEspecialidad.setMaxWidth(250);
        txfEspecialidad.setMinHeight(50);

        btnregistrarcita  = new Button("REGISTRAR CITA");
        btnregistrarcita.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        btnvisualizarcitas = new Button("Visualizar todas las citas");
        btnvisualizarcitas.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        vboxtituloregistro.getChildren().addAll(labelregistrodecitas, labelusuario);
        setTop(vboxtituloregistro);

        hboxbotonescitas.getChildren().addAll(btnregistrarcita, btnvisualizarcitas);

        vboxregistroconsultas.getChildren().addAll(
                labelID, txfID, labelNombre,
                txfNombre, labelTelefono, txfTel, labelEspecialidad, txfEspecialidad, hboxbotonescitas
        );

        btnvisualizarcitas.setOnAction(evt ->{
            RegistroVet registrovet = new RegistroVet(stageVentanaRvet);
            Scene sceneanaRcita = new Scene(registrovet, 600, 450);
            stageVentanaRvet.setScene(sceneanaRcita);
        });

        setCenter(vboxregistroconsultas);
    }
}
