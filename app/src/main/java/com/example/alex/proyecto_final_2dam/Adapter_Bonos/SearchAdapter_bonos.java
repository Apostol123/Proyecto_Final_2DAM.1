package com.example.alex.proyecto_final_2dam.Adapter_Bonos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;
import com.example.alex.proyecto_final_2dam.entidades.Practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Bonos_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Borrar_Bono_fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Modificar_Bono;
import com.example.alex.proyecto_final_2dam.layout_fragments.Practicas.citar_a_practica.modificar_datos_practica.Modificar_datos_practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;

import java.util.List;


class SearchViewHolder_agenda extends RecyclerView.ViewHolder implements View.OnClickListener{


    private Boolean dar_baja=false;
  private Bonos_Fragment bonos_fragment;
     private Alumno alumno;
     private Bono_Practica bono_practica;
     private Boolean borrar_bono = false;
     private boolean bono_sin_alumno=false;


       private boolean modificar_alu=false;
       private boolean pract=false;
       private Agenda_Fragment agenda_fragment;

    // creamos el recicle viee holder para sincronizar los comnentes del layout con el codigo
    public TextView nombre,apellido1,importe,nr_de_pract,fecha;

    public SearchViewHolder_agenda(View itemView) {
        super(itemView);
        importe = (TextView) itemView.findViewById(R.id.tv_imorte_bono_list_view);
        nombre = (TextView) itemView.findViewById(R.id.tvNombre_bono_list_view);
        apellido1 = (TextView) itemView.findViewById(R.id.tvApellido_bono_list_view);
       nr_de_pract = (TextView) itemView.findViewById(R.id.tv_nr_de_practicas_bono_list_view);
      fecha= (TextView) itemView.findViewById(R.id.tv_fecha_bono_list_view);


        itemView.setOnClickListener(this);

    }






    @Override
    public void onClick(View view) {
        if (borrar_bono){
            if (bono_sin_alumno){
                Borrar_Bono_fragment borrar_bono_fragment =  new Borrar_Bono_fragment();
                Bundle bundle = new Bundle();
                bundle.putString("sin_alu","sin_alu");
                borrar_bono_fragment.setArguments(bundle);

                Classe_Estatica_auxiliar.setBono_practica_a_eliminar(bono_practica);
                android.support.v4.app.FragmentManager fragmentManager = bonos_fragment.getActivity().getSupportFragmentManager();


                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, borrar_bono_fragment).addToBackStack(null).commit();

            } else {
                System.out.println("to string en bono adaptaer "+alumno.toString());
                Classe_Estatica_auxiliar.setAlumno_borrar_alumno_bonos(alumno);
                Classe_Estatica_auxiliar.setBono_practica_a_eliminar(bono_practica);
                android.support.v4.app.FragmentManager fragmentManager = bonos_fragment.getActivity().getSupportFragmentManager();


                fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Borrar_Bono_fragment()).addToBackStack(null).commit();
            }


        } else {
            System.out.println("to string en bono adaptaer "+alumno.toString());
            Classe_Estatica_auxiliar.setAlumno_bonos(alumno);
            Classe_Estatica_auxiliar.setBono_practica(bono_practica);

            android.support.v4.app.FragmentManager fragmentManager = bonos_fragment.getActivity().getSupportFragmentManager();


            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Modificar_Bono()).addToBackStack(null).commit();

        }


    }

    public boolean isBono_sin_alumno() {
        return bono_sin_alumno;
    }

    public void setBono_sin_alumno(boolean bono_sin_alumno) {
        this.bono_sin_alumno = bono_sin_alumno;
    }

    public Boolean getBorrar_bono() {
        return borrar_bono;
    }

    public void setBorrar_bono(Boolean borrar_bono) {
        this.borrar_bono = borrar_bono;
    }

    public Bono_Practica getBono_practica() {
        return bono_practica;
    }

    public void setBono_practica(Bono_Practica bono_practica) {
        this.bono_practica = bono_practica;
    }

    public Bonos_Fragment getBonos_fragment() {
        return bonos_fragment;
    }

    public void setBonos_fragment(Bonos_Fragment bonos_fragment) {
        this.bonos_fragment = bonos_fragment;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Agenda_Fragment getAgenda_fragment() {
        return agenda_fragment;
    }

    public void setAgenda_fragment(Agenda_Fragment agenda_fragment) {
        this.agenda_fragment = agenda_fragment;
    }

    public Boolean getDar_baja() {
        return dar_baja;
    }

    public void setDar_baja(Boolean dar_baja) {
        this.dar_baja = dar_baja;
    }

    public boolean isModificar_alu() {
        return modificar_alu;
    }

    public void setModificar_alu(boolean modificar_alu) {
        this.modificar_alu = modificar_alu;
    }

    public boolean isPract() {
        return pract;
    }

    public void setPract(boolean pract) {
        this.pract = pract;
    }
}

