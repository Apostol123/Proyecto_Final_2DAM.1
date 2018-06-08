package com.example.alex.proyecto_final_2dam.Auxiliar;

import android.content.Context;

import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Bonos_Fragment;

public class Classe_Estatica_auxiliar {
    private static Alumno alumno;
    private  static Alumno alumno_bonos;
    private static Bono_Practica bono_practica;
    private static Bonos_Fragment bonos_fragment;
    private static Alumno alumno_borrar_alumno_bonos;
    private static Bono_Practica bono_practica_a_eliminar;
    private static Base_deDatos_Autoescuela base_deDatos_autoescuela_MAIN;

    public static Base_deDatos_Autoescuela getBase_deDatos_autoescuela_MAIN() {
        return base_deDatos_autoescuela_MAIN;
    }

    public static void setBase_deDatos_autoescuela_MAIN(Base_deDatos_Autoescuela base_deDatos_autoescuela_MAIN) {
        Classe_Estatica_auxiliar.base_deDatos_autoescuela_MAIN = base_deDatos_autoescuela_MAIN;
    }

    public static Alumno getAlumno() {
        return alumno;
    }

    public static void setAlumno(Alumno alumno) {
        Classe_Estatica_auxiliar.alumno = alumno;
    }

    public static Alumno getAlumno_bonos() {
        return alumno_bonos;
    }

    public static void setAlumno_bonos(Alumno alumno_bonos) {
        Classe_Estatica_auxiliar.alumno_bonos = alumno_bonos;
    }

    public static Bono_Practica getBono_practica() {
        return bono_practica;
    }

    public static void setBono_practica(Bono_Practica bono_practica) {
        Classe_Estatica_auxiliar.bono_practica = bono_practica;
    }

    public static Bonos_Fragment getBonos_fragment() {
        return bonos_fragment;
    }

    public static void setBonos_fragment(Bonos_Fragment bonos_fragment) {
        Classe_Estatica_auxiliar.bonos_fragment = bonos_fragment;
    }

    public static Alumno getAlumno_borrar_alumno_bonos() {
        return alumno_borrar_alumno_bonos;
    }

    public static void setAlumno_borrar_alumno_bonos(Alumno alumno_borrar_alumno_bonos) {
        Classe_Estatica_auxiliar.alumno_borrar_alumno_bonos = alumno_borrar_alumno_bonos;
    }

    public static Bono_Practica getBono_practica_a_eliminar() {
        return bono_practica_a_eliminar;
    }

    public static void setBono_practica_a_eliminar(Bono_Practica bono_practica_a_eliminar) {
        Classe_Estatica_auxiliar.bono_practica_a_eliminar = bono_practica_a_eliminar;
    }
}
