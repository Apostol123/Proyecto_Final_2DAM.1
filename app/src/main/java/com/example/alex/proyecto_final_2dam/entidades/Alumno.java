package com.example.alex.proyecto_final_2dam.entidades;

import java.util.ArrayList;

/**
 * Created by Alex on 21/03/2018.
 */

public class Alumno {
    private String nie;
    private String nom;
    private String cognoms;
    private int nr_practicas;
    private String Tipo_carnet;
    private float  acuenta_matricula;
    private ArrayList<Practica>practicas = new ArrayList<>();
    private String telefono;
    private String direccion;




    public Alumno(String nie, String nom, String cognoms, int nr_practicas, String tipo_carnet, int acuenta_matricula,String telefono,String direccion) {
        this.nie = nie;
        this.nom = nom;
        this.cognoms = cognoms;
        this.nr_practicas = nr_practicas;
        Tipo_carnet = tipo_carnet;
        this.acuenta_matricula = acuenta_matricula;
        this.telefono=telefono;
        this.direccion=direccion;

    }

    public Alumno() {

    }

    public Alumno(String nie, String nom, String cognoms, int nr_practicas, String tipo_carnet, int acuenta_matricula) {
        this.nie = nie;
        this.nom = nom;
        this.cognoms = cognoms;
        this.nr_practicas = nr_practicas;
        Tipo_carnet = tipo_carnet;
        this.acuenta_matricula = acuenta_matricula;

    }
    public Alumno(String nie, String nom, String cognoms, int nr_practicas, String tipo_carnet, int acuenta_matricula,ArrayList<Practica>practicas) {
        this.nie = nie;
        this.nom = nom;
        this.cognoms = cognoms;
        this.nr_practicas = nr_practicas;
        this.Tipo_carnet = tipo_carnet;
        this.practicas=practicas;
        this.acuenta_matricula = acuenta_matricula;

    }


    @Override
    public String toString() {
        return this.getNom()+" "+this.getCognoms()+"\n"+" "+this.getTelefono()+" "+this.getDireccion();
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public int getNr_practicas() {
        return nr_practicas;
    }

    public void setNr_practicas(int nr_practicas) {
        this.nr_practicas = nr_practicas;
    }

    public String getTipo_carnet() {
        return Tipo_carnet;
    }

    public void setTipo_carnet(String tipo_carnet) {
        Tipo_carnet = tipo_carnet;
    }

    public int getAcuenta_matricula() {
        return (int) acuenta_matricula;
    }

    public void setAcuenta_matricula(float acuenta_matricula) {
        this.acuenta_matricula = acuenta_matricula;
    }

    public ArrayList<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(ArrayList<Practica> practicas) {
        this.practicas = practicas;
    }

    public void add_practica(Practica practica){
        this.practicas.add(practica);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
