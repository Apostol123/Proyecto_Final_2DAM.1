package com.example.alex.proyecto_final_2dam.layout_fragments.Bonos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.dao.Bono_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;

/**
 * A simple {@link Fragment} subclass.
 */
public class Borrar_Bono_fragment extends Fragment {
    private TextView fecha_bono_eliminado;
    private TextView importe_bono_eliminado;
    private TextView nr_de_pract_bono_eliminado;
    private TextView nombre_alu_bono_eliminado;
    private TextView apellido_bono_eliminado;
    private Button btn_elimnar_bono;
    private final String info ="ERROR";
    private Alumno alumno;
    private Bono_Practica bono_practica;
    private Bono_DAO bono_dao;
    private Bundle bundle;
    private AlumnosDAO alumnosDAO;


    public Borrar_Bono_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_borrar__bono_fragment,container,false);
        fecha_bono_eliminado=(TextView)view.findViewById(R.id.fecha_bono_a_eliminar);
        importe_bono_eliminado=(TextView)view.findViewById(R.id.importe_bono_a_eliminar);
       nr_de_pract_bono_eliminado=(TextView)view.findViewById(R.id.nr_de_pract_bono_a_eliminar);
       nombre_alu_bono_eliminado=(TextView)view.findViewById(R.id.nombre_alumno_bono_a_eliminar);
       apellido_bono_eliminado=(TextView)view.findViewById(R.id.apeliido_alumno_bono_a_eliminar);
        btn_elimnar_bono=(Button)view.findViewById(R.id.btn_eliminar_bono);

        Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());
        bono_dao = new Bono_DAO(base_deDatos_autoescuela);

       bundle= getArguments();
       alumnosDAO= new AlumnosDAO(base_deDatos_autoescuela);

       if (loadEntidades()){
           inicializarDatos();
           btn_elimnar_bono.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   AlertDialog.Builder aldial = new AlertDialog.Builder(getContext());
                   aldial.setMessage("Estas seguro/@ que quiere borrar el bono nr "+bono_practica.getId())
                           .setCancelable(false).setPositiveButton("SI", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           onClickBorrarBono();
                           android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                           Bonos_Fragment bonos_fragment = new Bonos_Fragment();
                           Bundle bundle = new Bundle();
                           bundle.putString("borrar","borrar");
                           bonos_fragment .setArguments(bundle);
                           fragmentManager.popBackStack();
                           fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,bonos_fragment ).addToBackStack("BONO ALU").commit();

                       }
                   }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           retroceder();

                       }
                   });
                   AlertDialog alertDialog = aldial.create();
                   alertDialog.setTitle("CONFIRAMCIÓN");
                   alertDialog.show();

               }
           });


       }


        return view;

    }

    private void retroceder(){
        android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
        Bonos_Fragment bonos_fragment = new Bonos_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("borrar","borrar");
        bonos_fragment .setArguments(bundle);
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,bonos_fragment ).addToBackStack("BONO ALU").commit();


    }

    private void inicializarDatos(){
            if (bundle!=null){

            fecha_bono_eliminado.setText("Fecha: "+bono_practica.getFecha_bono());
            nombre_alu_bono_eliminado.setText("Nombre: "+info);
            apellido_bono_eliminado.setText("Apellidos: "+info);
            nr_de_pract_bono_eliminado.setText("Practicas bono : "+String .valueOf(bono_practica.getCant_practicas()));
            importe_bono_eliminado.setText("Importe bono : "+String .valueOf(bono_practica.getCantida_dinero()+" €"));
            }

            else {
                fecha_bono_eliminado.setText("Fecha: "+bono_practica.getFecha_bono());
                nombre_alu_bono_eliminado.setText("Nombre: "+alumno.getNom());
                apellido_bono_eliminado.setText("Apellidos: "+alumno.getCognoms());
                nr_de_pract_bono_eliminado.setText("Practicas bono : "+String .valueOf(bono_practica.getCant_practicas()));
                importe_bono_eliminado.setText("Importe bono : "+String .valueOf(bono_practica.getCantida_dinero()+" €"));}




    }
    private void onClickBorrarBono(){
        if (bundle!=null){
            if (bono_dao.borrar_bono(bono_practica)){

                Toast.makeText(getContext(),"BONO ELIMINADO",Toast.LENGTH_LONG).show();
                retroceder();

            }else {
                Toast.makeText(getContext(),"ERROR BORRANDO EL BONO CONTACTE CON EL SERIVICO TECNICO",Toast.LENGTH_LONG).show();
                retroceder();


            }

        } else {
            if (bono_dao.borrar_bono(bono_practica)) {
                System.out.println(alumno.toString());
                int nr_pract = alumno.getNr_practicas() - bono_practica.getCant_practicas();
                alumno.setNr_practicas(nr_pract);
                alumno.setAcuenta_matricula(alumno.getAcuenta_matricula() - bono_practica.getCantida_dinero());
                alumnosDAO.modificarDatos(alumno);
                Toast.makeText(getContext(), "BONO ELIMINADO", Toast.LENGTH_LONG).show();
                retroceder();

            } else {
                Toast.makeText(getContext(), "ERROR BORRANDO EL BONO CONTACTE CON EL SERIVICO TECNICO", Toast.LENGTH_LONG).show();
                retroceder();


            }
        }

    }
    private boolean loadEntidades(){
        boolean cargado = false;


        try {
            if (bundle!= null){
                String info = "BONO SIN ALUMNO ";
                fecha_bono_eliminado.setText(info);
                nombre_alu_bono_eliminado.setText(info);
                nr_de_pract_bono_eliminado.setText(info);
                importe_bono_eliminado.setText(info);
                bono_practica=Classe_Estatica_auxiliar.getBono_practica_a_eliminar();
                cargado=true;

            } else {
            alumno=Classe_Estatica_auxiliar.getAlumno_borrar_alumno_bonos();
                System.out.println(alumno.toString());
            bono_practica=Classe_Estatica_auxiliar.getBono_practica_a_eliminar();
            if (alumno!=null&&bono_practica!=null){
                cargado=true;
            }else {
               fecha_bono_eliminado.setText(info);
                nombre_alu_bono_eliminado.setText(info);
                nr_de_pract_bono_eliminado.setText(info);
                importe_bono_eliminado.setText(info);

            }
            }

        }catch (Exception e){
            System.out.println(e.toString());
            Toast.makeText(getContext(),"ERROR CARGADO LOS ALUMNOS CONTACTE CON EL SERVICIO TECNICO",Toast.LENGTH_LONG).show();

        }

        return cargado;
    }

}
