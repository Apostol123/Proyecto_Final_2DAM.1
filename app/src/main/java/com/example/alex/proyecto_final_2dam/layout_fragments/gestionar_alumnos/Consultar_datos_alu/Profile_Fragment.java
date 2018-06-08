package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.Practicas_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Fragment extends Fragment {

        private Alumno alumno;
        private Practicas_DAO practicas_dao;

    private Button btn_generar_pdf;
    private Button btn_generar_app_pdf;
    private TextView tv_nie;
    private TextView tv_nom;
    private TextView tv_cognoms;
    private TextView tv_tipo_caret;
    private TextView tv_acuenta_matricula;
    private TextView tv_nr_practicas;
    private TextView tv_telefono;
    private TextView tv_Direccion;
    private TextView tv_nr_de_practicas_hechas;
    private String[] header = {"Nie","Nombre","Apellidos","Tipo Carnet", "Dinero Acuenta","Nrz de Practicas","Telefono","Direccion","Practicas Hechas"};
    private String  shortText="FICHERO DEL ALUMNO/@";
    private String longText="Datos de la autoescuela";
    private Plantilla_pdf plantilla_pdf;

    public Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_profile_,container,false);
       tv_nie=(TextView)view.findViewById(R.id.tv__profile_nie);
        tv_nom=(TextView)view.findViewById(R.id.tv__profile_Nom);
        tv_cognoms=(TextView)view.findViewById(R.id.tv__profile_apellido);
        tv_tipo_caret=(TextView)view.findViewById(R.id.tv__profile_tipo_carnet);
        tv_acuenta_matricula=(TextView)view.findViewById(R.id.tv__profile_acuenta_matr);
        tv_nr_practicas=(TextView)view.findViewById(R.id.tv__profile_nr_pract);
        btn_generar_pdf=(Button)view.findViewById(R.id.generar_pdf) ;
        btn_generar_app_pdf=(Button)view.findViewById(R.id.generar_pdf_app);
        tv_telefono=(TextView)view.findViewById(R.id.tv_Telefono);
        tv_Direccion=(TextView)view.findViewById(R.id.tv_Direccion);
        tv_nr_de_practicas_hechas=(TextView)view.findViewById(R.id.nr_practicas_hechas_consultar);
        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());
        practicas_dao= new Practicas_DAO(base_deDatos_autoescuela);


        load_Data();


        plantilla_pdf= new Plantilla_pdf(this.getContext());
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        plantilla_pdf.setFragmentManager(fragmentManager);
        plantilla_pdf.openDocument();
        plantilla_pdf.addMetaData("Alumno","Ficha","Alex Apostol");
        Date currentTime = Calendar.getInstance().getTime();
        plantilla_pdf.addTitle("Autoescuela","Alumno/@", currentTime);
        plantilla_pdf.addParafo(shortText);
        plantilla_pdf.addParafo(longText);
        plantilla_pdf.CreateTable(header,getAlumnos());
        plantilla_pdf.closeDocument();

        btn_generar_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGenerarPdf();
            }
        });

        btn_generar_app_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickViewAppPDF();
            }
        });




        return view;
    }

    private void load_Data(){
        if (alumno!=null){
            tv_nie.setText("NIE: "+alumno.getNie());
            tv_nom.setText("NOMBRE: "+alumno.getNom());
            tv_cognoms.setText("APELLIDOS: "+alumno.getCognoms());
            tv_tipo_caret.setText("TIPO CARNET: "+alumno.getTipo_carnet());
            tv_acuenta_matricula.setText("DINERO ACUENTA: "+String.valueOf(alumno.getAcuenta_matricula())+"€");
            tv_nr_practicas.setText("NR PRACTICAS: "+String.valueOf(alumno.getNr_practicas()));
            tv_telefono.setText("TLF: "+alumno.getTelefono());
            tv_Direccion.setText("Direccion: "+alumno.getDireccion());
            tv_nr_de_practicas_hechas.setText("PRACTICAS HECHAS: "+practicas_dao.getPracticasHechasDeAlu(alumno));
        }
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    private void onClickGenerarPdf(){
        plantilla_pdf.viewPDf();

    }

    private ArrayList<String[]> getAlumnos(){

        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[]{alumno.getNie(),alumno.getNom().toString(),alumno.getCognoms().toString(),alumno.getTipo_carnet().toString(),String.valueOf(alumno.getAcuenta_matricula()),
        String.valueOf(alumno.getNr_practicas()),alumno.getTelefono().toString(),alumno.getDireccion().toString(),String.valueOf(practicas_dao.getPracticasHechasDeAlu(alumno))});

        return rows;
    }

    private void onclickViewAppPDF(){
        plantilla_pdf.viewPDfApp(this.getActivity());
    }

}
