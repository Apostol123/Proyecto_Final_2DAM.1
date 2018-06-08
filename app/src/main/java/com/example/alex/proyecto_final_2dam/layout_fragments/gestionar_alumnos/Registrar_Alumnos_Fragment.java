package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;

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
        private String tipos_carnets[]={"AM","A1","A2","A","B","BTP","C1","C"
        ,"D","D1","E","B+E","C1+E","C+E","D1+E","D+E"};
        private Set<String> set = new HashSet<>(Arrays.asList(tipos_carnets));

        private Button btn_registrar;
        private Button btn_resetar_campos;

    //***CLASE AUXILIAR MANEJO DE BASE DE DATOS DE LOS ALUMNOS  **\\
        private AlumnosDAO alumnosDAO;


    public Registrar_Alumnos_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registrar__alumnos_,container,false);
        //***INSTANCIADO de la base de datos  **\\
        Base_deDatos_Autoescuela basedeDatosAutoescuela = new Base_deDatos_Autoescuela(getContext());
        alumnosDAO=new AlumnosDAO(basedeDatosAutoescuela);



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




        //*** ASIGNANDO ACCIONES A LOS BOTONES  **\\
       btn_registrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
          registar_Alu();
           }
       });
       btn_resetar_campos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               resetarCampos();
           }
       });


        return view;
    }



    //***METODO QUE RECOJE LA INFORMACION DEL LAYOUT DE REGISTRACION DE ALUMNO
    // Y LO GUARDA EL BASE DE DATOS **\\
    private void registar_Alu(){
        Alumno alu = new Alumno();
        //condicional que verifica si los campos estan rellenados
        if(et_nom.getText().toString().length()>1&&et_nom.getText().toString().length()>1&&et_cognoms.getText().toString().length()>1&&
                et_nr_practicas.getText().toString().length()>=1&&et_tipo_caret.getText().toString().length()>=1&&
                et_acuenta_matricula.getText().toString().length()>1&&et_direccion.getText().toString().length()>1&&et_telefono.getText().toString().length()>1){
    // condicional con validacion de campos concretos
            if (vD(et_nie.getText().toString())&&carnet_valido(et_tipo_caret.getText().toString())&&nr_valido(et_telefono.getText().toString())&&acuenta_valido(Float.valueOf(et_acuenta_matricula.getText().toString()))){
                alu.setNie(et_nie.getText().toString());
                alu.setNom(et_nom.getText().toString());
                alu.setCognoms(et_cognoms.getText().toString());
                alu.setNr_practicas(Integer.valueOf(et_nr_practicas.getText().toString()));
                alu.setTipo_carnet(et_tipo_caret.getText().toString());
                alu.setAcuenta_matricula(Float.valueOf(et_acuenta_matricula.getText().toString()));

                alu.setTelefono(et_telefono.getText().toString());

                alu.setDireccion(et_direccion.getText().toString());
               if (alumnosDAO.add_alumno(alu)){
                   Toast toast = Toast.makeText(getContext(),alu.getNom()+"\n"+"AÑADIDO CORRECTAMENTE AL REGISTRO DE ALUMNO",Toast.LENGTH_LONG);
                   toast.show();
               } else {
                   Toast toast = Toast.makeText(getContext(),"ERROR GUARDANDO EL ALUMNO "+"\n"+"CONTACTE CON EL SERVICIO TECNICO",Toast.LENGTH_LONG);
                   toast.show();
               }

            }


        }else{
            Toast t =  Toast.makeText(this.getContext(),"RELLENA TODOS LOS CAMPOS! ",Toast.LENGTH_LONG);
            t.show();
        }


    }

    private void resetarCampos(){
        et_nie.setText("");
        et_acuenta_matricula.setText("");
        et_cognoms.setText("");
        et_nom.setText("");
        et_tipo_caret.setText("");
        et_nr_practicas.setText("");
    }
        private boolean nr_valido(String tlf){
        if (tlf.length()==9){
            return true;
        }
        else     Toast.makeText(getContext(),"TELEFONO INVALIDO ",Toast.LENGTH_SHORT).show();

            return false;
        }

//METODO QUE VERIFICA SI EL TIPO DE CARNET ES VALIDO
    private boolean carnet_valido(String tipo_carnet){


           if (set.contains(tipo_carnet.toUpperCase())){

               return true ;
           }

        Toast toast = Toast.makeText(getContext(),"CARNET INVALIDO",Toast.LENGTH_SHORT);
        toast.show();

        return false;



    }
    private boolean acuenta_valido(float acuenta){
        if (acuenta<1500){
            return true;
        }
        else{Toast.makeText(getContext(),"ACUENTA DEMASIADO ELEVADO MAXIMO 1500€ ",Toast.LENGTH_SHORT).show();

            return false;
        }
    }

    // METODO QUE VERIFICA SI EL DOCUMENTO IDENTIFICATIVO TIENE UN FORMATO CORRECTO
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



    private  void comprovar_nie(){
        carnet_valido(et_tipo_caret.getText().toString());
    }
}
