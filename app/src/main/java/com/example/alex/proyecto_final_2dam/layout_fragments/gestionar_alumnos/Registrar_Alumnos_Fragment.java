package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.Capa_de_logica.Logica_Añdair_alumno;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class Registrar_Alumnos_Fragment extends Fragment {
    //***DECLRACION DE VARIABLES  **\\
        private EditText et_nie;
        private EditText et_nom;
        private EditText et_cognoms;
        private EditText et_tipo_caret;
        private EditText et_acuenta_matricula;
        private EditText et_nr_practicas;
        private EditText et_telefono;
        private EditText et_direccion;

    private Logica_Añdair_alumno logica_añdair_alumno;

        private Button btn_registrar;
        private Button btn_resetar_campos;


    public Registrar_Alumnos_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrar__alumnos_,container,false);

        //***INSTANCIADO DE LOS EDIT TEXT **\\
        et_nie=(EditText) view.findViewById(R.id.nie);
        et_nom=(EditText)view.findViewById(R.id.Nom);
        et_cognoms=(EditText)view.findViewById(R.id.apellido);
        et_nr_practicas=(EditText)view.findViewById(R.id.nr_pract);
        et_tipo_caret=(EditText)view.findViewById(R.id.tipo_carnet);
        et_acuenta_matricula=(EditText)view.findViewById(R.id.acuenta_matr);
        et_direccion=(EditText)view.findViewById(R.id.et_Direccion);
        et_telefono=(EditText)view.findViewById(R.id.et_Telefono);
        //***INSTANCIADO DE LOS BOTONES  **\\
       btn_registrar = (Button)view.findViewById(R.id.registrar_alu);
       btn_resetar_campos = (Button)view.findViewById(R.id.resetar_campos_button);
        logica_añdair_alumno = new Logica_Añdair_alumno(this);

        //*** ASIGNANDO ACCIONES A LOS BOTONES  **\\
       btn_registrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
          logica_añdair_alumno.registar_Alu(et_nie,et_nom,et_cognoms,et_nr_practicas,et_tipo_caret,et_acuenta_matricula,et_direccion,et_telefono);

           }
       });
       btn_resetar_campos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               logica_añdair_alumno.resetearCampos(et_nie,et_nom,et_cognoms,et_nr_practicas,et_tipo_caret,et_acuenta_matricula,et_direccion,et_telefono);
           }
       });


        return view;
    }











}
