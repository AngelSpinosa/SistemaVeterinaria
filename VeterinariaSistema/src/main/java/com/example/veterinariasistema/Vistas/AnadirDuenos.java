package com.example.veterinariasistema.Vistas;

import com.example.veterinariasistema.Controladores.CitasController;
import com.example.veterinariasistema.Controladores.DuenosController;
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

public class AnadirDuenos extends BorderPane {
    RegistroDuenos registroDuenos;
    private Stage stageVentanaRduenos;
    //Variable del nombre del usuario
    String nombreUsuario;
    Label labelregistrodecitas;
    Label labelusuario;
    Label labelID;
    TextField txfID;
    Label labelNombre;
    TextField txfNombre;
    Label labelTel;
    TextField txfTel;
    Label labelDireccion;
    TextField txfDireccion;
    Button btnregistrarcita;
    Button btnvisualizarcitas;

    public AnadirDuenos(Stage stageVentanaRduenos) { //Constructor por defecto
        this.stageVentanaRduenos = stageVentanaRduenos;
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

        labelregistrodecitas = new Label("Registro de dueños de mascotas");
        labelregistrodecitas.setStyle("-fx-font-size: 25; -fx-font-family: 'Times New Roman';");

        labelusuario = new Label("Usuario: " + nombreUsuario);
        labelusuario.setStyle("-fx-font-size: 15; -fx-font-family: 'Times New Roman';");

        labelID = new Label("ID del cliente");
        labelID.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfID = new TextField();
        txfID.setPromptText("Introduce el ID del cliente");
        txfID.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfID.setMinHeight(25);
        txfID.setMaxWidth(250);

        labelNombre = new Label("Nombre del cliente");
        labelNombre.setStyle("-fx-font-size: 14; -fx-font-family: 'Times New Roman';");

        txfNombre = new TextField();
        txfNombre.setPromptText("Introduce el nombre del cliente");
        txfNombre.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfNombre.setMaxWidth(250);
        txfNombre.setMinHeight(25);

        labelDireccion = new Label("Dirección del cliente");
        labelDireccion.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfDireccion = new TextField();
        txfDireccion.setPromptText("Introduce la dirección del cliente");
        txfDireccion.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfDireccion.setMaxWidth(250);
        txfDireccion.setMinHeight(50);

        labelTel = new Label("Introduce el numero de telefono");
        labelTel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");

        txfTel = new TextField();
        txfTel.setPromptText("Telefono");
        txfTel.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 50; -fx-prompt-text-fill: black;");
        txfTel.setMaxWidth(250);
        txfTel.setMinHeight(50);

        btnregistrarcita  = new Button("REGISTRAR CITA");
        btnregistrarcita.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        btnvisualizarcitas = new Button("Visualizar todas las citas");
        btnvisualizarcitas.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");
        
        vboxtituloregistro.getChildren().addAll(labelregistrodecitas, labelusuario);
        setTop(vboxtituloregistro);

        hboxbotonescitas.getChildren().addAll(btnregistrarcita, btnvisualizarcitas);

        vboxregistroconsultas.getChildren().addAll(
                labelID, txfID, labelNombre,
                txfNombre, labelDireccion, txfDireccion, hboxbotonescitas
        );

        btnvisualizarcitas.setOnAction(evt ->{
            RegistroDuenos registroDuenos = new RegistroDuenos(stageVentanaRduenos);
            Scene sceneanaRcita = new Scene(registroDuenos, 600, 450);
            stageVentanaRduenos.setScene(sceneanaRcita);
        });
        btnregistrarcita.setOnAction(evt -> {
            // Obtener los datos ingresados en los TextField
            int idDuenos = Integer.parseInt(txfID.getText());
            String nombreString = txfNombre.getText();
            String direccionString = txfDireccion.getText();
            String telefonoString = txfTel.getText();

            // Crear una instancia de la clase "Citas" con los datos obtenidos
            Dueno dueno = new Dueno(idDuenos, nombreString, direccionString, telefonoString);

            // Crear una instancia del controlador "CitasController"
            DuenosController duenosController = null;
            try {
                duenosController = new DuenosController();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Llamar al método "agregarCita" del controlador para guardar la cita en la base de datos
            boolean exito = duenosController.agregarDueno(dueno);

            if (exito) {
                // Mostrar un mensaje de éxito
                System.out.println("La cita se ha registrado correctamente.");

                // Limpiar los campos de los TextField
                txfID.clear();
                txfNombre.clear();
                txfTel.clear();
                txfDireccion.clear();
            } else {
                // Mostrar un mensaje de error
                System.out.println("Ha ocurrido un error al registrar la cita.");
            }
        });
        setCenter(vboxregistroconsultas);
    }
}
