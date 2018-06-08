package com.example.alex.proyecto_final_2dam;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.Auxiliar.CostumDialogClassWelcomeScreen;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Nuevo_Bono;
import com.example.alex.proyecto_final_2dam.layout_fragments.Main_menu_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Modificar_datos_alu.Profile_Fragment_modificar_datos;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Registrar_Alumnos_Fragment;


public class MainActivity extends AppCompatActivity {

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("ojo del main activity");



        // Para credel ar el menu lateral
        //Coge la Inforamcion
        mPlanetTitles  = getResources().getStringArray(R.array.plantes_arrays);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {
            // run your one time code here
            CostumDialogClassWelcomeScreen costumDialogClassWelcomeScreen = new CostumDialogClassWelcomeScreen(this);
            costumDialogClassWelcomeScreen.show();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }





        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.single_item_drawer_layout,R.id.tv_item_drawer, mPlanetTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        Base_deDatos_Autoescuela  base_deDatos_autoescuela= new Base_deDatos_Autoescuela(this);
        Classe_Estatica_auxiliar.setBase_deDatos_autoescuela_MAIN(base_deDatos_autoescuela);

        android.support.v4.app.FragmentManager fragmentManager =this.getSupportFragmentManager();


        fragmentManager.beginTransaction().replace(R.id.frame_layout,new Main_menu_Fragment()).addToBackStack("main_menu").commit();


    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position


        if (position==0){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

            Registrar_Alumnos_Fragment registrar_alumnos_fragment = new  Registrar_Alumnos_Fragment();
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, registrar_alumnos_fragment).addToBackStack(null).commit();


            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);

        } else  if (position==1){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Consultar_alumnos_fragment()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);
        } else if (position==2) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Profile_Fragment_modificar_datos()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        }else if (position==3) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Agenda_Fragment()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        } else if (position==4) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Nuevo_Bono()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        }else if (position==5) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Main_menu_Fragment()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        }


        else if (position==6) {
           System.exit(0);


        }

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        System.out.println("mtitles "+mPlanetTitles[position]);

        mDrawerLayout.closeDrawer(mDrawerList);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }



}
