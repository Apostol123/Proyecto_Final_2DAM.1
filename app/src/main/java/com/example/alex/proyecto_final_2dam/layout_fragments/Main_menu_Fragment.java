package com.example.alex.proyecto_final_2dam.layout_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Alumnos_main_menu_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main_menu_Fragment extends Fragment {
    private GridLayout mainGrid;
    public Main_menu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main_menu_,container,false);
       mainGrid = (GridLayout)view.findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
       return view;
    }

    private void setSingleEvent(GridLayout mainGrid){
        // lop all child items
        for (int i = 0 ;i<mainGrid.getChildCount();i++){
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (finalI==0){
                       android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();


                       fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,new Alumnos_main_menu_Fragment()).addToBackStack("main_menu_alu").commit();

                   }else if(finalI==2){
                       android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                            Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("practica","practica");
                            consultar_alumnos_fragment.setArguments(bundle);

                       fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment).addToBackStack("main_menu_alu").commit();


                   }
                   else if(finalI==3){
                       android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                       fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,new Bonos_Ofertas_main_menu()).addToBackStack(null).commit();

                   }else if(finalI==1){
                       android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                       fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,new Agenda_Fragment()).addToBackStack(null).commit();


                   }

                }
            });
        }


    }

}
