package com.example.alex.proyecto_final_2dam.entidades;

import java.util.Date;

/**
 * Created by Alex on 21/03/2018.
 */

public class Practica {
    private int id;
    private String  data_pract;
    private String lugar_practica;
    private String duracion;
    private String niew_alu;
    private String hora_salid;
    private boolean realizada;

    public Practica(int id, String data_pract, String lugar_practica, String duracion, String niew_alu, String hora_salid) {
        this.id = id;
        this.data_pract = data_pract;
        this.lugar_practica = lugar_practica;
        this.duracion = duracion;
        this.niew_alu = niew_alu;
        this.hora_salid = hora_salid;
    }
    public Practica(int id, String data_pract, String lugar_practica, String duracion, String niew_alu, String hora_salid,boolean realizada) {
        this.id = id;
        this.data_pract = data_pract;
        this.lugar_practica = lugar_practica;
        this.duracion = duracion;
        this.niew_alu = niew_alu;
        this.hora_salid = hora_salid;
        this.realizada=realizada;
    }

    public Practica(int id, String  data_pract, String lugar_practica, String  duracion) {
        this.id = id;
        this.data_pract = data_pract;
        this.lugar_practica = lugar_practica;
        this.duracion = duracion;

    }
    public Practica(int id, String  data_pract, String lugar_practica, String duracion,String nie) {
        this.id = id;
        this.data_pract = data_pract;
        this.lugar_practica = lugar_practica;
        this.duracion = duracion;
        this.niew_alu=nie;

    }

    public String getHora_salid() {
        return hora_salid;
    }

    public void setHora_salid(String hora_salid) {
        this.hora_salid = hora_salid;
    }

    public  Practica(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getData_pract() {
        return data_pract;
    }

    public void setData_pract(String data_pract) {
        this.data_pract = data_pract;
    }

    public String getLugar_practica() {
        return lugar_practica;
    }

    public void setLugar_practica(String lugar_practica) {
        this.lugar_practica = lugar_practica;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNiew_alu() {
        return niew_alu;
    }

    public void setNiew_alu(String niew_alu) {
        this.niew_alu = niew_alu;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    @Override
    public String toString() {
        return "Practica{" +
                "id=" + id +
                ", data_pract='" + data_pract + '\'' +
                ", lugar_practica='" + lugar_practica + '\'' +
                ", duracion='" + duracion + '\'' +
                ", niew_alu='" + niew_alu + '\'' +
                ", hora_salid='" + hora_salid + '\'' +
                ", realizada=" + realizada +
                '}';
    }
}
