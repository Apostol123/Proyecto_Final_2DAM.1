package com.example.alex.proyecto_final_2dam.layout_fragments.Bonos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.dao.Bono_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;

import java.sql.SQLOutput;
import java.util.Calendar;


public class Nuevo_Bono extends Fragment {
    private Alumno alumno;
    private Button fecha_bono;
    private EditText dinero_bono;
    private EditText nr_pract;
    private Button select_alu;
    private TextView nombre_alu;
    private TextView apelldios_alu;
    private TextView nr_pract_alu;
    private  TextView diner_gastado_alu;
    private Button button_guardar_bono;
    private final String info ="NINGUN ALUMNO SELECCIONADO";
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String data_bono="";
    private String importe_bono="";
    private String nr_de_pract_del_bono="";
    private Bono_DAO bono_dao;
    private AlumnosDAO alumnosDAO;


    public Nuevo_Bono() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuevo__bono,container,false);
        fecha_bono=(Button)view.findViewById(R.id.button_text_bono_fecha_bono);
        dinero_bono=(EditText)view.findViewById(R.id.importe_bono);
        nr_pract=(EditText)view.findViewById(R.id.nr_de_practicas_bono);
        select_alu=(Button)view.findViewById(R.id.button_seleccionar_alumno_bonos);
      nombre_alu = (TextView)view.findViewById(R.id.tv_bono_nombre_alu);
      apelldios_alu=(TextView)view.findViewById(R.id.tv_bono_apellido_alu);
      nr_pract_alu=(TextView)view.findViewById(R.id.tv_bono_nr_de_pract_alu);
      diner_gastado_alu=(TextView)view.findViewById(R.id.tv_bono_DINERO_GASTADO_alu);
      button_guardar_bono=(Button)view.findViewById(R.id.btn_guardar_bono);
        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());
        bono_dao=new Bono_DAO(base_deDatos_autoescuela);
        alumnosDAO = new AlumnosDAO(base_deDatos_autoescuela);

        inicializarDatos();


        fecha_bono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickSeleccionarFecha();
            }
        });


        select_alu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarAlu();

            }
        });

        button_guardar_bono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camposCompletados();
            }
        });




        return view;
    }


    private void onClickSeleccionarAlu(){
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
        Bundle bundle = new Bundle();
        bundle.putString("bono","bono");
        consultar_alumnos_fragment.setArguments(bundle);

        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, consultar_alumnos_fragment).addToBackStack("lista_alu").commit();


    }
    private void inicializarDatos(){
        if (Classe_Estatica_auxiliar.getAlumno_bonos()!=null){
            alumno =Classe_Estatica_auxiliar.getAlumno_bonos();

            nombre_alu.setText("Nombre: "+alumno.getNom().toString());
            apelldios_alu.setText("Apellidos: "+alumno.getCognoms().toString());
            nr_pract_alu.setText("Practicas por hacer: "+String .valueOf(alumno.getNr_practicas()));
            diner_gastado_alu.setText("Dinero Gastado: "+String .valueOf(alumno.getAcuenta_matricula())+" €");

        } else {
            nombre_alu.setText(info);
            apelldios_alu.setText(info);
            nr_pract_alu.setText(info);
            diner_gastado_alu.setText(info);

        }


    }

    private void onClickImporteBono(){
        if (!isEmpty(dinero_bono.getText().toString())){
            importe_bono=dinero_bono.getText().toString();
        }



    }

    private  void  onClickNumeroDePracticas(){
        if (!isEmpty(nr_pract.getText().toString())){
            nr_de_pract_del_bono=nr_pract.getText().toString();
        }

    }

    private  boolean isEmpty(String editText){

        boolean isempty=false;
        if (editText.equals("")){
            isempty=true;
            System.out.println("is empryyyy ! "+editText.getClass().getSimpleName());
        }
        return isempty;

    }

    private void onClickSeleccionarFecha(){

        Calendar cal = Calendar.getInstance();
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);



        onDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                data_bono =dayOfMonth+"-"+month+"-"+year;
            }
        };




        DatePickerDialog dialog = new DatePickerDialog(
                this.getContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                onDateSetListener,
                año,mes,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();




    }
    private  void camposCompletados(){

        onClickImporteBono();
        onClickNumeroDePracticas();
        inicializarDatos();

        if (!isEmpty(data_bono)&&!isEmpty(importe_bono)&&!isEmpty(nr_de_pract_del_bono)){
            if (Classe_Estatica_auxiliar.getAlumno_bonos()!=null){
                alumno=Classe_Estatica_auxiliar.getAlumno_bonos();
                Bono_Practica bono_practica = new Bono_Practica();
                bono_practica.setFecha_bono(data_bono);
                bono_practica.setCant_practicas(Integer.valueOf(nr_de_pract_del_bono));
                bono_practica.setCantida_dinero(Float.valueOf(importe_bono));
                bono_practica.setNie_alu(alumno.getNie());

                if (bono_dao.addBono(bono_practica,alumno)){
                    alumno.setNr_practicas(alumno.getNr_practicas()+bono_practica.getCant_practicas());
                    alumno.setAcuenta_matricula(alumno.getAcuenta_matricula()+bono_practica.getCantida_dinero());
                    if (alumnosDAO.modificarDatos(alumno)){
                        Toast.makeText(getContext(),"BONO GUARDADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();

                    }




                } else {
                    Toast.makeText(getContext(),"ERROR GUARDANDO EL BONO CONTACTE CON EL SERVICIO TECNICO",Toast.LENGTH_LONG).show();

                }
            }else {
                Toast.makeText(getContext(),"SELECCIONA UN ALUMNO!!",Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(),"RELLENA TODOS LOS CAMPOS!!",Toast.LENGTH_SHORT).show();

        }

    }



    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
