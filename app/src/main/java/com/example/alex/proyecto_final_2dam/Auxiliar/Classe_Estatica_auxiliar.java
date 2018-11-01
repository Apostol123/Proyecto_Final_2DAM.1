package com.example.alex.proyecto_final_2dam.Auxiliar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;

import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Bonos_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;

public class Classe_Estatica_auxiliar {
    private static Alumno alumno;
    private  static Alumno alumno_bonos;
    private static Bono_Practica bono_practica;
    private static Bonos_Fragment bonos_fragment;
    private static Alumno alumno_borrar_alumno_bonos;
    private static Bono_Practica bono_practica_a_eliminar;
    private static Base_deDatos_Autoescuela base_deDatos_autoescuela_MAIN;
    private static boolean registered = false;
    private static Activity MainActivity;
    private static Boolean logged=false;
    private static Agenda_Fragment agenda_fragment;
    private  static android.support.v4.app.FragmentManager fragmentManager;
    private static SharedPreferences prefs;
    private static  GoogleApiClient googleApiClient;
    private static GoogleSignInClient mGoogleSignInClient;
    private static Boolean session_cerrada_aporoposito=false;

    public static Boolean getSession_cerrada_aporoposito() {
        return session_cerrada_aporoposito;
    }

    public static void setSession_cerrada_aporoposito(Boolean session_cerrada_aporoposito) {
        Classe_Estatica_auxiliar.session_cerrada_aporoposito = session_cerrada_aporoposito;
    }

    public static GoogleSignInClient getmGoogleSignInClient() {
        return mGoogleSignInClient;
    }

    public static void setmGoogleSignInClient(GoogleSignInClient mGoogleSignInClient) {
        Classe_Estatica_auxiliar.mGoogleSignInClient = mGoogleSignInClient;
    }

    public static GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public static void setGoogleApiClient(GoogleApiClient googleApiClient) {
        Classe_Estatica_auxiliar.googleApiClient = googleApiClient;
    }

    public static SharedPreferences getPrefs() {
        return prefs;
    }

    public static void setPrefs(SharedPreferences prefs) {
        Classe_Estatica_auxiliar.prefs = prefs;
    }

    public static FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public static void setFragmentManager(FragmentManager fragmentManager) {
        Classe_Estatica_auxiliar.fragmentManager = fragmentManager;
    }

    public static Agenda_Fragment getAgenda_fragment() {
        return agenda_fragment;
    }

    public static void setAgenda_fragment(Agenda_Fragment agenda_fragment) {
        Classe_Estatica_auxiliar.agenda_fragment = agenda_fragment;
    }

    public static Boolean getLogged() {
        return logged;
    }

    public static void setLogged(Boolean logged) {
        Classe_Estatica_auxiliar.logged = logged;
    }

    public static Activity getMainActivity() {
        return MainActivity;
    }

    public static void setMainActivity(Activity mainActivity) {
        MainActivity = mainActivity;
    }

    public static boolean isRegistered() {
        return registered;
    }

    public static void setRegistered(boolean registered) {
        Classe_Estatica_auxiliar.registered = registered;
    }

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
