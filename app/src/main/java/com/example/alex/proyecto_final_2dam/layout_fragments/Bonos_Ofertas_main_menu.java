package com.example.alex.proyecto_final_2dam.layout_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Bonos_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Borrar_Bono_fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Modificar_Bono;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Nuevo_Bono;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Alumnos_main_menu_Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bonos_Ofertas_main_menu extends Fragment {
   private GridLayout mainGrid;

    public Bonos_Ofertas_main_menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_bonos__ofertas_main_menu,container,false);
       mainGrid = (GridLayout)view.findViewById(R.id.mainGridBono);
       setSingleEvenet(mainGrid);

       return view;
    }

    private void setSingleEvenet(GridLayout mainGrid){
        for (int i = 0;i<mainGrid.getChildCount();i++){
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI==0){
                        android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();


                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,new Nuevo_Bono()).addToBackStack("nuevo_bono").commit();


                    } else if (finalI==1){
                        android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();


                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,new Bonos_Fragment()).addToBackStack("bonos_fragment").commit();


                    } else if (finalI==2){
                        System.out.println("EN EL FINALI 2 ");
                        android.support.v4.app.FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                       Bonos_Fragment bonos_fragment = new Bonos_Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("borrar","borrar");
                        bonos_fragment.setArguments(bundle);
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,bonos_fragment ).addToBackStack("bonos_fragment").commit();




                    }
                }
            });

        }

    }

}
