package com.example.alex.proyecto_final_2dam.layout_fragments.Practicas.citar_a_practica;


import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.Auxiliar.CostumDialogClass;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.dao.Practicas_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Practica;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class citar_a_pract_prfile extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private Alumno alumno;
    private AlumnosDAO alumnosDAO;
    private Practicas_DAO practicas_dao;
    private Practica practica ;
        private Button btn_seleccionar_lugar;
        private Button btn_seleccionar_fecha;
        private Button btn_seleccionar_hora;
        private Button btn_seleccionar_duracion_pract;
        private Button btn_revisar_daots;
        private Button btn_confirmar_cita;
        private Button btn_notficiar_alu;
        private final static int MY_PERMISSION_FINE_LOCATION=101;
        private final static  int PLACE_PICKER_REQUEST=1;
    private GoogleApiClient mGoogleApiClient;



    CostumDialogClass costumDialogClass;

    private String[] header = {"Nie","Nombre","Apellidos","Tipo Carnet"};
    private String  shortALU="DATOS ALUMNO";
    private String  shortPract="DATOS PRACTICA";
    private String hora_salida="";
    private String lugar_salida="";
    private String fecha_pract="";
    private String duracion_pract="";

        private DatePickerDialog.OnDateSetListener onDateSetListener;

        private TextView tv_nom;
        private TextView tv_cognoms;
        private TextView tv_nif;
        private TextView tv_tipo_carnet;
        private TextView tv_nr_pract_hechas;
        private TextView tv_nr_practicas;
         private Plantilla_pdf plantilla_pdf;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
    public citar_a_pract_prfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citar_a_pract_prfile,container,false);
        btn_seleccionar_lugar = (Button)view.findViewById(R.id.btn_selecciona_lugar_pract);
        tv_nif=(TextView)view.findViewById(R.id.tv__pract_nie);
        tv_cognoms=(TextView)view.findViewById(R.id.tv__pract_apellido);
        tv_nom=(TextView)view.findViewById(R.id.tv__pract_Nom);
        tv_nr_practicas=(TextView)view.findViewById(R.id.tv__pract_nr_pract);
        tv_tipo_carnet=(TextView)view.findViewById(R.id.tv__pract_tipo_carnet);
        btn_seleccionar_fecha=(Button)view.findViewById(R.id.bt_seleccion_fecha);
        btn_seleccionar_hora = (Button)view.findViewById(R.id.bt_seleccion_hora);
        btn_seleccionar_duracion_pract = (Button)view.findViewById(R.id.btn_selecciona_duracion_pract);
        btn_revisar_daots=(Button)view.findViewById(R.id.btn_revisar_datos);
        btn_confirmar_cita=(Button)view.findViewById(R.id.btn_confirmar_cita);
        tv_nr_pract_hechas=(TextView)view.findViewById(R.id.tv_nr_de_practicas_hechas);
        btn_notficiar_alu=(Button)view.findViewById(R.id.notificar_Alu) ;
        costumDialogClass= new CostumDialogClass(getActivity());





        practicas_dao=new Practicas_DAO(Classe_Estatica_auxiliar.getBase_deDatos_autoescuela_MAIN());
        alumnosDAO=new AlumnosDAO(Classe_Estatica_auxiliar.getBase_deDatos_autoescuela_MAIN());

        plantilla_pdf= new Plantilla_pdf(this.getContext());
