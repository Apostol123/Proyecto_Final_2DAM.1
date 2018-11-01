package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Alumnos_main_menu_Fragment extends Fragment {
    private GridLayout gridLayout;

    public Alumnos_main_menu_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alumnos_main_menu_, container, false);
        gridLayout = (GridLayout) view.findViewById(R.id.Grid_layour_reigstrar_alumnos);

        setSingleEvent(gridLayout);
        return view;
    }

    private void setSingleEvent(GridLayout mainGrid) {
        // lop all child items
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) {
                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        Registrar_Alumnos_Fragment registrar_alumnos_fragment = new  Registrar_Alumnos_Fragment();
                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, registrar_alumnos_fragment,"alta_alu").addToBackStack(null).commit();

                    } else if(finalI==1){
                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();


                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Consultar_alumnos_fragment(),"consultar_datos").addToBackStack(null).commit();

                    }else if (finalI == 3) {
                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
                        Bundle bundle= new Bundle();
                        bundle.putString("baja","baja");
                        consultar_alumnos_fragment.setArguments(bundle);

                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment ).addToBackStack("lista_alu").commit();

                    } else if (finalI == 2){
                        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
                        Bundle bundle= new Bundle();
                        bundle.putString("mod","mod");
                        consultar_alumnos_fragment.setArguments(bundle);

                        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment ).addToBackStack("lista_alu").commit();

                    }

                }
            });
        }
    }
}


