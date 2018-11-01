package com.example.alex.proyecto_final_2dam.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Practica;

import java.util.ArrayList;

public class Practicas_DAO {
    private Base_deDatos_Autoescuela basedeDatosAutoescuela;
    private SQLiteDatabase db;

    public Practicas_DAO(Base_deDatos_Autoescuela basedeDatosAutoescuela) {
        this.basedeDatosAutoescuela = basedeDatosAutoescuela;
    }

    public ArrayList<Practica> getPraacticaByDate(String date){
        System.out.println("Date in get by date "+date);
        ArrayList<Practica> practicas = new ArrayList<>();


            db = basedeDatosAutoescuela.getReadableDatabase();


            String [] args = {"'"+date+"'"};



        System.out.println("Date in get by date new version  "+date);
        System.out.println();

        String [] columns = {"codi","data_pract","lugar_pract","duracion","nie_alu","hora_salida","realizada"};

        String query = "select * from practicas where data_pract= "+"'"+date+"'";
        System.out.println("MAIN SELECT DE PRACTICAS BY DATE "+query);



            Cursor cursor = db.rawQuery(query,null);

       // Cursor cursor = db.query("practicas",columns,"data_pract like ?",new String[]{"%"+date+"%"+";"},null,null,null);

        // Cursor cursor1 = db.rawQuery(query,null);



                if (db!=null){
                    if (cursor.moveToFirst()){
                    try {

                            do {
                                Practica practica = new Practica();
                                practica.setId(cursor.getInt(0));
                                practica.setData_pract(cursor.getString(1));
                                practica.setLugar_practica(cursor.getString(2));
                                practica.setDuracion(cursor.getString(3));
                                practica.setNiew_alu(cursor.getString(4));
                                practica.setHora_salid(cursor.getString(5));
                                boolean value = cursor.getInt(6)>0;
                                practica.setRealizada(value);


                                practicas.add(practica);

                            }while (cursor.moveToNext());

                    }catch (Exception e){
                        System.out.println("Exepcion del select " + e.toString());

                    }

                    } else
                        System.out.println("ERROR EN CURSOSR DE GET PRACTICAS by date  EN PRACTICAS_DAO getPraacticaByDate");



                } else System.out.println("base de datos nula en get pracicas by date");


            return practicas;



    }

