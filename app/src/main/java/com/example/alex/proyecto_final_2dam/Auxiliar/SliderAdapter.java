package com.example.alex.proyecto_final_2dam.Auxiliar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alex.proyecto_final_2dam.R;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public  SliderAdapter (Context context){
        this.context=context;

    }

    public int [] slide_imges = {
           R.mipmap.mainscreen_low_size,
            R.mipmap.agenda_low_size,
            R.mipmap.creat_doc_low_size




    };

    public String[] slide_decs = {

            "Administra tus alumnos de forma facil y eficiente",
            "Organizate con la agenda , controla las practicas y los horarios de forma fácil ",
            "Posibilidad de exportar archivos pdf encapuslados con información de practicas , datos de alumnos ..etc"
    };

    public String[] slide_headers = {
      "Administar" ,
      "Organizar",
      "Crear"
    };


    @Override
    public int getCount() {
        return slide_headers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView)view.findViewById(R.id.image_hole_welcome);
        TextView slideHeading = (TextView)view.findViewById(R.id.tv_header_welcome);
        TextView slideText = (TextView)view.findViewById(R.id.tv_txt_welcome);

        slideImageView.setImageResource(slide_imges[position]);
        slideHeading.setText(slide_headers[position]);
        slideText.setText(slide_decs[position]);


        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


        container.removeView((RelativeLayout)object);
    }
}
