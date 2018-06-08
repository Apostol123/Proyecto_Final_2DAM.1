package com.example.alex.proyecto_final_2dam.layout_fragments.Bonos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.proyecto_final_2dam.Adapter_Bonos.SearchAdapter_bonos;
import com.example.alex.proyecto_final_2dam.Adapter_agenda.SearchAdapter_agenda;
import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.Bono_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Bono_Practica;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bonos_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SearchAdapter_bonos adapter;
    private MaterialSearchBar materialSearchBar;
    private Bono_DAO bono_dao;
    private  List<String> nies = new ArrayList<>();
    private Map<String,Bono_Practica> bonos = new TreeMap<>();
    private ArrayList<Bono_Practica> bono_practicas = new ArrayList<>();



    public Bonos_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_bonos_,container,false);
      Base_deDatos_Autoescuela base_deDatos_autoescuela = new Base_deDatos_Autoescuela(getContext());
      bono_dao= new Bono_DAO(base_deDatos_autoescuela);

      bono_practicas = bono_dao.ListaBonos();

      loadBonoPracitcas();



      Classe_Estatica_auxiliar.setBonos_fragment(this);

      recyclerView=(RecyclerView)view.findViewById(R.id.recycleView_Bono);
      layoutManager=new LinearLayoutManager(this.getContext());
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setHasFixedSize(true);


      materialSearchBar=(MaterialSearchBar) view.findViewById(R.id.search_Bar_bono_practica);
      materialSearchBar.setHint("Buscar");
      materialSearchBar.setCardViewElevation(10);
      //load suggest list
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled){
                    adapter= new SearchAdapter_bonos(getContext(),bono_dao.ListaBonos(),nies);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        Bundle bundle = getArguments();
        if (bundle!=null){
            System.out.println("bundle is not null ");

            adapter= new SearchAdapter_bonos(getContext(),bono_dao.ListaBonos(),nies);
            adapter.setBorrar_bono(true);
            recyclerView.setAdapter(adapter);
            adapter.setFragment(this);


        } else {
        adapter= new SearchAdapter_bonos(getContext(),bono_dao.ListaBonos(),nies);
        recyclerView.setAdapter(adapter);
        adapter.setFragment(this);
        }

        return view;
    }

    private void loadBonoPracitcas() {
        for (int i = 0; i <bono_practicas.size() ; i++) {
            nies.add(bono_practicas.get(i).getNie_alu().toString());
        }
    }


}





