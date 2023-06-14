package com.example.veterinariasistema.Vistas;

import java.sql.Date;
import java.sql.Time;

public class Citas {
    private int idCita;
    private Date fecha;
    private Time hora;
    private int id_mascota;
    private int id_veterinario;
    public Citas(Integer idCita, Date fechaCita, Time horaCita, Integer idMascota, Integer idVeterinario) {
        this.idCita = this.idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.id_mascota = id_mascota;
        this.id_veterinario = id_veterinario;
    }

    //Setters y getters
    public int getIdCita() {
        return idCita;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public java.util.Date getFecha() {
        return fecha;
    }
    public void setFecha(java.util.Date fecha) {
        this.fecha = (Date) fecha;
    }

    public Time getHora() {
        return hora;
    }
    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getId_mascota() {
        return id_mascota;
    }
    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_veterinario() {
        return id_veterinario;
    }
    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

}
