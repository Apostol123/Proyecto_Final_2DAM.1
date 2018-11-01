package com.example.alex.proyecto_final_2dam;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.alex.proyecto_final_2dam.Auxiliar.Classe_Estatica_auxiliar;
import com.example.alex.proyecto_final_2dam.Auxiliar.CostumDialogClassWelcomeScreen;
import com.example.alex.proyecto_final_2dam.db.Base_deDatos_Autoescuela;
import com.example.alex.proyecto_final_2dam.layout_fragments.Bonos.Nuevo_Bono;
import com.example.alex.proyecto_final_2dam.layout_fragments.Main_menu_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.Practicas.citar_a_practica.citar_a_pract_prfile;
import com.example.alex.proyecto_final_2dam.layout_fragments.agenda.Agenda_Fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Consultar_datos_alu.Consultar_alumnos_fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Modificar_datos_alu.Profile_Fragment_modificar_datos;
import com.example.alex.proyecto_final_2dam.layout_fragments.gestionar_alumnos.Registrar_Alumnos_Fragment;

import com.example.alex.proyecto_final_2dam.layout_fragments.login_Package.Sign_UP_fragment;
import com.example.alex.proyecto_final_2dam.layout_fragments.login_Package.Sign_upAcitivity;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.widget.DataBufferAdapter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.google.android.gms.drive.Drive.getDriveResourceClient;


public class MainActivity extends AppCompatActivity {



    //** gooogle Drive SqliteDAtbase

    public static final String PACKAGE_NAME = "com.example.alex.proyecto_final_2dam";
    public static final String DATABASE_NAME = "Autoescuela";
    public static final String TABLE_NAME = "orgs_table";

    private static final String DATABASE_PATH = "/data/data/" + PACKAGE_NAME + "/databases/" + DATABASE_NAME;
    private static final File DATA_DIRECTORY_DATABASE =
            new File(Environment.getDataDirectory() + "/data/" + PACKAGE_NAME + "/databases/" + DATABASE_NAME);




    //** google drive SQliteDatabase

    private DriveFile driveFile;
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private    SharedPreferences prefs;

    private static final String TAG = "drive-quickstart";
    private static final int REQUEST_CODE_SIGN_IN = 0;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 1;
    private static final int REQUEST_CODE_CREATOR = 2;
    private DriveClient mDriveClient;
    private DriveResourceClient mDriveResourceClient;
    private GoogleSignInClient mGoogleSignInClient;
    private Bitmap mBitmapToSave;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("ojo del main activity");
        Base_deDatos_Autoescuela  base_deDatos_autoescuela= new Base_deDatos_Autoescuela(this);
        Classe_Estatica_auxiliar.setBase_deDatos_autoescuela_MAIN(base_deDatos_autoescuela);

