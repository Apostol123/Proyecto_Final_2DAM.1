package com.example.alex.proyecto_final_2dam.layout_fragments.Bonos;


import android.app.DatePickerDialog;
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

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Modificar_Bono extends Fragment {
    private AlumnosDAO alumnosDAO;
    private Bono_DAO bono_dao ;


    private Alumno alumno;
    private Button fecha_nuevo_bono;
    private final String info ="ERROR";
    private EditText dinero_nuevo_bono;
    private EditText nr_nuevo_pract;
    private Button select_nuevo_alu;
    private Bono_Practica bono_practica;
    private TextView fecha_bono_a_modificar;
    private TextView nombre_e_apellido_del_alu_del_bono;
    private TextView nr_pract_bono;
    private  TextView importe_del_bono;
    private  Button btn_guardar_bono_modificado;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private String data_bono="";


    public Modificar_Bono() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_modificar__bono,container,false);
        fecha_nuevo_bono=(Button) view.findViewById(R.id.button_text_modificar_bono_fecha_bono);
        dinero_nuevo_bono=(EditText) view.findViewById(R.id.importe_modificar_bono);
        nr_nuevo_pract=(EditText)view.findViewById(R.id.nr_de_practicas_modificar_bono);
        select_nuevo_alu=(Button)view.findViewById(R.id.button_seleccionar_alumno_modificar_bonos);
        btn_guardar_bono_modificado=(Button)view.findViewById(R.id.btn_guardar_bono_modificado);

        fecha_bono_a_modificar = (TextView)view.findViewById(R.id.tv_fecha_Bono_a_modificar);
        nombre_e_apellido_del_alu_del_bono=(TextView)view.findViewById(R.id.Nombre_del_alumno_Del_Bono);
        nr_pract_bono =(TextView)view.findViewById(R.id.nr_de_practicas_bono_a_modificar);
        importe_del_bono =(TextView)view.findViewById(R.id.tv_importe_del_bono);

        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());
        alumnosDAO = new AlumnosDAO(base_deDatos_autoescuela);
        bono_dao = new Bono_DAO(base_deDatos_autoescuela);
        bono_practica=Classe_Estatica_auxiliar.getBono_practica();

            inicializarDatos();


            fecha_nuevo_bono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSeleccionarFecha();
                }
            });
            select_nuevo_alu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSeleccionarAlu();
                }
            });

            btn_guardar_bono_modificado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Modificar_Bono();
                }
            });

        final Handler handler = new Handler();
        final int tiempo_refresco = 10000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                inicializarDatos();
                handler.postDelayed(this,tiempo_refresco);
            }
        },tiempo_refresco);




                return view;
    }

    private void Modificar_Bono(){

        if (dinero_nuevo_bono.getText().toString().length()>0){
            bono_practica.setCantida_dinero(Float.valueOf(dinero_nuevo_bono.getText().toString()));

        } if (nr_nuevo_pract.getText().length()>0){
            bono_practica.setCant_practicas(Integer.valueOf(nr_nuevo_pract.getText().toString()));

        }
        if (data_bono.toString().length()>0){
            bono_practica.setFecha_bono(data_bono.toString());
        }

        if (Classe_Estatica_auxiliar.getAlumno_bonos()!=null){
            
            Alumno alumno_bono_viejo=alumnosDAO.get_Alumno_por_NIE(bono_practica.getNie_alu());
            alumno_bono_viejo.setNr_practicas(alumno_bono_viejo.getNr_practicas()-bono_practica.getCant_practicas());
            alumno_bono_viejo.setAcuenta_matricula(alumno_bono_viejo.getAcuenta_matricula()-bono_practica.getCantida_dinero());
            alumnosDAO.modificarDatos(alumno_bono_viejo);

            alumno=Classe_Estatica_auxiliar.getAlumno_bonos();

            alumno.setNr_practicas(alumno.getNr_practicas()+bono_practica.getCant_practicas());
            alumno.setAcuenta_matricula(alumno.getAcuenta_matricula()+bono_practica.getCantida_dinero());
            
            alumnosDAO.modificarDatos(alumno);
            bono_practica.setNie_alu(alumno.getNie());
            if (bono_dao.modificarDatosBono(bono_practica)){
                inicializarDatos();
                Toast.makeText(getContext(),"BONO MODIFICADO",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getContext(),"ERROR MODIFICANDO EL BONO CONTACTE CON SERVICIO TECNICO",Toast.LENGTH_SHORT).show();

            }



        } else {
            Toast.makeText(getContext(),"ERROR ALUMNO DEL BONO NO ENCONTRADO",Toast.LENGTH_SHORT).show();

        }




        }


    private void inicializarDatos(){
        System.out.println("inicliazar datos iniciada");
        if (Classe_Estatica_auxiliar.getAlumno_bonos()!=null){
            alumno =Classe_Estatica_auxiliar.getAlumno_bonos();
            fecha_bono_a_modificar.setText("Fecha: "+bono_practica.getFecha_bono());
            nombre_e_apellido_del_alu_del_bono.setText("Apellidos: "+alumno.getCognoms());
            nr_pract_bono.setText("Practicas bono : "+String .valueOf(bono_practica.getCant_practicas()));
            importe_del_bono.setText("Importe bono : "+String .valueOf(bono_practica.getCantida_dinero()+" €"));

        } else {
            fecha_bono_a_modificar.setText(info);
            nombre_e_apellido_del_alu_del_bono.setText(info);
            nr_pract_bono.setText(info);
            importe_del_bono.setText(info);

        }


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

    private void onClickSeleccionarAlu(){
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
        Bundle bundle = new Bundle();
        bundle.putString("bono","bono");
        consultar_alumnos_fragment.setArguments(bundle);

        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, consultar_alumnos_fragment).addToBackStack("lista_alu").commit();


    }

    private void autoLoadData(){
        final Handler handler = new Handler();
        final int tiempo_refresco = 1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                inicializarDatos();
                handler.postDelayed(this,tiempo_refresco);
            }
        },tiempo_refresco);
    }



}
