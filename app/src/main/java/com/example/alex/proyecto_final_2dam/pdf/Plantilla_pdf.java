package com.example.alex.proyecto_final_2dam.pdf;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.alex.proyecto_final_2dam.R;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Registrar_Alumnos_Fragment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class Plantilla_pdf {
    private Context context;
    private File pdf_file;
    private  com.itextpdf.text.Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fsubtitle = new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fRestaltado = new Font(Font.FontFamily.TIMES_ROMAN,15,Font.BOLD, BaseColor.RED);
    android.support.v4.app.FragmentManager fragmentManager;

    public android.support.v4.app.FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(android.support.v4.app.FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public Plantilla_pdf(Context context) {
    this.context=context;
    }
    public void openDocument(){
        createFile();
        try {
            document = new com.itextpdf.text.Document(PageSize.A4);
            pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(pdf_file));
            document.open();

        }catch (Exception e){
            Log.e("openoDocument",e.toString());
        }
    }
    private void createFile(){
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"Ficha_alumnos");
        if (!folder.exists()){
            folder.mkdirs();
            pdf_file=new File(folder,"Plantilla_pdf.pdf");


        } else
        {pdf_file=new File(folder,"Plantilla_pdf.pdf");}
    }

    public void closeDocument(){
        document.close();
    }

    public void addMetaData(String title , String tema , String author){
        document.addTitle(title);
        document.addSubject(tema);
        document.addAuthor(author);

    }

    public void addTitle(String title , String subtitle, Date data){
        try {
        paragraph = new Paragraph();
        addChield(new Paragraph(title,fTitle));
        addChield(new Paragraph(subtitle,fsubtitle));
        addChield(new Paragraph("Generado: "+data,fRestaltado));
        paragraph.setSpacingAfter(30);
        document.add(paragraph);

        }catch (Exception e){
            Log.e("add title ",e.toString());
        }

    }

    private void addChield(Paragraph child){
        child.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(child);
    }

    public void addParafo(String text){
        try {
        paragraph = new Paragraph(text,fText);
        paragraph.setSpacingAfter(5);
        paragraph.setSpacingBefore(5);
        document.add(paragraph);

        }catch (Exception e){
            Log.e("addParafo ",e.toString());
        }
    }

    public void CreateTable(String[] header, ArrayList<String[]> alumnos){
        try {
        paragraph= new Paragraph();
        paragraph.setFont(fText);
        PdfPTable pdfPTable = new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(100);
        pdfPTable.setSpacingBefore(20);
        PdfPCell pdfPCell ;
        int indexC =0;
        while (indexC<header.length){
            pdfPCell= new PdfPCell(new Phrase(header[indexC++],fsubtitle));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(BaseColor.GREEN);
            pdfPTable.addCell(pdfPCell);

        }
        for (int indexRow=0;indexRow<alumnos.size();indexRow++){
            String[] row = alumnos.get(indexRow);
            for (indexC=0;indexC<header.length;indexC++){
                pdfPCell= new PdfPCell(new Phrase(row[indexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
        }
        paragraph.add(pdfPTable);
        document.add(paragraph);
        }catch (Exception e){
            Log.e("create table",e.toString());
        }
    }

   public void viewPDf(){


        View__pdf_Fragment view__pdf_fragment = new View__pdf_Fragment();
        Bundle bundle = new Bundle();
        // System.out.println("ABOSUUULTEEE PATHHHH"+pdf_file.getAbsolutePath().toString());
        bundle.putString("path",pdf_file.getAbsolutePath().toString());
        view__pdf_fragment.setArguments(bundle);


        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, view__pdf_fragment).addToBackStack(null).commit();

    }

    public void viewPDfApp(Activity activity){
        if (pdf_file.exists()){
          //Uri uri = Uri.fromFile(pdf_file);
          Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + "com.example.alex.proyecto_final_2dam.Auxiliar.GenericFileProvider",pdf_file);
           Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setDataAndType(uri,"application/pdf");
           intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
           try {
               activity.startActivity(intent);

           }catch (ActivityNotFoundException e){
               activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
               Toast toast = Toast.makeText(activity.getBaseContext(),"No tienes ninguna aplicacion para visualizar pdf",Toast.LENGTH_LONG);
               toast.show();
           }
        } else {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("markt://details?id=com.adobe.reader")));
            Toast toast = Toast.makeText(activity.getBaseContext(),"El archivo no se encontro",Toast.LENGTH_LONG);
            toast.show();

        }



    }
}