public class SearchAdapter_bonos extends RecyclerView.Adapter<SearchViewHolder_agenda> {
    private Context context;
    private List<Bono_Practica> bono_practicas;
    private Bonos_Fragment fragment;

    private boolean practica;

    private AlumnosDAO alumnosDAO;
    private List<String>niees;
    private Boolean borrar_bono =false;



    public SearchAdapter_bonos(Context context, List<Bono_Practica> bonos_practicas,List<String>niees) {
        this.context = context;
        this.bono_practicas = bonos_practicas;
        Base_deDatos_Autoescuela base_deDatos_autoescuela= new Base_deDatos_Autoescuela(Classe_Estatica_auxiliar.getBonos_fragment().getContext());
        alumnosDAO= new AlumnosDAO(base_deDatos_autoescuela);
        this.niees=niees;

    }

    // sincronizamos la la clase con el view xml
    @NonNull
    @Override
    public SearchViewHolder_agenda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.list_view_bonos_alumno,parent,false);

        return new SearchViewHolder_agenda(itemView);
    }

    // le damos valor a los comonentes

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder_agenda holder, int position) {
        System.out.printf("BORRAR BONO "+borrar_bono);

        if (fragment!=null) {
            System.out.println("no es null " + true);
            /*
            holder.setBono_practica(fragment);

            holder.setAlumno(alumnos.get(position));
            holder.setBono_practica(bono_practicas.get(position));
            */

            holder.setBonos_fragment(fragment);
            holder.setAlumno(alumnosDAO.get_Alumno_por_NIE(niees.get(position)));
            holder.setBono_practica(bono_practicas.get(position));


        }


        if (alumnosDAO.get_Alumno_por_NIE(niees.get(position))==null ){

            if (borrar_bono){
                holder.setBorrar_bono(true);
            }
            holder.setBono_sin_alumno(true);

            holder.nombre.setText("Nombre: Alumno Borrado");
            holder.apellido1.setText("Apellidos: Alumno borrado ");
            holder.fecha.setText("Fecha: "+bono_practicas.get(position).getFecha_bono());
            holder.nr_de_pract.setText("Nr Practicas:  "+String.valueOf(bono_practicas.get(position).getCant_practicas()));
            holder.importe.setText("Importe: "+String.valueOf(bono_practicas.get(position).getCantida_dinero())+" €");



        } else {
            if (borrar_bono){
                holder.setBorrar_bono(true);
            }

            holder.nombre.setText("Nombre: " + alumnosDAO.get_Alumno_por_NIE(niees.get(position)).getNom());
            holder.apellido1.setText("Apellidos: " + alumnosDAO.get_Alumno_por_NIE(niees.get(position)).getCognoms());
            holder.fecha.setText("Fecha: " + bono_practicas.get(position).getFecha_bono());
            holder.nr_de_pract.setText("Nr Practicas:  " + String.valueOf(bono_practicas.get(position).getCant_practicas()));
            holder.importe.setText("Importe: " + String.valueOf(bono_practicas.get(position).getCantida_dinero()) + " €");


        }





    }
// le ddecimos o grande que sera la lista
    @Override
    public int getItemCount() {
        return bono_practicas.size();
    }



    public boolean isPractica() {
        return practica;
    }

    public void setPractica(boolean practica) {
        this.practica = practica;
    }

    public Bonos_Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Bonos_Fragment fragment) {
        this.fragment = fragment;
    }

    public Boolean getBorrar_bono() {
        return borrar_bono;
    }

    public void setBorrar_bono(Boolean borrar_bono) {
        this.borrar_bono = borrar_bono;
    }
}

