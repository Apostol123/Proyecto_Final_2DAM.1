package com.example.alex.proyecto_final_2dam.Capa_de_logica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Registrar_Alumnos_Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logica_Añdair_alumno {
    private Registrar_Alumnos_Fragment registrar_alumnos_fragment;
    private AlumnosDAO alumnosDAO;
    private String tipos_carnets[]={"AM","A1","A2","A","B","BTP","C1","C"
            ,"D","D1","E","B+E","C1+E","C+E","D1+E","D+E"};
    private Set<String> set = new HashSet<>(Arrays.asList(tipos_carnets));
    private Boolean correcto_acuenta=false;
    private Boolean correcto_nr_practicas=false;


    public Logica_Añdair_alumno(Registrar_Alumnos_Fragment registrar_alumnos_fragment) {
        this.registrar_alumnos_fragment = registrar_alumnos_fragment;

        alumnosDAO= new AlumnosDAO(Classe_Estatica_auxiliar.getBase_deDatos_autoescuela_MAIN());
    }

    public void resetearCampos(EditText et_niew, EditText et_acuenta_matricula, EditText et_cognmos, EditText et_nom, EditText et_tipo_carnet, EditText et_nr_practicas, EditText et_tlf, EditText et_direccion){
        et_niew.setText("");
        et_acuenta_matricula.setText("");
        et_cognmos.setText("");
        et_nom.setText("");
        et_tipo_carnet.setText("");
        et_nr_practicas.setText("");
        et_tlf.setText("");
        et_direccion.setText("");


    }

    //***METODO QUE RECOJE LA INFORMACION DEL LAYOUT DE REGISTRACION DE ALUMNO
    // Y LO GUARDA EL BASE DE DATOS **\\
    public  void registar_Alu(EditText et_nie,EditText et_nom,EditText et_cognoms,EditText et_nr_practicas,EditText et_tipo_caret,EditText et_acuenta_matricula,EditText et_direccion,EditText et_telefono){
        Alumno alu = new Alumno();
        //condicional que verifica si los campos estan rellenados

        if(et_nom.getText().toString().length()>1&&et_nom.getText().toString().length()>1&&et_cognoms.getText().toString().length()>1&&
                et_nr_practicas.getText().toString().length()>=1&&et_tipo_caret.getText().toString().length()>=1&&
                et_acuenta_matricula.getText().toString().length()>1&&et_direccion.getText().toString().length()>1&&et_telefono.getText().toString().length()>1){
            // condicional con validacion de campos concretos
            if (vD(et_nie.getText().toString())&&carnet_valido(et_tipo_caret.getText().toString())&&nr_valido(et_telefono.getText().toString())&&comprobarCantidadACuenta(et_acuenta_matricula.getText().toString(),et_acuenta_matricula)&&comprobarCantidadPracticas(et_nr_practicas.getText().toString(),et_nr_practicas)){
                alu.setNie(et_nie.getText().toString());
                alu.setNom(et_nom.getText().toString());
                alu.setCognoms(et_cognoms.getText().toString());
                alu.setNr_practicas(Integer.valueOf(et_nr_practicas.getText().toString()));
                alu.setTipo_carnet(et_tipo_caret.getText().toString());
                alu.setAcuenta_matricula(Float.valueOf(et_acuenta_matricula.getText().toString()));

                alu.setTelefono(et_telefono.getText().toString());

                alu.setDireccion(et_direccion.getText().toString());
                if (alumnosDAO.add_alumno(alu)){
                    Toast toast = Toast.makeText(registrar_alumnos_fragment.getContext(),alu.getNom()+"\n"+"AÑADIDO CORRECTAMENTE AL REGISTRO DE ALUMNO",Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(registrar_alumnos_fragment.getContext(),"ERROR GUARDANDO EL ALUMNO "+"\n"+"CONTACTE CON EL SERVICIO TECNICO",Toast.LENGTH_LONG);
                    toast.show();
                }

            }


        }else{
            Toast t =  Toast.makeText(registrar_alumnos_fragment.getContext(),"RELLENA TODOS LOS CAMPOS! ",Toast.LENGTH_LONG);
            t.show();
        }


    }


    //METODO QUE VERIFICA SI EL TIPO DE CARNET ES VALIDO
    private boolean carnet_valido(String tipo_carnet){


        if (set.contains(tipo_carnet.toUpperCase())){

            return true ;
        }

        Toast toast = Toast.makeText(registrar_alumnos_fragment.getContext(),"CARNET INVALIDO",Toast.LENGTH_SHORT);
        toast.show();

        return false;



    }
    private boolean acuenta_valido(float acuenta){
        if (acuenta<1500){
            return true;
        }
        else{Toast.makeText(registrar_alumnos_fragment.getContext(),"ACUENTA DEMASIADO ELEVADO MAXIMO 1500€ ",Toast.LENGTH_SHORT).show();

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
        Toast toast = Toast.makeText(registrar_alumnos_fragment.getContext(),"DOCUMENTO IDENTIFICADTIVO  NO VALIDO",Toast.LENGTH_SHORT);
        toast.show();
        return false;

    }

    private Boolean comprobarCantidadACuenta(String cantidad, final EditText et_acuenta_matricula){
        int dinero =Integer.parseInt(cantidad);

        if (dinero>1000){
            AlertDialog.Builder aldial = new AlertDialog.Builder(registrar_alumnos_fragment.getContext());
            aldial.setMessage("La cantitad a cuenta supera los 1000 euros quiere continuar ? ").setCancelable(false)
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            correcto_acuenta=true;

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            et_acuenta_matricula.setText("");
                        }
                    });
            AlertDialog alertDialog = aldial.create();
            alertDialog.setTitle("CONFIRAMCIÓN");
            alertDialog.show();
        }else {
            correcto_acuenta=true;
        }
        return correcto_acuenta;
    }
    private Boolean comprobarCantidadPracticas(String cantidad, final EditText et_nr_practicas){
        int dinero =Integer.parseInt(cantidad);

        if (dinero>15){
            AlertDialog.Builder aldial = new AlertDialog.Builder(registrar_alumnos_fragment.getContext());
            aldial.setMessage("La cantitad de practicas es de "+ String.valueOf(dinero)+" quiere continuar ? ").setCancelable(false)
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            correcto_nr_practicas=true;

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            et_nr_practicas.setText("");

                        }
                    });
            AlertDialog alertDialog = aldial.create();
            alertDialog.setTitle("CONFIRAMCIÓN");
            alertDialog.show();
        } else {


            correcto_nr_practicas=true;
        }
        return correcto_nr_practicas;
    }
    private boolean nr_valido(String tlf){
        if (tlf.length()==9){
            return true;
        }
        else     Toast.makeText(registrar_alumnos_fragment.getContext(),"TELEFONO INVALIDO ",Toast.LENGTH_SHORT).show();

        return false;
    }



}
