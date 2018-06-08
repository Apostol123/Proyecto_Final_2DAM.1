package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Modificar_datos_alu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_Fragment_modificar_datos extends Fragment {

    private Alumno alumno;
    private AlumnosDAO alumnosDAO;

    private Button btn_modificar_alu;
    private TextView tv_nie;
    private TextView tv_nom;
    private TextView tv_cognoms;
    private TextView tv_tipo_caret;
    private TextView tv_acuenta_matricula;
    private TextView tv_nr_practicas;
    private String[] header = {"Nie", "Nombre", "Apellidos", "Tipo Carnet", "Dinero Acuenta", "Numero de Practicas"};
    private String shortText = "FICHERO DEL ALUMNO/@";
    private String longText = "Datos de la autoescuela";
    private Plantilla_pdf plantilla_pdf;

    private EditText et_nie;
    private EditText et_nom;
    private EditText et_cognoms;
    private EditText et_tipo_caret;
    private EditText et_acuenta_matricula;
    private EditText et_nr_practicas;
    private EditText et_nr_telf;
    private EditText et_direccion;
    private   ArrayList<EditText> componenetes = new ArrayList<>();


    public Profile_Fragment_modificar_datos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment_modificar_datos_layout, container, false);
        tv_nie = (TextView) view.findViewById(R.id.tv__profile_nie);
        tv_nom = (TextView) view.findViewById(R.id.tv__profile_Nom);
        tv_cognoms = (TextView) view.findViewById(R.id.tv__profile_apellido);
        tv_tipo_caret = (TextView) view.findViewById(R.id.tv__profile_tipo_carnet);
        tv_acuenta_matricula = (TextView) view.findViewById(R.id.tv__profile_acuenta_matr);
        tv_nr_practicas = (TextView) view.findViewById(R.id.tv__profile_nr_pract);



        et_nie = (EditText) view.findViewById(R.id.et__profile_nie);
        et_nom = (EditText) view.findViewById(R.id.et__profile_Nom);
        et_cognoms = (EditText) view.findViewById(R.id.et__profile_apellido);
        et_tipo_caret = (EditText) view.findViewById(R.id.et__profile_tipo_carnet);
        et_acuenta_matricula = (EditText) view.findViewById(R.id.et__profile_acuenta_matr);
        et_nr_practicas = (EditText) view.findViewById(R.id.et__profile_nr_pract);
        btn_modificar_alu=(Button)view.findViewById(R.id.modificar_alu);
        et_nr_telf=(EditText)view.findViewById(R.id.et__profile_nr_telf_modificar_alu);
        et_direccion=(EditText)view.findViewById(R.id.et_nueva_direccion_modificar_alu);


        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());

        alumnosDAO=new AlumnosDAO(base_deDatos_autoescuela);

        load_Data();

       btn_modificar_alu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onClickGuardarDatos();
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

    private  void onClickGuardarDatos(){


                if (!isEmpty(et_nie)){
                    if (vD(et_nie.getText().toString())) {
                        alumno.setNie(et_nie.getText().toString());
                    }
             } else if (!isEmpty(et_nom)){
                    alumno.setNom(et_nom.getText().toString());

        } else if (!isEmpty(et_cognoms)){
                    alumno.setCognoms(et_cognoms.getText().toString());
        } else if (!isEmpty(et_tipo_caret)){
                    alumno.setTipo_carnet(et_tipo_caret.getText().toString());

        }else if (!isEmpty(et_acuenta_matricula)){
                    alumno.setAcuenta_matricula(Float.valueOf(et_acuenta_matricula.getText().toString()));

        } else  if (!isEmpty(et_nr_practicas)){
                    alumno.setNr_practicas(Integer.valueOf(et_nr_practicas.getText().toString()));
        } else  if (!isEmpty(et_direccion)){
                    alumno.setDireccion(et_direccion.getText().toString());
                }else if (!isEmpty(et_nr_telf)){
                    alumno.setTelefono(et_nr_telf.getText().toString());
                }

        if (alumnosDAO.modificarDatos( alumno)){

                    load_Data();
        } else {
                    Toast toast = Toast.makeText(getContext(),"ERROR CONTACTE CON EL SERVICIO TECNICO",Toast.LENGTH_LONG);
                    toast.show();
        }

}

    private  boolean isEmpty(EditText editText){
        System.out.println("is empryyyy !");
        boolean isempty=false;
        if (editText.getText().toString().equals("")){
            isempty=true;
        }
        return isempty;

    }

    private boolean vD(String niew_dni){
        ArrayList<Pattern> patterns = new ArrayList<>();


        Pattern p_dni =Pattern.compile("\\d{8}\\w{1}");
        Pattern p_nie_m = Pattern.compile("([M]|[m])\\d{7}\\w{1}");
        Pattern p_nie_x = Pattern.compile("([X]|[x])\\d{7}\\w{1}");
        Pattern p_nie_y = Pattern.compile("([Y]|[y])\\d{7}\\w{1}");
        Pattern p_nie_Z = Pattern.compile("([Z]|[z])\\d{7}\\w{1}");

        patterns.add(p_dni);
        patterns.add(p_nie_m);
        patterns.add(p_nie_x);
        patterns.add(p_nie_y);
        patterns.add(p_nie_Z);

        for (Pattern pattern :patterns) {
            Matcher m = pattern.matcher(niew_dni);
            if (m.matches()){

                return true;
            }

        }
        Toast toast = Toast.makeText(getContext(),"DOCUMENTO IDENTIFICADTIVO  NO VALIDO",Toast.LENGTH_SHORT);
        toast.show();
        return false;

    }




}


