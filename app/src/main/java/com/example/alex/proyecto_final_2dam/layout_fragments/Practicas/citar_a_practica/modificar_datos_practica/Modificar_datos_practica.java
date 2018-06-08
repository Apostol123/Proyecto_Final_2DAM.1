package com.example.alex.proyecto_final_2dam.layout_fragments.Practicas.citar_a_practica.modificar_datos_practica;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.Auxiliar.CostumDialogClass;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.dao.Practicas_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Modificar_datos_practica extends Fragment {

   private TextView tv_Nom_alu;
    private  TextView tv_apellido_alu;
    private TextView tv_lugar_salida;
    private TextView tv_tipo_carnet;
    private  TextView tv_hora_salida;
    private  TextView tv_fecha_salida;

    private Button seleccionar_nueva_fecha;
    private  Button seleccionar_nueva_hora;
    private  Button seleccionar_nueva_duracion;
    private  Button seleccionar_nueva_salida;
    private  Button guardar_cambios;
    private  Button resetar_campos;
    private  Button ver_en_pdf;
    private Button elminar_practica;
    private CheckBox practica_realizada;
    private Alumno alumno;
    private Practica practica;
    private CostumDialogClass costumDialogClass;
    private String hora_salida;
    private String data_pract;
    private String salida_pract;
    private String duracaion_pract;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private final static  int PLACE_PICKER_REQUEST=1;
    private Practicas_DAO practicas_dao;
    private AlumnosDAO alumnosDAO;
    private Plantilla_pdf plantilla_pdf;


    private String[] header = {"Nie","Nombre","Apellidos","Tipo Carnet"};
    private String  shortALU="DATOS ALUMNO";
    private String  shortPract="DATOS PRACTICA";


    public Modificar_datos_practica() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_modificar_datos_practica,container,false);

       tv_Nom_alu=(TextView)view.findViewById(R.id.Modificar_practica_tv__profile_Nom);
       tv_apellido_alu=(TextView)view.findViewById(R.id.Modificar_practica_tv__profile_apellido);
       tv_lugar_salida =(TextView)view.findViewById(R.id.Modificar_practica_tvLugar_practica);
       tv_tipo_carnet =(TextView)view.findViewById(R.id.Modificar_practica_tv__profile_tipo_carnet);
       tv_hora_salida =(TextView)view.findViewById(R.id.Modificar_practica_hora_salida);
       tv_fecha_salida =(TextView)view.findViewById(R.id.Modificar_practica_tv__fecha_salida);

        seleccionar_nueva_fecha  =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_bt_seleccion_fecha);
        seleccionar_nueva_hora  =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_bt_seleccion_hora);
        seleccionar_nueva_duracion  =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_btn_selecciona_duracion_pract);
        seleccionar_nueva_salida =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_btn_selecciona_lugar_pract);
        guardar_cambios =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_BTN_GUARDAR_DATOS);
        resetar_campos =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_RESETEAR_CAMPOS);
        ver_en_pdf =(Button)view.findViewById(R.id.MODIFICAR_DATOS_PRACTICA_BTN_VER_EN_PDF);
        elminar_practica=(Button)view.findViewById(R.id.BTN_BORRAR_PRACT);
        practica_realizada=(CheckBox) view.findViewById(R.id.checkbox_practica_realziada);
        plantilla_pdf = new Plantilla_pdf(getContext());
        plantilla_pdf.CreateTable(header,getAlumnos());


      //  onChecBoxClicked(view);

        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getActivity());
        practicas_dao= new Practicas_DAO(base_deDatos_autoescuela);
        alumnosDAO = new AlumnosDAO(base_deDatos_autoescuela);


        costumDialogClass= new CostumDialogClass(getActivity());
        loadData();
        loadPDF();



        resetar_campos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickResetarCampos();
            }
        });

        seleccionar_nueva_duracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCostumDialog();
            }
        });

        seleccionar_nueva_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarHora();
            }
        });

        seleccionar_nueva_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarFecha();
            }
        });

        seleccionar_nueva_salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarLugar();
            }
        });
        guardar_cambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCliclGuardarDAtos();
            }
        });
        elminar_practica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder aldial = new AlertDialog.Builder(getContext());
                aldial.setMessage("Estas seguro que quiere borrar la practica ?  ").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               onClickElminarPract();
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



        ver_en_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                plantilla_pdf.setFragmentManager(fragmentManager);
                plantilla_pdf.openDocument();
                plantilla_pdf.addMetaData("PRACTICA","DATOS PRACTICA","Alex Apostol");
                Date currentTime = Calendar.getInstance().getTime();
                plantilla_pdf.addTitle("Autoescuela","Practica", currentTime);
                plantilla_pdf.addParafo(shortALU);
                plantilla_pdf.CreateTable(header,getAlumnos());
                plantilla_pdf.addParafo(shortPract);
                plantilla_pdf.addParafo(datos());
                plantilla_pdf.closeDocument();
                onclickViewAppPDF();

            }
        });


       return view;
    }

    private void loadPDF(){

        if (practica!=null
                ){
            hora_salida=practica.getHora_salid();
            data_pract=practica.getData_pract();
            salida_pract=practica.getLugar_practica();
            duracaion_pract=practica.getDuracion();
        }
    }


    private String datos(){

        if (costumDialogClass.getDuracionPract()>0){
           duracaion_pract=String.valueOf(costumDialogClass.getDuracionPract());
        }
        String datos = "Hora salida: "+hora_salida+"\n"+
                "Lugar Salida: "+salida_pract+"\n"+
                "Fecha Pract: "+data_pract+"\n"+
                "Duracion Practica: "+duracaion_pract;
        return datos;
    }

    private void onClickResetarCampos(){
         hora_salida="";
          data_pract="";
         salida_pract="";
         duracaion_pract="";

    }
    private void onClickElminarPract(){
        if (practica!=null){
            if (practicas_dao.eliminar_pract(practica)){
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Agenda_Fragment agenda_fragment = new Agenda_Fragment();
                fragmentManager.popBackStack();

                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout,agenda_fragment ).addToBackStack(null).commit();

                Toast.makeText(getContext(),"PRACTICA BORRADA",Toast.LENGTH_LONG).show();

            }

        } Toast.makeText(getContext(),"ERROR CONTACTE EL SERVICIO TECNICO",Toast.LENGTH_LONG).show();


    }


