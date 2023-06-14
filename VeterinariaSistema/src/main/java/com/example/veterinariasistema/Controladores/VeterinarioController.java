package com.example.veterinariasistema.Controladores;

import com.example.veterinariasistema.Vistas.Veterinario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioController {

    public static Connection conn;

    public VeterinarioController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Veterinario> listaVeterinarios()  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Veterinarios");
        List<Veterinario> listaVeterinarios = new ArrayList<Veterinario>();
        while(resultados.next()) {
            Integer idVet = resultados.getInt("id");
            String nombre =  resultados.getString("nombre");
            String telefono = resultados.getString("telefono");
            String especialidad = resultados.getString("especialidad");

//Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Veterinario veterinario = new Veterinario(idVet, nombre,  telefono, especialidad);
            listaVeterinarios.add(veterinario);
        }
        return listaVeterinarios;
    }


    //Busqueda de veterinario por ID
    public static Veterinario buscarPorID(int id)  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Veterinarios where id = "+id);
        Veterinario veterinario=null;
        if(resultados.next()) {
            Integer idVet = resultados.getInt("id");
            String nombre =  resultados.getString("nombre");
            String telefono = resultados.getString("telefono");
            String especialidad = resultados.getString("especialidad");

            veterinario = new Veterinario(id,nombre,telefono,especialidad);

        }
        return veterinario;
    }


    //Agregar registro de veterinario
    public boolean agregarVeterinario(Veterinario veterinario) {
        if (validarVeterinario(veterinario)) {
            try {

                String valores = String.format("%d, %s,%s,%s", veterinario.getIdVet(),veterinario.getNombre(),veterinario.getTelefono(),veterinario.getEspecialidad());
                String instSQL = "insert into Veterinarios (id,nombre,telefono,especialidad) values (" + valores + ")";
                Statement inst = conn.createStatement();
                inst.executeUpdate(instSQL);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else
            return false;
    }


    //Validacion de veterinario

    private boolean validarVeterinario(Veterinario veterinario) {
        boolean correcto = true;
        if (veterinario == null)
            correcto = false;
        else if (veterinario.getNombre().isBlank())
            correcto = false;
        else {
            try {
                String instSQL = String.format("select id from Veterinarios where id = '%s'",
                        veterinario.getIdVet());
                Statement inst = conn.createStatement();
                ResultSet resultado = inst.executeQuery(instSQL);
                correcto = !resultado.next();
            } catch (Exception e) {
                return false;
            }
        }
        return  correcto;
    }

    //Modificar registro de veterinario
    public boolean modificarVeterinario(Veterinario veterinario) {
        try {

            String valores = String.format("%d, %s,%s,%s", veterinario.getIdVet(),veterinario.getNombre(),veterinario.getTelefono(),veterinario.getEspecialidad());
            String instSQL = String.format("update  Veterinario set %s where id = %d", valores, veterinario.getIdVet());
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Eliminar registro de veterinario
    public boolean eliminarVeterinario(int id) {
        try {
            String instSQL = "delete from Veterinarios where id = "+id;
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}