package com.example.veterinariasistema.Vistas;

import com.example.veterinariasistema.Controladores.MascotasController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistroMascotas extends BorderPane {
    private Stage stage;
    //Elementos
    TextField txfbuscador;
    Button btnbuscador;
    TableView<Mascotas> tbvcitas;
    Button btnagregar;
    Button btneiliminar;
    Button btneditar;

    //Constructor parametrizado
    public RegistroMascotas(Stage stage) {
        IniciarComponentes();
        this.stage = stage; // Asignar el valor recibido al campo stage
    }

    public void IniciarComponentes(){
        //Contenedores
        HBox hBoxBuscador = new HBox();
        VBox vBoxOpcionescliente = new VBox();

        //Elementos
        txfbuscador = new TextField();
        txfbuscador.setPromptText("Buscar citas disponibles");
        txfbuscador.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 30; -fx-prompt-text-fill: black;");

        btnbuscador = new Button("Buscar");
        btnbuscador.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        tbvcitas = new TableView<>();

        btnagregar = new Button("Agregar");
        btnagregar.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        btneiliminar = new Button("Eliminar");
        btneiliminar.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        btneditar = new Button("Editar");
        btneditar.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 5;");

        //Agregar los componentes a los contenedores
        hBoxBuscador.getChildren().addAll(txfbuscador, btnbuscador);
        setTop(hBoxBuscador);
        hBoxBuscador.setSpacing(6);
        hBoxBuscador.setPrefHeight(50);
        hBoxBuscador.setPrefWidth(550);

        vBoxOpcionescliente.getChildren().addAll(btnagregar,btneditar, btneiliminar);
        setRight(vBoxOpcionescliente);
        vBoxOpcionescliente.setAlignment(Pos.TOP_CENTER);
        vBoxOpcionescliente.setSpacing(5);
        vBoxOpcionescliente.setPrefWidth(75);
        vBoxOpcionescliente.setMaxHeight(325);

        TableColumn<Mascotas, String> columnaID = new TableColumn<>("ID");
        TableColumn<Mascotas, String> columnaNombre = new TableColumn<>("NOMBRE");
        TableColumn<Mascotas, String> columnaEspecie = new TableColumn<>("ESPECIE");
        TableColumn<Mascotas, String> columnaRaza = new TableColumn<>("RAZA");
        TableColumn<Mascotas, String> columnaEdad = new TableColumn<>("EDAD");
        TableColumn<Mascotas, String> columnaDueno_id = new TableColumn<>("ID_DUEÑO");
        tbvcitas.getColumns().addAll(columnaID,columnaNombre,columnaEspecie,columnaRaza, columnaEdad, columnaDueno_id);

        //evento que cambia de ventana a AnadirDuenos
        btnagregar.setOnAction(evt ->{
            AnadirMascota anadirMascota = new AnadirMascota(stage);
            Scene sceneanadirMascota = new Scene(anadirMascota, 450, 480);
            stage.setScene(sceneanadirMascota);
        });

        btnbuscador.setOnAction(evt -> {
            String idBusqueda = txfbuscador.getText().trim();
            if (!idBusqueda.isEmpty()) {
                try {
                    int id = Integer.parseInt(idBusqueda);
                    List<Mascotas> mascotasEncontradas = buscarMascotasporID(id);
                    actualizarTabla(mascotasEncontradas);
                } catch (NumberFormatException e) {
                    // El texto ingresado no es un número válido
                    // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
                }
            }
        });

        setCenter(tbvcitas);
    }
    private List<Mascotas> buscarMascotasporID(int id) {
        List<Mascotas> mascotasEncontradas = new ArrayList<>();

        try {
            String query = "SELECT * FROM Mascotas WHERE id = ?";
            PreparedStatement statement = MascotasController.conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultados = statement.executeQuery();

            while (resultados.next()) {
                int idMascotas = resultados.getInt("id");
                String nombre = resultados.getString("nombre");
                String especie = resultados.getString("especie");
                String raza = resultados.getString("raza");
                int edad = resultados.getInt("edad");
                int dueno_id = resultados.getInt("id");

                Mascotas mascotas = new Mascotas(idMascotas, nombre, especie, raza, edad, dueno_id);
                mascotasEncontradas.add(mascotas);
            }
        } catch (Exception e) {
            // Manejo de excepciones en caso de error en la consulta
            // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
        }

        // Actualizar la tabla con los dueños encontrados
        actualizarTabla(mascotasEncontradas);

        return mascotasEncontradas;
    }
    private void actualizarTabla(List<Mascotas> mascotas) {
        tbvcitas.getItems().clear();
        tbvcitas.getItems().addAll(mascotas);
    }
}