    Classe_Estatica_auxiliar.setMainActivity(this);
    Classe_Estatica_auxiliar.setFragmentManager(getSupportFragmentManager());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);





    Classe_Estatica_auxiliar.setPrefs(prefs);


        if(prefs.getBoolean("Logged", Classe_Estatica_auxiliar.getLogged())==false) {



            Intent i = new Intent(this, Sign_upAcitivity.class);
            MainActivity.this.startActivity(i);

            // run your one time code here

            Classe_Estatica_auxiliar.setLogged(true);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Logged", Classe_Estatica_auxiliar.getLogged());
            editor.commit();
        } else {




            mPlanetTitles  = getResources().getStringArray(R.array.plantes_arrays);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer1);

            // Set the adapter for the list view
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.single_item_drawer_layout,R.id.tv_item_drawer, mPlanetTitles));

            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



            Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Main_menu_Fragment()).addToBackStack(null).commit();


            if(!prefs.getBoolean("firstTime", false)) {


                // run your one time code here
                CostumDialogClassWelcomeScreen costumDialogClassWelcomeScreen = new CostumDialogClassWelcomeScreen(this);
                costumDialogClassWelcomeScreen.show();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("firstTime", true);
                editor.commit();
            }
        }










    }


    /*

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.moveTaskToBack(true);
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position


        if (position==0){


            android.support.v4.app.FragmentTransaction fragmentTransaction = Classe_Estatica_auxiliar.getFragmentManager().beginTransaction();
            Registrar_Alumnos_Fragment registrar_alumnos_fragment = (Registrar_Alumnos_Fragment)   Classe_Estatica_auxiliar.getFragmentManager().findFragmentByTag("alta_alu");

            if (registrar_alumnos_fragment!=null){
                System.out.println("esta creado el fragment de registrar alumnos");
                Log.e("alu","esta creado el fragment de registrar alumnos");
            fragmentTransaction.attach(registrar_alumnos_fragment);


            } else
                System.out.println("no esta creado el fragmento de registarr alumnos");

                Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Registrar_Alumnos_Fragment()).addToBackStack(null).commit();




            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);

        } else  if (position==1){
            Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Consultar_alumnos_fragment()).addToBackStack(null).commit();
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

            android.support.v4.app.FragmentTransaction fragmentTransaction = Classe_Estatica_auxiliar.getFragmentManager().beginTransaction();
         Agenda_Fragment  agenda_fragment= (Agenda_Fragment)   Classe_Estatica_auxiliar.getFragmentManager().findFragmentByTag("agenda");

            if (agenda_fragment!=null) {
                System.out.println("esta creado el fragment de agenda");
                Log.e("alu", "esta creado el fragment de registrar alumnos");
                Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, agenda_fragment).addToBackStack("agenda").commit();

            }else {
                System.out.println("NO NO N NO esta creado el fragment de agenda");

                Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Agenda_Fragment()).addToBackStack("agenda").commit();
            }
            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        } else if (position==4) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Nuevo_Bono()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        }else if (position==5) {
            android.support.v4.app.FragmentManager fragmentManager =getSupportFragmentManager();
            Consultar_alumnos_fragment consultar_alumnos_fragment = new Consultar_alumnos_fragment();
            Bundle bundle = new Bundle();
            bundle.putString("practica","practica");
            consultar_alumnos_fragment.setArguments(bundle);

            Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right).replace(R.id.frame_layout,consultar_alumnos_fragment).addToBackStack("main_menu_alu").commit();


            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        }
        else if (position==6) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();


            Classe_Estatica_auxiliar.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right).replace(R.id.frame_layout, new Main_menu_Fragment()).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mPlanetTitles[position]);
            System.out.println("mtitles "+mPlanetTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);


        }


        else if (position==7) {
            System.exit(0);


        }
        else if (position==8){
            Classe_Estatica_auxiliar.setLogged(false);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Logged", Classe_Estatica_auxiliar.getLogged());
            editor.commit();
            signOut();
               Intent intent = new Intent(this,Sign_upAcitivity.class);
               startActivity(intent);



        } else if (position==9){
            signIn();

        } else if (position==10){

            listFiles();



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




    /** Start sign in activity. */
    private void signIn() {
        Log.i(TAG, "Start sign in");
        mGoogleSignInClient = buildGoogleSignInClient();
        startActivityForResult(mGoogleSignInClient.getSignInIntent(), REQUEST_CODE_SIGN_IN);


    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(Classe_Estatica_auxiliar.getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(getApplicationContext(),"Session De Google Cerrada",Toast.LENGTH_SHORT).show();


            }
        });
    }

    /** Build a Google SignIn client. */
    private GoogleSignInClient buildGoogleSignInClient() {
        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestScopes(Drive.SCOPE_FILE)
                        .build();
        return GoogleSignIn.getClient(this, signInOptions);
    }

    /** Create a new file and save it to Drive. */
    private void saveFileToDrive(final String filePath) {
        // Start by creating a new contents, and setting a callback.
        Log.i(TAG, "Creating new contents.");

        mDriveResourceClient
                .createContents()
                .continueWithTask(
                        new Continuation<DriveContents, Task<Void>>() {
                            @Override
                            public Task<Void> then(@NonNull Task<DriveContents> task) throws Exception {

                                return createFileIntentSender(task.getResult(), new File(filePath));
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Failed to create new contents.", e);
                            }
                        });
    }



    /**
     * Creates an {@link IntentSender} to start a dialog activity with configured {@link
     * CreateFileActivityOptions} for user to create a new photo in Drive.
     */
    private Task<Void> createFileIntentSender(DriveContents driveContents, File file) throws Exception {
        Log.i(TAG, "New contents created.");

        OutputStream outputStream = driveContents.getOutputStream();
        InputStream in = new FileInputStream(file);
        try {
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    outputStream.write(buf, 0, len);
                }
            } finally {
                outputStream.close();
            }
        } finally {
            in.close();
        }

        SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());

        Date date1 = Calendar.getInstance().getTime();


        String selected_date=dateFormatForMonth.format(date1).toString();

        // Create the initial metadata - MIME type and title.
        // Note that the user will be able to change the title later.
        MetadataChangeSet metadataChangeSet =
                new MetadataChangeSet.Builder()
                        .setMimeType("application/x-sqlite3")
                        .setTitle("Copia base de datos "+selected_date)
                        .build();
        // Set up options to configure and display the create file activity.
        CreateFileActivityOptions createFileActivityOptions =
                new CreateFileActivityOptions.Builder()
                        .setInitialMetadata(metadataChangeSet)
                        .setInitialDriveContents(driveContents)
                        .build();

        return mDriveClient
                .newCreateFileActivityIntentSender(createFileActivityOptions)
                .continueWith(
                        new Continuation<IntentSender, Void>() {
                            @Override
                            public Void then(@NonNull Task<IntentSender> task) throws Exception {
                                startIntentSenderForResult(task.getResult(), REQUEST_CODE_CREATOR, null, 0, 0, 0);
                                return null;
                            }
                        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN:
                Log.i(TAG, "Sign in request code");
                // Called after user is signed in.
                if (resultCode == RESULT_OK) {
                    Log.i(TAG, "Signed in successfully.");
                    // Use the last signed in account here since it already have a Drive scope.
                    mDriveClient = Drive.getDriveClient(this, GoogleSignIn.getLastSignedInAccount(this));
                    // Build a drive resource client.
                    mDriveResourceClient =
                            getDriveResourceClient(this, GoogleSignIn.getLastSignedInAccount(this));



                    final String filePath = DATABASE_PATH;
                    saveFileToDrive(filePath);
                }
                break;
        }
    }





    private void listFiles() {
        Query query = new Query.Builder()
                .addFilter(Filters.eq(SearchableField.MIME_TYPE, "application/x-sqlite3"))
                .build();
        // [START query_files]
        mDriveResourceClient =
                getDriveResourceClient(this, GoogleSignIn.getLastSignedInAccount(this));
        Task<MetadataBuffer> queryTask = mDriveResourceClient.query(query);
        // [END query_files]
        // [START query_results]
        queryTask.addOnCompleteListener(this,
                new OnCompleteListener<MetadataBuffer>() {
                    @Override
                    public void onComplete(@NonNull Task<MetadataBuffer> task1) {
                        if (task1.isSuccessful()) {


                                // Start by creating a new contents, and setting a callback.
                                Log.i(TAG, "Creating new contents.");

                                int nr = task1.getResult().getCount();

                            System.out.println("nr de results "+nr);

                        driveFile = task1.getResult().get(0).getDriveId().asDriveFile();



                            Log.i("ARRIVE", "File Found");

                        }
                    }});


        Task<DriveContents> openFileTask =
                mDriveResourceClient.openFile(driveFile, DriveFile.MODE_READ_ONLY);

        openFileTask.addOnCompleteListener(this, new OnCompleteListener<DriveContents>() {
            @Override
            public void onComplete(@NonNull Task<DriveContents> task) {
                    if (task.isSuccessful()){
                        DriveContents driveContents =task.getResult();


                        InputStream mInput = driveContents.getInputStream();
                        OutputStream mOutput;

                        try {
                            mOutput = new FileOutputStream(DATABASE_PATH);
                            byte[] mBuffer = new byte[1024];
                            int mLength;
                            while ((mLength = mInput.read(mBuffer)) > 0) {
                                mOutput.write(mBuffer, 0, mLength);
                            }

                            mOutput.flush();

                            mInput.close();
                            mOutput.close();
                            System.out.println("load succesfull");

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        System.out.println("error copianddo el archivo ");
                    }

        }});


        // [END query_results]
    }
    }







