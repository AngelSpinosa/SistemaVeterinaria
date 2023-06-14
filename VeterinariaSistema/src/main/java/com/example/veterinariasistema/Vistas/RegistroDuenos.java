package com.example.veterinariasistema.Vistas;

import com.example.veterinariasistema.Controladores.DuenosController;
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

public class RegistroDuenos extends BorderPane {

    AnadirDuenos anadirDuenos;
    private Stage stage;
    //Elementos
    TextField txfbuscador;
    Button btnbuscador;
    TableView<Dueno> tbvcitas;
    Button btnagregar;
    Button btneiliminar;
    Button btneditar;

    //Constructor parametrizado
    public RegistroDuenos(Stage stage) {
        IniciarComponentes();
        this.stage = stage; // Asignar el valor recibido al campo stage
        this.anadirDuenos = new AnadirDuenos(stage);
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

        TableColumn<Dueno, String> columnaID = new TableColumn<>("ID");
        TableColumn<Dueno, String> columnaNombre = new TableColumn<>("NOMBRE");
        TableColumn<Dueno, String> columnaTelefono = new TableColumn<>("TELEFONO");
        TableColumn<Dueno, String> columnaDireccion = new TableColumn<>("DIRECCION");
        tbvcitas.getColumns().addAll(columnaID,columnaNombre,columnaTelefono,columnaDireccion);
        //evento que cambia de ventana a AnadirDuenos
        btnagregar.setOnAction(evt ->{
            AnadirDuenos anadirDuenos = new AnadirDuenos(stage);
            Scene sceneanadirduenos = new Scene(anadirDuenos, 450, 480);
            stage.setScene(sceneanadirduenos);
        });
        btnbuscador.setOnAction(evt -> {
            String idBusqueda = txfbuscador.getText().trim();
            if (!idBusqueda.isEmpty()) {
                try {
                    int id = Integer.parseInt(idBusqueda);
                    List<Dueno> duenosEncontrados = buscarDuenosPorID(id);
                    actualizarTabla(duenosEncontrados);
                } catch (NumberFormatException e) {
                    // El texto ingresado no es un número válido
                    // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
                }
            }
        });

        setCenter(tbvcitas);
    }

    private List<Dueno> buscarDuenosPorID(int id) {
        List<Dueno> duenosEncontrados = new ArrayList<>();

        try {
            String query = "SELECT * FROM Dueños WHERE id = ?";
            PreparedStatement statement = DuenosController.conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultados = statement.executeQuery();

            while (resultados.next()) {
                int idDueno = resultados.getInt("id");
                String nombreCompleto = resultados.getString("nombre");
                String direccion = resultados.getString("direccion");
                String telefono = resultados.getString("telefono");

                Dueno dueno = new Dueno(idDueno, nombreCompleto, direccion, telefono);
                duenosEncontrados.add(dueno);
            }
        } catch (Exception e) {
            // Manejo de excepciones en caso de error en la consulta
            // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción
        }

        // Actualizar la tabla con los dueños encontrados
        actualizarTabla(duenosEncontrados);

        return duenosEncontrados;
    }


    private void actualizarTabla(List<Dueno> duenos) {
        tbvcitas.getItems().clear();
        tbvcitas.getItems().addAll(duenos);
    }


}
