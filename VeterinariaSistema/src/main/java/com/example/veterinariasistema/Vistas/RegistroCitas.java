package com.example.veterinariasistema.Vistas;

import com.example.veterinariasistema.Controladores.CitasController;
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

import java.util.ArrayList;
import java.util.List;

public class RegistroCitas extends BorderPane {
    AnadirCitas anadirCitas;
    private Stage stage;
    //Elementos
    TextField txfbuscador;
    Button btnbuscador;
    TableView<Citas> tbvcitas;
    Button btnagregar;
    Button btneiliminar;
    Button btneditar;

    //Constructor parametrizado
    public RegistroCitas(Stage stage) {
        IniciarComponentes();
        this.stage = stage; // Asignar el valor recibido al campo stage
        this.anadirCitas = new AnadirCitas(stage);
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

        TableColumn<Citas, String> columnaID = new TableColumn<>("ID");
        TableColumn<Citas, String> columnaFecha = new TableColumn<>("FECHA");
        TableColumn<Citas, String> columnaHora = new TableColumn<>("Hora");
        TableColumn<Citas, String> columnaMascota_id = new TableColumn<>("ID_MASCOTA");
        TableColumn<Citas, String> columnaVet_id = new TableColumn<>("ID_VETERINARIO");
        tbvcitas.getColumns().addAll(columnaID,columnaFecha,columnaHora,columnaMascota_id, columnaVet_id);

        //Evento que cambia la ventana a AnadirDuenos
        btnagregar.setOnAction(evt ->{
            AnadirCitas anadirCitas = new AnadirCitas(stage);
            Scene sceneanadircita = new Scene(anadirCitas, 450, 480);
            stage.setScene(sceneanadircita);
        });

        btnbuscador.setOnAction(evt -> {
            String idBusqueda = txfbuscador.getText().trim();
            if (!idBusqueda.isEmpty()) {
                try {
                    int id = Integer.parseInt(idBusqueda);
                    List<Citas> citasEncontradas = buscarCitasPorID(id);
                    actualizarTabla(citasEncontradas);
                } catch (NumberFormatException e) {
                    // El texto ingresado no es un número válido
                    // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
                }
            }
        });

        // Crear una instancia del controlador "CitasController"
        CitasController citasController = null;
        try {
            citasController = new CitasController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            // Obtener la lista de citas desde la base de datos
            List<Citas> listaCitas = citasController.listaCitas();

            // Limpiar los elementos existentes en el TableView
            tbvcitas.getItems().clear();

            // Agregar las citas a la lista del TableView
            tbvcitas.getItems().addAll(listaCitas);
        } catch (Exception e) {
            // Mostrar un mensaje de error
            System.out.println("Ha ocurrido un error al obtener las citas.");
        }

        setCenter(tbvcitas);
    }
    private List<Citas> buscarCitasPorID(int id) {
        List<Citas> citasEncontradas = new ArrayList<>();

        try {
            CitasController citasController = new CitasController();
            Citas citaEncontrada = citasController.buscarPorID(id);

            if (citaEncontrada != null) {
                citasEncontradas.add(citaEncontrada);
            }
        } catch (Exception e) {
            // Manejo de excepciones en caso de error en la consulta
            // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
        }

        return citasEncontradas;
    }
    private void actualizarTabla(List<Citas> citas) {
        tbvcitas.getItems().clear();
        tbvcitas.getItems().addAll(citas);
    }

}
