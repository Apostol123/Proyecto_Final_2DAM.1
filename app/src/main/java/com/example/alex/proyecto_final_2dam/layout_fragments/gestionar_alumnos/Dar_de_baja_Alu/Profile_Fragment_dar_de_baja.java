package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Dar_de_baja_Alu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Fragment_dar_de_baja extends Fragment {

    private Alumno alumno;
    private AlumnosDAO alumnosDAO;

    private Button btn_borrar_alu;
    private TextView tv_nie;
    private TextView tv_nom;
    private TextView tv_cognoms;
    private TextView tv_tipo_caret;
    private TextView tv_acuenta_matricula;
    private TextView tv_nr_practicas;



    public Profile_Fragment_dar_de_baja() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_dar_de_baja_layout, container, false);
        tv_nie = (TextView) view.findViewById(R.id.tv__profile_nie);
        tv_nom = (TextView) view.findViewById(R.id.tv__profile_Nom);
        tv_cognoms = (TextView) view.findViewById(R.id.tv__profile_apellido);
        tv_tipo_caret = (TextView) view.findViewById(R.id.tv__profile_tipo_carnet);
        tv_acuenta_matricula = (TextView) view.findViewById(R.id.tv__profile_acuenta_matr);
        tv_nr_practicas = (TextView) view.findViewById(R.id.tv__profile_nr_pract);
        btn_borrar_alu=(Button)view.findViewById(R.id.dar_de_baja);


        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());

        alumnosDAO=new AlumnosDAO(base_deDatos_autoescuela);


        load_Data();
        btn_borrar_alu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder aldial = new AlertDialog.Builder(getContext());
                aldial.setMessage("Estas seguro que quiere dar de baja a "+alumno.getNom()+" ?").setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onClickDarDeBajaAlu();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                                Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
                                Bundle bundle= new Bundle();
                                bundle.putString("baja","Baja");
                                consultar_alumnos_fragment.setArguments(bundle);
                                fragmentManager.popBackStack();
                                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment ).addToBackStack(null).commit();


                            }
                        });
                AlertDialog alertDialog = aldial.create();
                alertDialog.setTitle("CONFIRAMCIÓN");
                alertDialog.show();


            }


        });


        return view;
    }

    private void load_Data() {
        if (alumno != null) {
            tv_nie.setText("NIE: " + alumno.getNie());
            tv_nom.setText("NOMBRE: " + alumno.getNom());
            tv_cognoms.setText("APELLIDOS: " + alumno.getCognoms());
            tv_tipo_caret.setText("TIPO CARNET: " + alumno.getTipo_carnet());
            tv_acuenta_matricula.setText("DINERO ACUENTA: " + String.valueOf(alumno.getAcuenta_matricula()) + "€");
            tv_nr_practicas.setText("NR PRACTICAS: " + String.valueOf(alumno.getNr_practicas()));
        }
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    private void onClickDarDeBajaAlu(){

        if (alumnosDAO.borrar_alu(alumno)) {

            android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
            Bundle bundle= new Bundle();
            bundle.putString("baja","Baja");
            consultar_alumnos_fragment.setArguments(bundle);
            fragmentManager.popBackStack();

            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment ).addToBackStack(null).commit();

            Toast toast = Toast.makeText(getContext(),"Alumno Borrado",Toast.LENGTH_LONG);
            toast.show();
            } else{
            android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
            Bundle bundle= new Bundle();
            bundle.putString("baja","Baja");
            consultar_alumnos_fragment.setArguments(bundle);
            fragmentManager.popBackStack();
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment ).addToBackStack(null).commit();

            Toast toast = Toast.makeText(getContext(),"Error Borrando el alumno Contacte Servicio Tecnico",Toast.LENGTH_LONG);
            toast.show();
        }
    }


}