//        plantilla_pdf.CreateTable(header,getAlumnos());

        load_Data();
        requestPermsion();
       // requestPermsionWriteeXTERNARsTORAGE();
        //git clone https://github.com/googlesamples/google-services.gitrequestPermsionReadEXTERNARsTORAGE();








        btn_seleccionar_duracion_pract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarDuracionPract();
            }
        });

        btn_seleccionar_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarFecha();
            }
        });
        btn_seleccionar_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarHora();
            }
        });

        btn_seleccionar_lugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSeleccionarLugar();
            }
        });
        btn_confirmar_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickConfirmarcita();

            }
        });
        btn_notficiar_alu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onCLickNotificarALu();
            }
        });


        btn_revisar_daots.setOnClickListener(new View.OnClickListener() {
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

    private String datos(){
        if (costumDialogClass.getDuracionPract()>0){
            duracion_pract=String.valueOf(costumDialogClass.getDuracionPract());
        }
        String datos = "Hora salida: "+hora_salida+"\n"+
                "Lugar Salida: "+lugar_salida+"\n"+
                "Fecha Pract: "+fecha_pract+"\n"+
                "Duracion Practica: "+duracion_pract;
        return datos;
    }

    private void onClickSeleccionarLugar(){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(Classe_Estatica_auxiliar.getMainActivity());
            startActivityForResult(intent,PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }


    }

    private void onCLickNotificarALu(){
        if (camposCompletos()){

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);

            String text = "Practica \n"+"Hora salida: "+hora_salida+"\n"+"Lugar: "+lugar_salida+"\n"+"Fecha: "+fecha_pract+"\n"+"Duración: "+duracion_pract+"\n";

            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");
            // Put this line here
            sendIntent.setPackage("com.whatsapp");
            //
            startActivity(sendIntent);

        }

    }
    private void requestPermsionWriteeXTERNARsTORAGE(){
        // look in the manieft and check if its not grant aask the user to grant it
        if (ActivityCompat.checkSelfPermission(this.getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_FINE_LOCATION);
        }
    }
    private void requestPermsionReadEXTERNARsTORAGE(){
        // look in the manieft and check if its not grant aask the user to grant it
        if (ActivityCompat.checkSelfPermission(this.getContext(),Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_FINE_LOCATION);
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

    private void onClickSeleccionarDuracionPract(){

        costumDialogClass.show();
        if (costumDialogClass.getDuracionPract()>0){
            duracion_pract=String.valueOf(costumDialogClass.getDuracionPract());
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
        final int mes = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        onDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;


                fecha_pract=dayOfMonth+"-"+month+"-"+year;
                System.out.println("fehch e citar a practca "+fecha_pract);
                if(control_citas_Practicas()){
                    fecha_pract=dayOfMonth+"-"+month+"-"+year;
                } else
                    fecha_pract="";
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

    private void requestPermsion(){
        // look in the manieft and check if its not grant aask the user to grant it
            if (ActivityCompat.checkSelfPermission(this.getContext(),Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
                }
                }
    }

    private void load_Data() {
        if (alumno != null) {
            tv_nif.setText("NIE: " + alumno.getNie());
            tv_nom.setText("NOMBRE: " + alumno.getNom());
            tv_cognoms.setText("APELLIDOS: " + alumno.getCognoms());
            tv_tipo_carnet.setText("TIPO CARNET: " + alumno.getTipo_carnet());
            tv_nr_practicas.setText("NR PRACTICAS: " + String.valueOf(alumno.getNr_practicas()));
            tv_nr_pract_hechas.setText("PRACTICAS HECAS: "+practicas_dao.getPracticasHechasDeAlu(alumno));
        }
    }

    // metodo que vcompreua si se le ha otorgado el los permisos s no se cierra la app
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast toast = Toast.makeText(getContext(),"ESTA APLICACION NECESITA PERMISO DE LOCALIZACION ",Toast.LENGTH_LONG);
                    toast.show();
                    try {
                        finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }

                }

                break;
        }



    }

    private void onClickConfirmarcita(){
        if (camposCompletos()){
            practica=new Practica();
            practica.setData_pract(fecha_pract);
            practica.setDuracion(duracion_pract);
            practica.setHora_salid(hora_salida);
            practica.setLugar_practica(lugar_salida);
            practica.setNiew_alu(alumno.getNie());
            System.out.println("FECHA DE PRACTICA ANTES DE GRUARDARLA  "+practica.getData_pract());
            if (alumno.getNr_practicas()>0){
                if (practicas_dao.addPractica(alumno,practica)){
                    Toast toast = Toast.makeText(getContext(),"Practica Insertada",Toast.LENGTH_LONG);
                    toast.show();
                }

            } else
            {
                Toast toast = Toast.makeText(getContext(),"El alumno no tiene  más practicas disponibles  ",Toast.LENGTH_LONG);
                toast.show();
            }



        }else{Toast toast = Toast.makeText(getContext(),"RELLENA TODOS LOS CAMPOS ",Toast.LENGTH_LONG);
        toast.show();};


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // recoge la infromacion de la ubucacion seleccionada por el ususaario
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PLACE_PICKER_REQUEST){
            if (resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(this.getContext(),data);
                /*
                String helper[]=place.getAddress().toString().split(",");
                lugar_salida=helper[0];
                */
                lugar_salida=place.getAddress().toString();


            }
        }
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    private boolean camposCompletos(){
        boolean completos=false;

        if (costumDialogClass.getDuracionPract()>0){
            duracion_pract=String.valueOf(costumDialogClass.getDuracionPract());
        }
        if (hora_salida.length()>0&&lugar_salida.length()>0&&fecha_pract.length()>0&&duracion_pract.length()>0){
            completos=true;
        } else {  Toast.makeText(getContext(),"RELLENA TODOS LOS CAMPOS",Toast.LENGTH_LONG).show();}

        return completos;

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private boolean control_citas_Practicas(){
        Date date1 = Calendar.getInstance().getTime();
        Boolean correcte = false;


        String current_date=dateFormatForMonth.format(date1).toString();
        try {
            Date current_date1 = dateFormatForMonth.parse(current_date);
            Date selected_date =dateFormatForMonth.parse(fecha_pract);
            if (current_date1.after(selected_date)){
                Toast.makeText(getContext(),"NO SE PUEDE CIATAR EN UNA FECHA INFORIOR A LA ACUTAL ",Toast.LENGTH_LONG).show();
                fecha_pract="";
            } else{
                correcte = true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  correcte;
    }
}
