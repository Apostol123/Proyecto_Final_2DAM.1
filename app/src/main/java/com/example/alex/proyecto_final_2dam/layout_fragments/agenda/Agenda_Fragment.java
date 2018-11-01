package com.example.alex.proyecto_final_2dam.layout_fragments.agenda;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.alex.proyecto_final_2dam.Adapter.SearchAdapter;
import com.example.alex.proyecto_final_2dam.Adapter_agenda.SearchAdapter_agenda;
import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.dao.AlumnosDAO;
import com.example.alex.proyecto_final_2dam.dao.Practicas_DAO;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.entidades.Alumno;
import com.example.alex.proyecto_final_2dam.entidades.Practica;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Agenda_Fragment extends Fragment {
    private Practicas_DAO practicas_dao;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SearchAdapter_agenda adapter;
    private MaterialSearchBar materialSearchBar;
    private AlumnosDAO alumnosDAO;
    private ArrayList<Practica> practicas_de_ese_dia=new ArrayList<>();
    private ArrayList<Alumno> alumnos_de_con_practicas_de_ese_dia=new ArrayList<>();
    private String MAinDAte;
    ArrayList<Practica> practicas;







    public Agenda_Fragment() {
        // Required empty public constructor
    }
    private android.support.v7.widget.Toolbar toolbar;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());

   private  CompactCalendarView compactCalendarView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agenda_,container,false);

            toolbar = (android.support.v7.widget.Toolbar)view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        Classe_Estatica_auxiliar.setAgenda_fragment(this);

        practicas_dao=new Practicas_DAO(Classe_Estatica_auxiliar.getBase_deDatos_autoescuela_MAIN());

        alumnosDAO=new AlumnosDAO(Classe_Estatica_auxiliar.getBase_deDatos_autoescuela_MAIN());

        practicas=practicas_dao.getPraacticas();
            /*
        Date date1 = Calendar.getInstance().getTime();


        String selected_date=dateFormatForMonth.format(date1).toString();

        System.out.println("FIRST DATE TO STRING "+selected_date);
        loadPracticas(selected_date);

        Poluate_Day_with_practicas poluate_day_with_practicas = new Poluate_Day_with_practicas();
        poluate_day_with_practicas.execute();
        */





        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView_Agenda);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar) view.findViewById(R.id.searchBar_agenda_practica);


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
                if (!enabled) {
                    adapter = new SearchAdapter_agenda(getContext(),practicas_de_ese_dia,alumnos_de_con_practicas_de_ese_dia);
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

        compactCalendarView=(CompactCalendarView)view.findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        Populate_Agenda_with_practicas  populate_agenda_with_practicas= new Populate_Agenda_with_practicas();
        populate_agenda_with_practicas.execute();


        actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

       // actionBar.setTitle(dateFormatForMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                String selected_date=dateFormatForMonth.format(dateClicked).toString();
                System.out.println("DAte clicked "+selected_date);

                MAinDAte=selected_date;

                startSearch(selected_date);


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));



            }
        });

        adapter = new SearchAdapter_agenda(getContext(),practicas_de_ese_dia,alumnos_de_con_practicas_de_ese_dia);
        recyclerView.setAdapter(adapter);




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter = new SearchAdapter_agenda(getContext(),practicas_de_ese_dia,alumnos_de_con_practicas_de_ese_dia);
        recyclerView.setAdapter(adapter);

    }

    private void startSearch(String text) {
        System.out.println("SELECTED DATE "+text);
            loadPracticas(text);
        System.out.println("TAMAÑO DE LAS PRACTICCAS DE ESE DIA "+practicas_de_ese_dia.size());

        //System.out.println("LUGAR DE PRACTICA "+practicas_de_ese_dia.get(0).getLugar_practica());
        Poluate_Day_with_practicas poluate_day_with_practicas = new Poluate_Day_with_practicas();
        poluate_day_with_practicas.execute();
        adapter = new SearchAdapter_agenda(this.getContext(), practicas_de_ese_dia,alumnos_de_con_practicas_de_ese_dia);
        recyclerView.setAdapter(adapter);
        adapter.setFragment(this);
    }





    private class Poluate_Day_with_practicas extends AsyncTask<Void,Integer,Boolean>{

        public Poluate_Day_with_practicas() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // lo que se hace antes de inciciar la tarea
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            super.onPostExecute(resultado);
            if (resultado){
                Toast.makeText(getContext(), "Carga Correcta",Toast.LENGTH_SHORT).show();
            }
            // lo que se ejecuta justo despies de acabar la tarea
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getContext(), "Carga Cancelada",Toast.LENGTH_SHORT).show();

        }



        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean correcto=false;





            if (practicas_de_ese_dia!=null){

                System.out.println("size del practiacs de ese dia en el poluate day with practicas "+practicas_de_ese_dia.size());
                correcto=true;
                for (int i = 0 ; i<practicas_de_ese_dia.size();i++) {

                    alumnos_de_con_practicas_de_ese_dia.add(alumnosDAO.get_Alumno_por_NIE(practicas_de_ese_dia.get(i).getNiew_alu()));
                    System.out.println("NIE ALU " + practicas_de_ese_dia.get(i).getNiew_alu().toString());
                }
                if (isCancelled()){
                    correcto=false;
                }
            }

            // lo que se hace durante la tarea
            return correcto;
        }
    }

    private void loadPracticas(String date){


        practicas_de_ese_dia =practicas_dao.getPraacticaByDate(date);
        System.out.println("tamaño de las practicas de ese del metodo loadPracticas"+practicas_dao.getPraacticaByDate(date));
    }



    private class Populate_Agenda_with_practicas extends AsyncTask<Void,Integer,Boolean>{

        public Populate_Agenda_with_practicas() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // lo que se hace antes de inciciar la tarea
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            super.onPostExecute(resultado);
            if (resultado){
                Toast.makeText(getContext(), "Carga Correcta",Toast.LENGTH_SHORT).show();
            }
            // lo que se ejecuta justo despies de acabar la tarea
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getContext(), "Carga Cancelada",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
          //  final Event ev1 = new Event(Color.RED,date.getTime(),"My EVENT");

            boolean correcto=false;
            if (practicas!=null){
                System.out.println("size de todas las pracitcas "+practicas.size());

                correcto=true;
                for (int i = 0 ; i<practicas.size();i++) {
                    try {

                        Date date = dateFormatForMonth.parse(practicas.get(i).getData_pract());
                        System.out.println("Date en el aasync de pulate "+date);
                        String date2 = dateFormatForMonth.format(date);
                        System.out.println("date 2 "+date2);
                        System.out.println("Main DAte "+MAinDAte);



                        Date date1 = Calendar.getInstance().getTime();



                        String current_date=dateFormatForMonth.format(date1).toString();
                        Event event = new Event(Color.BLACK,date.getTime(),"PRACTICA");

                        try {

                            // Cojo la fecha acutal
                            // compruebo si la fecha de la practia es inferior a la acutal
                            // si es  asi compruebo si la preactia esta realizada o n o
                            //
                            Date current_date1 = dateFormatForMonth.parse(current_date);

                            Date selected_date =dateFormatForMonth.parse(practicas.get(i).getData_pract());



                            if (current_date1.after(selected_date)&&!practicas.get(i).isRealizada()){

                                event = new Event(Color.RED, date.getTime(), "PRACTICA");

                            } else if (current_date1.after(selected_date)&&practicas.get(i).isRealizada()){

                                    event = new Event(Color.GREEN, date.getTime(), "PRACTICA");


                            }



                        } catch (ParseException e) {
                            e.printStackTrace();
                        }






                        compactCalendarView.addEvent(event);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


                if (isCancelled()){
                    correcto=false;
                }
            }

            // lo que se hace durante la tarea
            return correcto;
        }
    }



}
