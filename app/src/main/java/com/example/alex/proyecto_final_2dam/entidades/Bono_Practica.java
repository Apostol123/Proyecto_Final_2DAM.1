package com.example.alex.proyecto_final_2dam.entidades;

/**
 * Created by Alex on 21/03/2018.
 */

public class Bono_Practica {
    private int id;
    private  float cantida_dinero;
    private int cant_practicas;
    private String nie_alu;
    private String fecha_bono;

    public Bono_Practica(int id, float cantida_dinero, int cant_practicas, String nie_alu, String fecha_bono) {
        this.id = id;
        this.cantida_dinero = cantida_dinero;
        this.cant_practicas = cant_practicas;
        this.nie_alu=nie_alu;
        this.fecha_bono=fecha_bono;
    }
    public Bono_Practica(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCantida_dinero() {
        return cantida_dinero;
    }

    public void setCantida_dinero(float cantida_dinero) {
        this.cantida_dinero = cantida_dinero;
    }

    public int getCant_practicas() {
        return cant_practicas;
    }

    public void setCant_practicas(int cant_practicas) {
        this.cant_practicas = cant_practicas;
    }

    public String getNie_alu() {
        return nie_alu;
    }

    public void setNie_alu(String nie_alu) {
        this.nie_alu = nie_alu;
    }

    public String getFecha_bono() {
        return fecha_bono;
    }

    public void setFecha_bono(String fecha_bono) {
        this.fecha_bono = fecha_bono;
    }
}
