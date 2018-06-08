package com.example.alex.proyecto_final_2dam.Adapter;
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
import com.example.alex.proyecto_final_2dam.layout_fragments.Practicas.citar_a_practica.citar_a_pract_prfile;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Profile_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Dar_de_baja_Alu.Profile_Fragment_dar_de_baja;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Modificar_datos_alu.Profile_Fragment_modificar_datos;

import java.util.List;


class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    private Boolean dar_baja=false;
       private  List<Alumno> alumnos;
       private Consultar_alumnos_fragment consultar_alumnos_fragment;
       private boolean modificar_alu=false;
       private boolean pract=false;
       private boolean bono=false;

    // creamos el recicle viee holder para sincronizar los comnentes del layout con el codigo
    public TextView nombre,apellido1,telefono,direccion,tipo_carnet;
    public SearchViewHolder(View itemView) {
        super(itemView);
        nombre=(TextView)itemView.findViewById(R.id.tvNombre);
        apellido1=(TextView)itemView.findViewById(R.id.tvApellido);
        telefono=(TextView)itemView.findViewById(R.id.tvtelf);
        direccion=(TextView)itemView.findViewById(R.id.tv_Direccion);
        tipo_carnet=(TextView)itemView.findViewById(R.id.tvtipo_carnet);
        itemView.setOnClickListener(this);




    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Consultar_alumnos_fragment getConsultar_alumnos_fragment() {
        return consultar_alumnos_fragment;
    }

    public void setConsultar_alumnos_fragment(Consultar_alumnos_fragment consultar_alumnos_fragment) {
        this.consultar_alumnos_fragment = consultar_alumnos_fragment;
    }



    @Override
    public void onClick(View view) {
        if (dar_baja){
            android.support.v4.app.FragmentManager fragmentManager = consultar_alumnos_fragment.getActivity().getSupportFragmentManager();

           Profile_Fragment_dar_de_baja profile_fragment = new Profile_Fragment_dar_de_baja();
            profile_fragment.setAlumno(alumnos.get(getAdapterPosition()));
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, profile_fragment).addToBackStack(null).commit();
            System.out.println(alumnos.get(getAdapterPosition()).getNom());

        } else if (modificar_alu){
            android.support.v4.app.FragmentManager fragmentManager = consultar_alumnos_fragment.getActivity().getSupportFragmentManager();

           Profile_Fragment_modificar_datos profile_fragment_dar_de_baja = new Profile_Fragment_modificar_datos();
            profile_fragment_dar_de_baja.setAlumno(alumnos.get(getAdapterPosition()));
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, profile_fragment_dar_de_baja).addToBackStack(null).commit();
            System.out.println(alumnos.get(getAdapterPosition()).getNom());

        } else  if (pract){
            android.support.v4.app.FragmentManager fragmentManager = consultar_alumnos_fragment.getActivity().getSupportFragmentManager();

            citar_a_pract_prfile citar_a_pract_prfile = new citar_a_pract_prfile();
           citar_a_pract_prfile.setAlumno(alumnos.get(getAdapterPosition()));
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, citar_a_pract_prfile).addToBackStack(null).commit();
            System.out.println(alumnos.get(getAdapterPosition()).getNom());


        } else if (bono){
            Classe_Estatica_auxiliar.setAlumno_bonos(alumnos.get(getAdapterPosition()));
            android.support.v4.app.FragmentManager fragmentManager = consultar_alumnos_fragment.getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();

        }else {

            android.support.v4.app.FragmentManager fragmentManager = consultar_alumnos_fragment.getActivity().getSupportFragmentManager();

            Profile_Fragment profile_fragment = new Profile_Fragment();
            profile_fragment.setAlumno(alumnos.get(getAdapterPosition()));
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, profile_fragment).addToBackStack(null).commit();
            System.out.println(alumnos.get(getAdapterPosition()).getNom());
        }

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

    public boolean isBono() {
        return bono;
    }

    public void setBono(boolean bono) {
        this.bono = bono;
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private Context context;
    private List<Alumno> alumnos;
    private Consultar_alumnos_fragment fragment;
    private boolean baja;
    private boolean modificar_datos;
    private boolean practica;
    private boolean bono;


    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public SearchAdapter(Context context, List<Alumno> alumnos) {
        this.context = context;
        this.alumnos = alumnos;
    }

    // sincronizamos la la clase con el view xml
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.list_view_consultar_datos_alumno,parent,false);

        return new SearchViewHolder(itemView);
    }

    // le damos valor a los comonentes

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.setConsultar_alumnos_fragment(fragment);
        holder.setAlumnos(alumnos);
        if (baja){
            holder.setDar_baja(true);
            holder.nombre.setText("Nombre: "+alumnos.get(position).getNom());
            holder.apellido1.setText("Apellidos: "+alumnos.get(position).getCognoms());
            holder.tipo_carnet.setText("Tipo Carnet "+alumnos.get(position).getTipo_carnet());
            holder.telefono.setText("TLF: "+alumnos.get(position).getTelefono());
            holder.direccion.setText("Direccion: "+alumnos.get(position).getDireccion());

        } else  if (modificar_datos){
            holder.setModificar_alu(true);
            holder.nombre.setText("Nombre: "+alumnos.get(position).getNom());
            holder.apellido1.setText("Apellidos: "+alumnos.get(position).getCognoms());
            holder.tipo_carnet.setText("Tipo Carnet "+alumnos.get(position).getTipo_carnet());
            holder.telefono.setText("TLF: "+alumnos.get(position).getTelefono());
            holder.direccion.setText("Direccion: "+alumnos.get(position).getDireccion());

        } else if (practica){
            holder.setPract(true);
            holder.nombre.setText("Nombre: "+alumnos.get(position).getNom());
            holder.apellido1.setText("Apellidos: "+alumnos.get(position).getCognoms());
            holder.tipo_carnet.setText("Tipo Carnet "+alumnos.get(position).getTipo_carnet());
            holder.telefono.setText("TLF: "+alumnos.get(position).getTelefono());
            holder.direccion.setText("Direccion: "+alumnos.get(position).getDireccion());

        }else  if (bono){
            holder.setBono(true);
            holder.nombre.setText("Nombre: "+alumnos.get(position).getNom());
            holder.apellido1.setText("Apellidos: "+alumnos.get(position).getCognoms());
            holder.tipo_carnet.setText("Tipo Carnet "+alumnos.get(position).getTipo_carnet());
            holder.telefono.setText("TLF: "+alumnos.get(position).getTelefono());
            holder.direccion.setText("Direccion: "+alumnos.get(position).getDireccion());

        }else

        holder.nombre.setText("Nombre: "+alumnos.get(position).getNom());
        holder.apellido1.setText("Apellidos: "+alumnos.get(position).getCognoms());
        holder.tipo_carnet.setText("Tipo Carnet "+alumnos.get(position).getTipo_carnet());
        holder.telefono.setText("TLF: "+alumnos.get(position).getTelefono());
        holder.direccion.setText("Direccion: "+alumnos.get(position).getDireccion());





    }
// le ddecimos o grande que sera la lista
    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public Consultar_alumnos_fragment getFragment() {
        return fragment;
    }

    public void setFragment(Consultar_alumnos_fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    public boolean isModificar_datos() {
        return modificar_datos;
    }

    public void setModificar_datos(boolean modificar_datos) {
        this.modificar_datos = modificar_datos;
    }

    public boolean isPractica() {
        return practica;
    }

    public void setPractica(boolean practica) {
        this.practica = practica;
    }

    public boolean isBono() {
        return bono;
    }

    public void setBono(boolean bono) {
        this.bono = bono;
    }
}
