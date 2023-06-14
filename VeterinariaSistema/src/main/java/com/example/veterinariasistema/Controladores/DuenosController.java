package com.example.veterinariasistema.Controladores;

import com.example.veterinariasistema.Vistas.Dueno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DuenosController {
    public static Connection conn;

    public DuenosController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Dueno> listaDuenos()  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Dueños");
        List<Dueno> listaDuenos = new ArrayList<Dueno>();
        while(resultados.next()) {
            int idDueno = resultados.getInt("id");
            String nombreCompleto =  resultados.getString("nombre");
            String direccion = resultados.getString("direccion");
            String telefono = resultados.getString("telefono");

//Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Dueno dueno = new Dueno(idDueno, nombreCompleto,  direccion, telefono);
            listaDuenos.add(dueno);
        }
        return listaDuenos;
    }


    //Busqueda de veterinario por ID
    public static Dueno buscarPorID(int idDueno)  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Dueños where id = "+idDueno);
        Dueno dueno = null;
        if(resultados.next()) {
            int id = resultados.getInt("id");
            String nombreCompleto =  resultados.getString("nombre");
            String direccion = resultados.getString("direccion");
            String telefono = resultados.getString("telefono");

            dueno = new Dueno(id,nombreCompleto,direccion,telefono);

        }
        return dueno;
    }


    //Agregar registro de veterinario
    public boolean agregarDueno(Dueno dueno) {
        if (validarDueno(dueno)) {
            try {

                String valores = String.format("%d, '%s','%s','%s'", dueno.getIdDueno(), dueno.getNombreCompleto(), dueno.getDireccion(), dueno.getTelefono());
                String instSQL = "insert into Dueños (id,nombre,telefono,direccion) values (" + valores + ")";
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

    private boolean validarDueno(Dueno dueno) {
        boolean correcto = true;
        if (dueno == null)
            correcto = false;
        else if (dueno.getNombreCompleto().isBlank())
            correcto = false;
        else {
            try {
                String instSQL = String.format("select id from Dueños where id = '%s'",
                        dueno.getIdDueno());
                Statement inst = conn.createStatement();
                ResultSet resultado = inst.executeQuery(instSQL);
                correcto = !resultado.next();
            } catch (Exception e) {
                return false;
            }
        }
        return  correcto;
    }

    //Modificar registro de Dueño
    public boolean modificarDueno(Dueno dueno) {
        try {

            String valores = String.format("%d, %s,%s,%s", dueno.getIdDueno(),dueno.getNombreCompleto(),dueno.getDireccion(),dueno.getTelefono());
            String instSQL = String.format("update  Dueños set %s where id = %d", valores, dueno.getIdDueno());
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Eliminar registro de Dueño
    public boolean eliminarDueno(int id) {
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
