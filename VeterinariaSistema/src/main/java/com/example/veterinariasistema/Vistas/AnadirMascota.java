package com.example.veterinariasistema.Vistas;

import com.example.veterinariasistema.Controladores.CitasController;
import com.example.veterinariasistema.Controladores.MascotasController;
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

public class AnadirMascota extends BorderPane {
    private Stage stageVentanaRmascota;
    //Variable del nombre del usuario
    String nombreUsuario;
    Label labelregistrodecitas;
    Label labelusuario;
    Label labelID;
    TextField txfID;
    Label labelNombre;
    TextField txfNombre;
    Label labelEspecie;
    TextField txfEspecie;
    Label labelRaza;
    TextField txfRaza;
    Label labelEdad;
    TextField txfEdad;
    Label labelduenoID;
    TextField txfduenoID;
    Button btnregistrarcita;
    Button btnvisualizarcitas;

    public AnadirMascota(Stage stageVentanaRmascota) { //Constructor por defecto
        this.stageVentanaRmascota = stageVentanaRmascota;
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

        labelregistrodecitas = new Label("Registro de mascotas");
        labelregistrodecitas.setStyle("-fx-font-size: 25; -fx-font-family: 'Times New Roman';");

        labelusuario = new Label("Usuario: " + nombreUsuario);
        labelusuario.setStyle("-fx-font-size: 15; -fx-font-family: 'Times New Roman';");

        labelID = new Label("ID del la mascota");
        labelID.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfID = new TextField();
        txfID.setPromptText("ID");
        txfID.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfID.setMinHeight(25);
        txfID.setMaxWidth(250);

        labelNombre = new Label("Nombre de la mascota");
        labelNombre.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfNombre = new TextField();
        txfNombre.setPromptText("Introduce el nombre de la mascota");
        txfNombre.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfNombre.setMaxWidth(250);
        txfNombre.setMinHeight(25);

        labelEspecie = new Label("Especie de la mascota");
        labelEspecie.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfEspecie = new TextField();
        txfEspecie.setPromptText("Introduce la especie de la mascota");
        txfEspecie.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfEspecie.setMaxWidth(250);
        txfEspecie.setMinHeight(50);

        labelRaza = new Label("Introduce la especie de la raza");
        labelRaza.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfRaza = new TextField();
        txfRaza.setPromptText("Raza");
        txfRaza.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfRaza.setMaxWidth(250);
        txfRaza.setMinHeight(50);

        labelEdad = new Label("Introduce la edad en meses de la mascota");
        labelEdad.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfEdad = new TextField();
        txfEdad.setPromptText("Edad");
        txfEdad.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfEdad.setMaxWidth(250);
        txfEdad.setMinHeight(50);

        labelduenoID = new Label("Introduce el ID del dueño");
        labelduenoID.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfduenoID = new TextField();
        txfduenoID.setPromptText("ID");
        txfduenoID.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfduenoID.setMaxWidth(250);
        txfduenoID.setMinHeight(50);

        btnregistrarcita  = new Button("REGISTRAR CITA");
        btnregistrarcita.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        btnvisualizarcitas = new Button("Visualizar todas las citas");
        btnvisualizarcitas.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        vboxtituloregistro.getChildren().addAll(labelregistrodecitas, labelusuario);
        setTop(vboxtituloregistro);

        hboxbotonescitas.getChildren().addAll(btnregistrarcita, btnvisualizarcitas);

        vboxregistroconsultas.getChildren().addAll(
                labelID, txfID, labelNombre,
                txfNombre, labelEspecie, txfEspecie, labelRaza, txfRaza, labelEdad, txfEdad, hboxbotonescitas
        );

        btnvisualizarcitas.setOnAction(evt ->{
            RegistroMascotas registromascotas = new RegistroMascotas(stageVentanaRmascota);
            Scene sceneanaRcita = new Scene(registromascotas, 600, 450);
            stageVentanaRmascota.setScene(sceneanaRcita);
        });
        btnregistrarcita.setOnAction(evt -> {
            // Obtener los datos ingresados en los TextField
            int idMascota = Integer.parseInt(txfID.getText());
            String nombreString = txfNombre.getText();
            String especiaString = txfEspecie.getText();
            String razaString = txfRaza.getText();
            int edad = Integer.parseInt(txfEdad.getText());
            int duenoID = Integer.parseInt(txfduenoID.getText());

            // Crear una instancia de la clase "Citas" con los datos obtenidos
            Mascotas mascotas = new Mascotas(idMascota, nombreString, especiaString, razaString, edad, duenoID);

            // Crear una instancia del controlador "CitasController"
            CitasController citasController = null;
            try {
                citasController = new CitasController();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Llamar al método "agregarCita" del controlador para guardar la cita en la base de datos
            boolean exito = MascotasController.agregarMascota(mascotas);

            if (exito) {
                // Mostrar un mensaje de éxito
                System.out.println("La cita se ha registrado correctamente.");

                // Limpiar los campos de los TextField
                txfID.clear();
                txfNombre.clear();
                txfEspecie.clear();
                txfRaza.clear();
                txfEdad.clear();
                txfduenoID.clear();
            } else {
                // Mostrar un mensaje de error
                System.out.println("Ha ocurrido un error al registrar la cita.");
            }
        });
        setCenter(vboxregistroconsultas);
    }
}
