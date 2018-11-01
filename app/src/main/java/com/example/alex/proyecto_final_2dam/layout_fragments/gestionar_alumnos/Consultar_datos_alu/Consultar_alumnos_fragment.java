package com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import com.example.alex.proyecto_final_2dam.Adapter.SearchAdapter;
import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */



public class Consultar_alumnos_fragment extends Fragment {
    private AlumnosDAO alumnosDAO;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SearchAdapter adapter;


    private MaterialSearchBar materialSearchBar;

    private List<String> suggestList = new ArrayList<>();


    public Consultar_alumnos_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultar_alumnos_fragment, container, false);

        Base_deDatos_Autoescuela base_deDatos_autoescuela = Classe_Estatica_auxiliar.getBase_deDatos_autoescuela_MAIN();

        alumnosDAO = new AlumnosDAO(base_deDatos_autoescuela);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar) view.findViewById(R.id.searchBar);

        // setup search bar
        materialSearchBar.setHint("Buscar");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();
                for (String search : suggestList
                        ) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toString().toLowerCase())) {
                        suggest.add(search);

                    }
                    materialSearchBar.setLastSuggestions(suggest);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) {

                    adapter = new SearchAdapter(getContext(), alumnosDAO.llista_Alu());

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());

            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            //fer un switch
            if (bundle.getString("baja") != null) {
                adapter = new SearchAdapter(getContext(), alumnosDAO.llista_Alu());
                adapter.setBaja(true);
                recyclerView.setAdapter(adapter);
                adapter.setFragment(this);
                return view;
            } else if (bundle.getString("mod") != null) {
                adapter = new SearchAdapter(getContext(), alumnosDAO.llista_Alu());
                adapter.setModificar_datos(true);
                recyclerView.setAdapter(adapter);
                adapter.setFragment(this);
                return view;

            } else  if (bundle.getString("practica")!=null){
                adapter = new SearchAdapter(getContext(), alumnosDAO.llista_Alu());
                adapter.setPractica(true);
                recyclerView.setAdapter(adapter);
                adapter.setFragment(this);
                return view;
            }
            else if (bundle.getString("bono")!=null){
                adapter = new SearchAdapter(getContext(), alumnosDAO.llista_Alu());
                adapter.setBono(true);
                recyclerView.setAdapter(adapter);
                adapter.setFragment(this);
                return view;

            }

        } else
            // init adapter  default set all result
            adapter = new SearchAdapter(getContext(), alumnosDAO.llista_Alu());
             recyclerView.setAdapter(adapter);
            adapter.setFragment(this);



        return view;
    }

    private void startSearch(String text) {
        adapter = new SearchAdapter(this.getContext(), alumnosDAO.get_Alumnos_por_nombre(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = alumnosDAO.get_Alu_names();
        materialSearchBar.setLastSuggestions(suggestList);
    }




}

