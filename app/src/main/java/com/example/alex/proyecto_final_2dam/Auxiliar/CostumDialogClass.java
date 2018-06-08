package com.example.alex.proyecto_final_2dam.Auxiliar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.R;

public class CostumDialogClass extends Dialog implements android.view.View.OnClickListener {
    private CheckBox checkBox_dura_normal;
    private CheckBox checkBox_dura_doble;
    private CheckBox checkBox_dura_largo;
    private  int duracionPract=0;

    public CostumDialogClass(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.seleccioar_hora_dialog);
        checkBox_dura_normal=(CheckBox)findViewById(R.id.radio_pract_normal);
        checkBox_dura_largo=(CheckBox)findViewById(R.id.radio_pract_larga);
        checkBox_dura_doble=(CheckBox)findViewById(R.id.radio_pract_doble);

        checkBox_dura_normal.setOnClickListener(this);
        checkBox_dura_largo.setOnClickListener(this);
        checkBox_dura_doble.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {



        switch (v.getId()){
            case R.id.radio_pract_normal:
                  duracionPract=45;


                    dismiss();


                break;

            case R.id.radio_pract_larga:
                duracionPract=67;

                try {
                    dismiss();

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }


                break;
            case R.id.radio_pract_doble: duracionPract=90;  try {

                dismiss();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } break;
            default: break;

        }


    }

    public int getDuracionPract() {
        return duracionPract;
    }

    private void Uncheck(){
        checkBox_dura_normal.setChecked(false);
        checkBox_dura_largo.setChecked(false);
        checkBox_dura_doble.setChecked(false);
    }


}
