package com.example.veterinariasistema.Controladores;
import com.example.veterinariasistema.Vistas.Citas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitasController {
    Connection connection;

    //Conexión en el constructor
    public CitasController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Citas> listaCitas()  throws Exception {
        Statement inst = connection.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Citas");
        List<Citas> listaCitas = new ArrayList<Citas>();
        while(resultados.next()) {
            Integer idCita = resultados.getInt("id");
            Date fechaCita = resultados.getDate("fecha");
            Time horaCita = resultados.getTime("hora");
            int consultorioCita = resultados.getInt("consultorio");
            Integer idMascota = resultados.getInt("mascota_id");
            Integer idVeterinario = resultados.getInt("veterinario_id");


            //Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Citas cita = new Citas(idCita, fechaCita, horaCita, idMascota,idVeterinario);
            listaCitas.add(cita);
        }
        return listaCitas;
    }

    //Busqueda de citas por ID
    public Citas buscarPorID(int id)  throws Exception {
        Statement inst = connection.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Citas where id = "+id);
        Citas cita=null;
        if(resultados.next()) {
            Integer idCita = resultados.getInt("id");
            Date fechaCita = resultados.getDate("fecha");
            Time horaCita = resultados.getTime("hora");
            Integer consultorioCita = resultados.getInt("consultorio");
            Integer idMascota = resultados.getInt("mascota_id");
            Integer idVeterinario = resultados.getInt("veterinario_id");

            cita = new Citas(idCita,fechaCita,horaCita,idMascota,idVeterinario);
        }
        return cita;
    }

    //Agregar registro de cita
    public boolean agregarCita(Citas citas) {
        if (validarCita(citas)) {
            try {

                String valores = String.format("%d, %s,%s,%s", citas.getIdCita(),citas.getFecha(),citas.getHora(),citas.getId_mascota(),citas.getId_veterinario());
                String instSQL = "insert into Citas (id,nombre,especie,raza,edad,dueño_id) values (" + valores + ")";
                Statement inst = connection.createStatement();
                inst.executeUpdate(instSQL);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else
            return false;
    }


    //Validar registro de cita

    private boolean validarCita(Citas citas) {
        boolean correcto = true;
        if (citas == null)
            correcto = false;
        else if (citas.getFecha().toString().isBlank())
            correcto = false;
        else {
            try {
                String instSQL = String.format("select id from Citas where id = '%d'",
                        citas.getIdCita());
                Statement inst = connection.createStatement();
                ResultSet resultado = inst.executeQuery(instSQL);
                correcto = !resultado.next();
            } catch (Exception e) {
                return false;
            }
        }
        return  correcto;
    }

    public boolean modificarCita(Citas citas) {
        try {
            String valores = String.format("%d, %s,%s,%s", citas.getIdCita(),citas.getFecha(),citas.getHora(),citas.getId_mascota(),citas.getId_veterinario());
            String instSQL = String.format("update  Mascotas set %s where id = %d", valores, citas.getIdCita());
            Statement inst = connection.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean eliminarCita(int id) {
        try {
            String instSQL = "delete from Citas where id = "+id;
            Statement inst = connection.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