private  void onChecBoxClicked(View view){

    switch (view.getId()){
        case R.id.checkbox_practica_realziada:
           if (  practica.isRealizada()){
               practica_realizada.setChecked(true);
               /*
               practica.isRealizada();
                practicas_dao.modificarPractica(practica);
             alumnosDAO.decrementar_NR_Practicas(1,alumno); break;
             */
           } else

               practica.setRealizada(false);
           /*
           practicas_dao.modificarPractica(practica);
          alumnosDAO.incrementar_NR_Practicas(1,alumno) ; break;
          */
    }

}
    private void onCliclGuardarDAtos(){




        if (!isEmpty(hora_salida)){

            practica.setHora_salid(hora_salida);
        } if (!isEmpty(data_pract)){

            practica.setData_pract(data_pract);

        }if (salida_pract.length()>0){

            practica.setLugar_practica(salida_pract);
        } if (duracaion_pract.length()>0){

            practica.setDuracion(duracaion_pract);
        }  if (practica_realizada.isChecked()){

            practica.setRealizada(true);
            alumnosDAO.decrementar_NR_Practicas(1,alumno);


        }  if (!practica_realizada.isChecked()){

            practica.setRealizada(false);
            alumnosDAO.incrementar_NR_Practicas(1,alumno);

        }

        if (practicas_dao.modificarPractica(practica)){
            loadData();

            Toast toast = Toast.makeText(getContext(),"CAMBIOS GUARDADOS!",Toast.LENGTH_LONG);
            toast.show();


        } else {
            Toast toast = Toast.makeText(getContext(),"ERROR en modificar datos_practica CONTACTE CON EL SERVICIO TECNICO",Toast.LENGTH_LONG);
            toast.show();

        }
    }



    private void onclickViewAppPDF(){
        plantilla_pdf.viewPDfApp(this.getActivity());
    }
    private ArrayList<String[]> getAlumnos(){
        ArrayList<String[]> rows = new ArrayList<>();

        // falta la restriccion del otro del duracion pract


        rows.add(new String[]{alumno.getNie(),alumno.getNom().toString(),alumno.getCognoms().toString(),alumno.getTipo_carnet().toString()
        });
        /*
    } else{
            Toast toast = Toast.makeText(getContext(),"RELLENA TODOS LOS CAMPOS ",Toast.LENGTH_LONG);
            toast.show();
        }
        */




        return rows;
    }



    private void loadData(){
        if (alumno!=null&&practica!=null){

            tv_Nom_alu.setText(alumno.getNom().toString());
            tv_apellido_alu.setText(alumno.getCognoms());
            tv_lugar_salida.setText(practica.getLugar_practica());
            tv_tipo_carnet.setText(alumno.getTipo_carnet());
            tv_hora_salida.setText(practica.getHora_salid());
            tv_fecha_salida.setText(practica.getData_pract());

            if (practica.isRealizada()){
              practica_realizada.setChecked(true);
            }
            else if (!practica.isRealizada())
             {practica_realizada.setChecked(false);}

        }
    }



    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    private  void  onClickCostumDialog(){
        costumDialogClass.show();
        if (costumDialogClass.getDuracionPract()>0){
           duracaion_pract=String.valueOf(costumDialogClass.getDuracionPract());
        }


    }

    private void onClickSeleccionarHora(){
        Calendar cal1 = Calendar.getInstance();
        int hora = cal1.get(Calendar.HOUR);
        int minuto=cal1.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora_salida=hourOfDay+":"+minute;

                    }
                },hora,minuto,false

        );
        timePickerDialog.show();

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
                data_pract=dayOfMonth+"-"+month+"-"+year;
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

    private  boolean isEmpty(String editText){

        boolean isempty=false;
        if (editText.equals("")){
            isempty=true;
            System.out.println("is empryyyy !");
        }
        return isempty;

    }


    private void onClickSeleccionarLugar(){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(this.getActivity());
            startActivityForResult(intent,PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // recoge la infromacion de la ubucacion seleccionada por el ususaario
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PLACE_PICKER_REQUEST){
            if (resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(getContext(),data);
                String helper[]=place.getAddress().toString().split(",");
                salida_pract=helper[0]+" "+helper[1];


            }
        }
    }
}
