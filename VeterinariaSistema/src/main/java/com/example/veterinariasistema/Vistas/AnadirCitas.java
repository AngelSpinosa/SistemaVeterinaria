package com.example.veterinariasistema.Vistas;

import com.example.veterinariasistema.Controladores.CitasController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.Time;

public class AnadirCitas extends BorderPane {
    RegistroCitas registroCitas;
    private Stage stageVentanaRcitas;
    private Stage stageVentanaAcitas;
    //Variable del nombre del usuario
    String nombreUsuario;
    Label labelregistrodecitas;
    Label labelusuario;
    Label labelID;
    TextField txfID;
    Label labelFecha;
    TextField txfFecha;
    Label labelHora;
    TextField txfHora;
    Label labelidmascota;
    TextField txfidmascota;
    Label labelidveterinario;
    TextField txfveterinario;
    Button btnregistrarcita;
    Button btnvisualizarcitas;

    public AnadirCitas(Stage stageVentanaRcitas) { //Constructor por defecto
        this.stageVentanaRcitas = stageVentanaRcitas;
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

        labelID = new Label("Introduce el ID a asignar a la cita");
        labelID.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfID = new TextField();
        txfID.setPromptText("ID");
        txfID.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfID.setMinHeight(25);
        txfID.setMaxWidth(250);

        labelFecha = new Label("Introduce la fecha en la que se va a agendar la cita");
        labelFecha.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfFecha = new TextField();
        txfFecha.setPromptText("Fecha (Escribirla Dia/Mes)");
        txfFecha.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfFecha.setMaxWidth(250);
        txfFecha.setMinHeight(25);

        labelHora = new Label("Introduce la hora agendada");
        labelHora.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfHora = new TextField();
        txfHora.setPromptText("Hora");
        txfHora.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfHora.setMaxWidth(250);
        txfHora.setMinHeight(50);

        labelidmascota = new Label("Introduce el ID de la mascota");
        labelHora.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfidmascota = new TextField();
        txfidmascota.setPromptText("Introduce el ID");
        txfidmascota.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");

        labelidveterinario = new Label("Introduce el ID del veterinario asignado");
        labelidveterinario.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfveterinario = new TextField();
        txfveterinario.setPromptText("Introduce el ID");
        txfveterinario.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");

        btnregistrarcita  = new Button("REGISTRAR CITA");
        btnregistrarcita.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        btnvisualizarcitas = new Button("Visualizar todas las citas");
        btnvisualizarcitas.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        vboxtituloregistro.getChildren().addAll(labelregistrodecitas, labelusuario);
        setTop(vboxtituloregistro);

        hboxbotonescitas.getChildren().addAll(btnregistrarcita, btnvisualizarcitas);

        vboxregistroconsultas.getChildren().addAll(
                labelID, txfID, labelFecha,
                txfFecha, labelHora, txfHora, hboxbotonescitas
        );

        btnvisualizarcitas.setOnAction(evt ->{
            RegistroCitas registroCitas = new RegistroCitas(stageVentanaRcitas);
            Scene sceneanaRcita = new Scene(registroCitas, 600, 450);
            stageVentanaRcitas.setScene(sceneanaRcita);
        });
        btnregistrarcita.setOnAction(evt -> {
            // Obtener los datos ingresados en los TextField
            int idCita = Integer.parseInt(txfID.getText());
            String fechaString = txfFecha.getText();
            Time hora = Time.valueOf(txfHora.getText());
            int idMascota = Integer.parseInt(txfidmascota.getText());
            int idVeterinario = Integer.parseInt(txfveterinario.getText());

            // Crear una instancia de la clase "Citas" con los datos obtenidos
            Citas cita = new Citas(idCita, Date.valueOf(fechaString), hora, idMascota, idVeterinario);

            // Crear una instancia del controlador "CitasController"
            CitasController citasController = null;
            try {
                citasController = new CitasController();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Llamar al método "agregarCita" del controlador para guardar la cita en la base de datos
            boolean exito = citasController.agregarCita(cita);

            if (exito) {
                // Mostrar un mensaje de éxito
                System.out.println("La cita se ha registrado correctamente.");

                // Limpiar los campos de los TextField
                txfID.clear();
                txfFecha.clear();
                txfHora.clear();
                txfidmascota.clear();
                txfveterinario.clear();
            } else {
                // Mostrar un mensaje de error
                System.out.println("Ha ocurrido un error al registrar la cita.");
            }
        });

        setCenter(vboxregistroconsultas);
    }

}
