package com.example.alex.proyecto_final_2dam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 22/03/2018.
 */

public class Base_deDatos_Autoescuela extends SQLiteOpenHelper {


   private  final String crear_tabla_usuarios ="create table usuarios(" +
           "nie text primary key ," +
           "password not null);";
   private final String crear_tabla_alumnos="create table alumnos(nie text primary key," +
            " nom text not null," +
            "cognoms text not null," +
            "nr_practicas number ," +
            "tipo_carnet text not null," +
            "acuenta_matricula float , " +
           "telefono text ,"+
           "direccion text "+
            ") ";
    private   final String crear_tabla_practicas = "create table practicas(codi integer primary key autoincrement," +
            "data_pract text," +
            "lugar_pract text," +
            "duracion text," +
            "nie_alu text ," +
            "hora_salida text,"+
            "realizada integer,"+
            "foreign key(nie_alu) references crear_tabla_alumnos(nie));";
    private   final String crear_tabla_bonos = "create table bonos(" +
            "codi integer primary key autoincrement," +
            "nie_alumno text  not null," +
            "cantidad_dinero float," +
            "cant_pract number,"+
            "fecha_pract text,"+
            "foreign key(nie_alumno) references crear_tabla_alumnos(nie))" ;



    //versión de la base de datos
    private static final int version = 7;
    private static final String nombre_db="Autoescuela";

   public Base_deDatos_Autoescuela(Context context) {
        super(context, nombre_db,null, version);
       System.out.println("ojo en el constructor de la base de datos");

    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("ojo en el oncreate de sqlite");
        db.execSQL(crear_tabla_alumnos);
        db.execSQL(crear_tabla_practicas);
        db.execSQL(crear_tabla_bonos);
        db.execSQL(crear_tabla_usuarios);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("drop table if exists alumnos");
            db.execSQL("drop table if exists practicas ");
            db.execSQL("drop table if exists bonos");
            db.execSQL("drop table if exists manejo_bonos");
            db.execSQL("drop table if exists usuarios");


            db.execSQL(crear_tabla_alumnos);
            db.execSQL(crear_tabla_practicas);
            db.execSQL(crear_tabla_bonos);
            db.execSQL(crear_tabla_usuarios);



    }
}
