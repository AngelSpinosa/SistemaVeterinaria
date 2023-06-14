module com.example.veterinariasistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.veterinariasistema to javafx.fxml;
    exports com.example.veterinariasistema;
    exports com.example.veterinariasistema.Vistas;
    opens com.example.veterinariasistema.Vistas to javafx.fxml;
}