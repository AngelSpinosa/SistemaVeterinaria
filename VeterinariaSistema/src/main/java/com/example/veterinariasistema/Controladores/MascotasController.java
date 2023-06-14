package com.example.veterinariasistema.Controladores;

import com.example.veterinariasistema.Vistas.Mascotas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MascotasController {

    public static Connection conn;

    public MascotasController()throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria?user=root&password=123456");
    }

    public List<Mascotas> listaMascotas()  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Mascotas");
        List<Mascotas> listaMascotas = new ArrayList<Mascotas>();
        while(resultados.next()) {
            Integer idMascota = resultados.getInt("id");
            String nombreMascota =  resultados.getString("nombre");
            String especie = resultados.getString("especie");
            String raza = resultados.getString("raza");
            Integer edad = resultados.getInt("edad");
            Integer prop_id = resultados.getInt("dueño_id");

            //Tomar en cuenta que el constructor llevaba null como ultimo parametro
            Mascotas mascota = new Mascotas(idMascota, nombreMascota,  especie, raza,edad,prop_id);
            listaMascotas.add(mascota);
        }
        return listaMascotas;
    }


    //Busqueda de Mascota por ID
    public static Mascotas buscarPorID(int idMascota)  throws Exception {
        Statement inst = conn.createStatement();
        ResultSet resultados = inst.executeQuery("select * from Mascotas where id = "+idMascota);
        Mascotas mascotas = null;
        if(resultados.next()) {
            Integer id = resultados.getInt("id");
            String nombreMascota =  resultados.getString("nombre");
            String especie = resultados.getString("especie");
            String raza = resultados.getString("raza");
            Integer edad = resultados.getInt("edad");
            Integer prop_id = resultados.getInt("dueño_id");

            mascotas = new Mascotas(id,nombreMascota,especie,raza,edad,prop_id);

        }
        return mascotas;
    }


    //Agregar registro de mascotas
    public static boolean agregarMascota(Mascotas mascotas) {
        if (validarMascota(mascotas)) {
            try {

                String valores = String.format("%d, %s,%s,%s", mascotas.getId(),mascotas.getEspecie(),mascotas.getRaza(),mascotas.getEdad(),mascotas.getPropietario_id());
                String instSQL = "insert into Mascotas (id,nombre,especie,raza,edad,dueño_id) values (" + valores + ")";
                Statement inst = conn.createStatement();
                inst.executeUpdate(instSQL);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else
            return false;
    }


    //Validacion de mascotas

    private static boolean validarMascota(Mascotas mascotas) {
        boolean correcto = true;
        if (mascotas == null)
            correcto = false;
        else if (mascotas.getNombre().isBlank())
            correcto = false;
        else {
            try {
                String instSQL = String.format("select id from Mascotas where id = '%d'",
                        mascotas.getId());
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
    public boolean modificarMascota(Mascotas mascotas) {
        try {
            String valores = String.format("%d, %s,%s,%s", mascotas.getId(),mascotas.getEspecie(),mascotas.getRaza(),mascotas.getEdad(),mascotas.getPropietario_id());
            String instSQL = String.format("update  Mascotas set %s where id = %d", valores, mascotas.getId());
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Eliminar registro de Dueño
    public boolean eliminarMascota(int id) {
        try {
            String instSQL = "delete from Mascotas where id = "+id;
            Statement inst = conn.createStatement();
            inst.executeUpdate(instSQL);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
