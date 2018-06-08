package com.example.alex.proyecto_final_2dam.pdf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.proyecto_final_2dam.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class View__pdf_Fragment extends Fragment {
    private PDFView pdfView;
    private File file;


    public View__pdf_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view__pdf_,container,false);
        pdfView = (PDFView)view.findViewById(R.id.pdf_view);

        Bundle bundle =this.getArguments();
        if (bundle!=null){
            file= new File(bundle.getString("path",""));
            pdfView.fromFile(file).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).enableAntialiasing(true).load();
        } else Log.e("error load pdf","error loading el pdf en el View__dpf");


        return view;
    }

}
