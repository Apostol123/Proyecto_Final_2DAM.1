package com.example.alex.proyecto_final_2dam.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;

import java.util.ArrayList;

public class Bono_DAO {
    private Base_deDatos_Autoescuela basedeDatosAutoescuela;
    private SQLiteDatabase db;

    public Bono_DAO(Base_deDatos_Autoescuela basedeDatosAutoescuela) {
        this.basedeDatosAutoescuela = basedeDatosAutoescuela;
    }

    public ArrayList<Bono_Practica> ListaBonos(){
        ArrayList<Bono_Practica> bonos_practicas= new ArrayList<>();
        db=basedeDatosAutoescuela.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from bonos ",null);

        if (cursor.moveToFirst()){
            do {
                Bono_Practica bono_practica = new Bono_Practica();
                bono_practica.setId(cursor.getInt(0));
                bono_practica.setNie_alu(cursor.getString(1));
                bono_practica.setCantida_dinero(cursor.getFloat(2));
                bono_practica.setCant_practicas(cursor.getInt(3));
                bono_practica.setFecha_bono(cursor.getString(4));
                bonos_practicas.add(bono_practica);
                System.out.println("BONO DAO "+bono_practica.getNie_alu());




            }while (cursor.moveToNext());
        }

        return bonos_practicas;
    }

    public boolean addBono(Bono_Practica bono_practica, Alumno alumno){
        boolean inserted = false;
        db=basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("nie_alumno",alumno.getNie());
            contentValues.put("cantidad_dinero",bono_practica.getCantida_dinero());
            contentValues.put("cant_pract",bono_practica.getCant_practicas());
            contentValues.put("fecha_pract",bono_practica.getFecha_bono());

            long insertado = db.insert("bonos",null,contentValues);
            if (insertado!=-1){
                inserted=true;
            }

        }else System.out.println("base de datos en add bono nula");

        return inserted;
    }

    public boolean modificarDatosBono(Bono_Practica bono_practica){
        boolean modificado= false;
        db=basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("codi",bono_practica.getId());
            contentValues.put("nie_alumno",bono_practica.getNie_alu());
            contentValues.put("cantidad_dinero",bono_practica.getCantida_dinero());
            contentValues.put("cant_pract",bono_practica.getCant_practicas());
            contentValues.put("fecha_pract",bono_practica.getFecha_bono());
            long updated = db.update("bonos",contentValues,"codi = ?",new String[]{String.valueOf(bono_practica.getId())});
            if (updated!=-1){
                modificado=true;
            }

        }
        return modificado;

    }

    public boolean borrar_bono (Bono_Practica bono_practica){
        boolean borrado=false;
        db = basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            String selection = "codi "+"LIKE ?";
            String[] selection_args={String.valueOf(bono_practica.getId())};
            long deleted = db.delete("bonos",selection,selection_args);
            if (deleted!=-1){
                borrado=true;
            }
        }
        return borrado;
    }
}
