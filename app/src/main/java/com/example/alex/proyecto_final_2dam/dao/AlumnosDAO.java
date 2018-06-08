package com.example.alex.proyecto_final_2dam.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Practica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 22/03/2018.
 */

public class AlumnosDAO {
    private Base_deDatos_Autoescuela basedeDatosAutoescuela;
    private SQLiteDatabase db;
    public AlumnosDAO(Base_deDatos_Autoescuela bdAuteoscuela) {
        basedeDatosAutoescuela = bdAuteoscuela;


    }




    public boolean add_alumno(Alumno alu){
        // la base de datos instanciada pasada por parametro se  transforma a una base de datos para uqe podamos escribir sobre ella
            db= basedeDatosAutoescuela.getWritableDatabase();

                if(db!=null){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("nie",alu.getNie());
                    contentValues.put("nom",alu.getNom());
                    contentValues.put("cognoms",alu.getCognoms());
                    contentValues.put("nr_practicas",alu.getNr_practicas());
                    contentValues.put("tipo_carnet",alu.getTipo_carnet());
                    contentValues.put("acuenta_matricula",alu.getAcuenta_matricula());
                    contentValues.put("telefono",alu.getTelefono());
                    contentValues.put("direccion",alu.getDireccion());
                   long insertado= db.insert("alumnos",null,contentValues);
                    if (insertado!=-1){
                        db.close();
                        return true;

                    }

                    else
                        db.close();
                        return false;
                }else System.out.println("BASE DE DATOS NULA");
                db.close();
            return false;
    }

    public List llista_Alu(){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        db= basedeDatosAutoescuela.getReadableDatabase();
        String [] columnas = {"nie","nom","cognoms","nr_practicas","tipo_carnet","acuenta_matricula","telefono","direccion"};
        Cursor cursor = db.query("alumnos",columnas,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Alumno alu  = new Alumno();
                alu.setNie(cursor.getString(0));
                alu.setNom(cursor.getString(1));
                alu.setCognoms(cursor.getString(2));
                alu.setNr_practicas(cursor.getInt(3));
                alu.setTipo_carnet(cursor.getString(4));
                alu.setAcuenta_matricula(cursor.getFloat(5));
                alu.setTelefono(cursor.getString(6));
                alu.setDireccion(cursor.getString(7));
                listaAlumnos.add(alu);
            }while(cursor.moveToNext());
        }
        db.close();
        return listaAlumnos;
    }

    public  List<String > get_Alu_names(){
        ArrayList<String> nombres = new ArrayList<>();
        db= basedeDatosAutoescuela.getReadableDatabase();
        String [] columnas = {"nie","nom","cognoms","nr_practicas","tipo_carnet","acuenta_matricula","telefono","direccion"};
        Cursor cursor = db.query("alumnos",columnas,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                nombres.add(cursor.getString(cursor.getColumnIndex("nom")));
            }while (cursor.moveToNext());
        }
        db.close();
        return nombres;
    }


    public List<Alumno> get_Alumnos_por_nombre(String nombre){
        ArrayList<Alumno> nombre_alu= new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        db= basedeDatosAutoescuela.getReadableDatabase();
        String [] columna = {"nie","nom","cognoms","nr_practicas","tipo_carnet","acuenta_matricula","telefono","direccion"};
        Cursor cursor = db.query("alumnos",columna,"nom like ?",new String[]{"%"+nombre+"%"},null,null,null);
        if (cursor.moveToFirst()){
            do {
                Alumno alu  = new Alumno();
                alu.setNie(cursor.getString(0));
                alu.setNom(cursor.getString(1));
                alu.setCognoms(cursor.getString(2));
                alu.setNr_practicas(cursor.getInt(3));
                alu.setTipo_carnet(cursor.getString(4));
                alu.setAcuenta_matricula(cursor.getFloat(5));
                alu.setTelefono(cursor.getString(6));
                alu.setDireccion(cursor.getString(7));


                nombre_alu.add(alu);
            }while (cursor.moveToNext());
        }
        db.close();
        return nombre_alu;

    }

    public Alumno get_Alumno_por_NIE(String nie){
            Alumno alumno = null;
        db= basedeDatosAutoescuela.getReadableDatabase();
        String [] columna = {"nie","nom","cognoms","nr_practicas","tipo_carnet","acuenta_matricula","telefono","direccion"};
        Cursor cursor = db.query("alumnos",columna,"nie like ?",new String[]{"%"+nie+"%"},null,null,null);
        if (cursor.moveToFirst()){
            do {
                Alumno alu  = new Alumno();
                alu.setNie(cursor.getString(0));
                alu.setNom(cursor.getString(1));
                alu.setCognoms(cursor.getString(2));
                alu.setNr_practicas(cursor.getInt(3));
                alu.setTipo_carnet(cursor.getString(4));
                alu.setAcuenta_matricula(cursor.getFloat(5));
                alu.setTelefono(cursor.getString(6));
                alu.setDireccion(cursor.getString(7));


               alumno = alu;
            }while (cursor.moveToNext());
        }
        db.close();
        return alumno;

    }

    public boolean borrar_alu(Alumno alumno){
        boolean borrado=false;
        db= basedeDatosAutoescuela.getWritableDatabase();

        if(db!=null){
            String selection = "nie"+" LIKE ? ";
            String[] selection_args = {alumno.getNie()};
      long deleted= db.delete("alumnos",selection,selection_args);
      if (deleted!=-1){
          borrado=true;
      }
        }
        return borrado;
    }

    public boolean modificarDatos(Alumno alu){
        boolean modificado=false;
        db= basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("nie",alu.getNie());
            contentValues.put("nom",alu.getNom());
            contentValues.put("cognoms",alu.getCognoms());
            contentValues.put("nr_practicas",alu.getNr_practicas());
            contentValues.put("tipo_carnet",alu.getTipo_carnet());
            contentValues.put("acuenta_matricula",alu.getAcuenta_matricula());
            contentValues.put("telefono",alu.getTelefono());
            contentValues.put("direccion",alu.getDireccion());
            long updated= db.update("alumnos",contentValues,"nie = ?",new String[]{alu.getNie().toString()});
           if (updated!=-1){
               modificado=true;
           }
        }
        return modificado;
    }

    public boolean incrementar_NR_Practicas (int nr,Alumno alumno){
        boolean modificado = false;
        db= basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.rawQuery("update alumnos set nr_practicas= nr_practicas + "+nr+" where nie = "+"'"+alumno.getNie()+"'",null);
            if (cursor.moveToFirst()){
                do {
                    modificado=true;

                }while (cursor.moveToNext());
            }

        }
        return modificado;
    }

    public boolean decrementar_NR_Practicas (int nr,Alumno alumno){
        boolean modificado = false;
        db= basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();
            Cursor cursor = db.rawQuery("update alumnos set nr_practicas= nr_practicas - "+nr+" where nie = "+"'"+alumno.getNie()+"'",null);
            if (cursor.moveToFirst()){
                do {
                    modificado=true;

                }while (cursor.moveToNext());
            }

        }
        return modificado;
    }






}