    public boolean modificarPractica(Practica practica){
        boolean modificada=false;
        db=basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            ContentValues contentValues = new ContentValues();

            contentValues.put("data_pract",practica.getData_pract());
            contentValues.put("lugar_pract",practica.getLugar_practica());
            contentValues.put("duracion",practica.getDuracion());
            contentValues.put("hora_salida",practica.getHora_salid());
            int realizada = 0;
            if (practica.isRealizada()){
                realizada=1;
            }
            contentValues.put("realizada",realizada);
            long updated = db.update("practicas",contentValues,"codi = ?",new String[]{String.valueOf(practica.getId())});
            if (updated!=-1){
                modificada=true;
            }
        }
        return modificada;
    }



    public ArrayList<Practica> getPraacticas(){

        ArrayList<Practica> practicas = new ArrayList<>();

//new String[]{date}
        db = basedeDatosAutoescuela.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from practicas",null);

        if (cursor.moveToFirst()){
            do {
                Practica practica = new Practica();
                practica.setId(cursor.getInt(0));
                practica.setData_pract(cursor.getString(1));
                practica.setLugar_practica(cursor.getString(2));
                practica.setDuracion(cursor.getString(3));
                practica.setNiew_alu(cursor.getString(4));
                practica.setHora_salid(cursor.getString(5));
                boolean value = cursor.getInt(6)>0;
                practica.setRealizada(value);
                practicas.add(practica);

            }while (cursor.moveToNext());

        } else
            System.out.println("ERROR EN CURSOSR DE GET PRACTICAS  EN PRACTICAS_DAO getPraacticas");

        return practicas;



    }

    public int getPracticasHechasDeAlu(Alumno alumno){
        ArrayList<Practica> practicas = new ArrayList<>();

        db = basedeDatosAutoescuela.getReadableDatabase();
        String [] columnas = {"codi","data_pract","lugar_pract","duracion","nie_alu","hora_salida","realizada"};
        Cursor cursor = db.rawQuery("select * from practicas where nie_alu="+"'"+alumno.getNie()+"'"+" and realizada=1",null);
        if (cursor.moveToFirst()){
                do {
                Practica practica = new Practica();
                practica.setId(cursor.getInt(0));
                practica.setData_pract(cursor.getString(1));
                practica.setLugar_practica(cursor.getString(2));
                practica.setDuracion(cursor.getString(3));
                practica.setNiew_alu(cursor.getString(4));
                practica.setHora_salid(cursor.getString(5));
                boolean value = cursor.getInt(6)>0;
                    practica.setRealizada(value);
                    practicas.add(practica);




            }while (cursor.moveToNext());

        } else
            System.out.println("ERROR EN CURSOSR DE GET PRACTICAS  EN PRACTICAS_DAO getPracticasHechasDeAlu");

        return practicas.size();
    }

    public boolean addPractica(Alumno alumno, Practica practica){
        boolean inserted=false;
        db= basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){

            ContentValues contentValues = new ContentValues();

            contentValues.put("data_pract",practica.getData_pract());
            contentValues.put("lugar_pract",practica.getLugar_practica());
            contentValues.put("duracion", practica.getDuracion());
            contentValues.put("nie_alu",alumno.getNie());
            contentValues.put("hora_salida ",practica.getHora_salid());
            if (practica.isRealizada()){
                contentValues.put("realizada",1);

            }else contentValues.put("realizada",0);



            long insertada = db.insert("practicas",null,contentValues);
            if (insertada!=-1){
                inserted=true;
                alumno.add_practica(practica);


            }

        }else {
            System.out.println("BASE DE DATOS DE ADD PRACTIA DE ALU ESTA NULL ");
        }
        return inserted;
    }
    public ArrayList<Practica> getPracticasByNie (String nie){
        ArrayList<Practica> practicas = new ArrayList<>();
        db = basedeDatosAutoescuela.getReadableDatabase();
        String [] columnas = {"codi","data_pract","lugar_pract","duracion","nie_alu","hora_salida "};
        Cursor cursor = db.query("practias",columnas,"codi like ? ",new String[]{"%"+nie+"%"},null,null,null);

        if (cursor.moveToFirst()){
            do {
                Practica practica = new Practica();
                practica.setId(cursor.getInt(0));
                practica.setData_pract(cursor.getString(1));
                practica.setDuracion(cursor.getString(2));
                practica.setNiew_alu(cursor.getString(3));
                practica.setHora_salid(cursor.getString(4));
                boolean value = cursor.getInt(6)>0;
                practica.setRealizada(value);
                practicas.add(practica);

            }while (cursor.moveToNext());

        } else System.out.println("ERROR EN CURSOSR DE GET PRACTICAS EN CITA_DAO getPracticasByNie ");

        return practicas;

    }

    public boolean eliminar_pract(Practica practica){
        boolean eliminada=false;
        db=basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            String selection = "codi "+"LIKE ?";
            String [] selection_args= {String.valueOf(practica.getId())};
            long eliminated=db.delete("practicas",selection,selection_args);
            if (eliminated!=-1){
                eliminada=true;
            }

        }
        return eliminada;
    }
    public boolean eliminar_pract_por_nie_alu(Alumno alumno){
        boolean eliminada=false;
        db=basedeDatosAutoescuela.getWritableDatabase();
        if (db!=null){
            String selection = "nie_alu "+"LIKE ?";
            String [] selection_args= {String.valueOf(alumno.getNie())};
            long eliminated=db.delete("practicas",selection,selection_args);
            if (eliminated!=-1){
                eliminada=true;
            }

        }
        return eliminada;
    }






}
