package com.example.alex.proyecto_final_2dam.Adapter_agenda;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Practica;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.Practicas.citar_a_practica.modificar_datos_practica.Modificar_datos_practica;
import com.example.alex.proyecto_final_2dam.pdf.Plantilla_pdf;

import java.util.ArrayList;
import java.util.List;


class SearchViewHolder_agenda extends RecyclerView.ViewHolder implements View.OnClickListener{


    private Boolean dar_baja=false;
    private Practica practica;
     private Alumno alumno;

       private boolean modificar_alu=false;
       private boolean pract=false;
       private Agenda_Fragment agenda_fragment;

    // creamos el recicle viee holder para sincronizar los comnentes del layout con el codigo
    public TextView nombre,apellido1,tipo_carnet,hora_lugar_salida,lugar_salida;
    public SearchViewHolder_agenda(View itemView) {
        super(itemView);
        lugar_salida = (TextView) itemView.findViewById(R.id.tv_lugar_practica_list_view);
        nombre = (TextView) itemView.findViewById(R.id.tvNombre_practica_list_view);
        apellido1 = (TextView) itemView.findViewById(R.id.tvApellido_practica_list_view);
        tipo_carnet = (TextView) itemView.findViewById(R.id.tvtipo_carnet_practica_list_view);
        hora_lugar_salida = (TextView) itemView.findViewById(R.id.tvhora_practica_list_view);


        itemView.setOnClickListener(this);

    }






    @Override
    public void onClick(View view) {

        Modificar_datos_practica modificar_datos_practica = new Modificar_datos_practica();
        modificar_datos_practica.setAlumno(alumno);
        modificar_datos_practica.setPractica(practica);

        android.support.v4.app.FragmentManager fragmentManager = Classe_Estatica_auxiliar.getAgenda_fragment().getActivity().getSupportFragmentManager();


        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, modificar_datos_practica).addToBackStack(null).commit();




    }


    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
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

public class SearchAdapter_agenda extends RecyclerView.Adapter<SearchViewHolder_agenda> {
    private Context context;
    private List<Practica> practicas;
    private Agenda_Fragment fragment;
    private boolean baja;
    private boolean modificar_datos;
    private boolean practica;
    private  List<Alumno>alumnos;

    public List<Practica> getPracticas() {
        return practicas;
    }

    public SearchAdapter_agenda(Context context, List<Practica> practicas,List<Alumno>alumnos) {
        this.context = context;
        this.practicas = practicas;
        this.alumnos=alumnos;
    }

    // sincronizamos la la clase con el view xml
    @NonNull
    @Override
    public SearchViewHolder_agenda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.list_view_practica_agenda,parent,false);

        return new SearchViewHolder_agenda(itemView);
    }

    // le damos valor a los comonentes

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder_agenda holder, int position) {

        if (Classe_Estatica_auxiliar.getAgenda_fragment()!=null) {
            System.out.println("no es null " + true);
            holder.setAgenda_fragment(fragment);

            holder.setAlumno(alumnos.get(position));
            holder.setPractica(practicas.get(position));
        }

        holder.nombre.setText("Nombre: "+alumnos.get(position).getNom());
        holder.apellido1.setText("Apellidos: "+alumnos.get(position).getCognoms());
        holder.tipo_carnet.setText("Tipo Carnet "+alumnos.get(position).getTipo_carnet());
        holder.hora_lugar_salida.setText("Hora  salida: "+practicas.get(position).getHora_salid());
        holder.lugar_salida.setText("Lugar Salida: "+practicas.get(position).getLugar_practica());





    }
// le ddecimos o grande que sera la lista
    @Override
    public int getItemCount() {
        return practicas.size();
    }

    public Agenda_Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Agenda_Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isPractica() {
        return practica;
    }

    public void setPractica(boolean practica) {
        this.practica = practica;
    }
}
