package tfg.lostandfound;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;

import Classes.Item;
import Classes.User;
import Controller.ServiceController;
import Services.LaunchService;
import Services.MatchingChecker;
import Services.NotificationDaemon;

import static Auxiliar.Auxiliar.showMessageError;
import static Auxiliar.Constants.INTERRUPTION_EXCEPTION;
import static Auxiliar.Constants.IO_EXCEPTION;

public class MainActivity extends AppCompatActivity {


    ImageButton btnChats;
    ImageButton btnArchive;
    ImageButton btnOptions;
    ImageButton btnLogOff;
    Button btnILost;
    Button btnIFound;
    TextView txtWelcome;
    User user;
    MyService service;
    IntentFilter filter;
    ReceptorServicio myReceiver;

    Intent intentService;

    boolean notificationsRead = false;


    ServiceController serviceController;


    @Override
    protected  void onResume()
    {

        super.onResume();
        try
        {


            // este trocito se debe mover






            txtWelcome.setText("Welcome, " + user.getStrUserName());



        }
        catch(Exception e)
        {

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeComponents();
        initializeListeners();

        //serviceController = new ServiceController(user);


        //serviceController.createService();
        //serviceController.startService();

        service = new MyService(user);

        filter = new IntentFilter("DATA");
        myReceiver = new ReceptorServicio();
        registerReceiver(myReceiver, filter);

        user = service.getUser();
       // Log.d("Username ", "-> " + user.getStrEmail() );
//        Log.d("Username 2", "-> " + service.getUser().getStrEmail() );
        Intent intentService = new Intent(MainActivity.this, service.getClass());
        intentService.putExtra("User", (Serializable) user);
        startService(intentService);




        //TODO Crear demonio que estará eternamente comprobando

    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);

        Item item = (Item) intent.getSerializableExtra("Item");

        if (item != null)
        {
            try
            {
                int a = item.save(user);
                Log.d("PEPEPE ", "---------" + item.getStrFoundLost());
                user.getListOfItems().add(item);
            }
            catch (IOException e)
            {
                showMessageError(MainActivity.this,IO_EXCEPTION);
            }
            catch (InterruptedException e)
            {
                showMessageError(MainActivity.this,INTERRUPTION_EXCEPTION);
            }
        }
    }






    /**
     *  Method that initialize all the listeners related to the components
     */
    public void initializeListeners()
    {
        /*
            Listener for Chats button
         */
        btnChats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //TODO CHATS SCREEN
                Intent I = new Intent(MainActivity.this, ChatsActivity.class);
                startActivity(I);
            }
        });
        /*
            Listener for Archive button
         */
        btnArchive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //TODO ARCHIVE SCREEN
                Intent I = new Intent(MainActivity.this, ArchiveActivity.class);
                I.putExtra("User", (Serializable) user);

                startActivity(I);
            }
        });
        /*
            Listener for Options button
         */
        btnOptions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //TODO OPTIONS SCREEN
                Intent I = new Intent(MainActivity.this, OptionsActivity.class);

                startActivity(I);
            }
        });
        /*
            Listener for I Lost button
         */
        btnILost.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Log.d("Button pressed","Lost Button");

                //Intent I = new Intent(MainActivity.this,NewCoordActivity.class);
                //startActivityForResult(I,1);

                Intent I = new Intent(MainActivity.this, RegisterItem.class);
                I.putExtra("ItemType","Lost");
                startActivity(I);
            }
        });
        /*
            Listener for I Found button
         */
        btnIFound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Log.d("Button pressed","Found Button");
                Intent I = new Intent(MainActivity.this, RegisterItem.class);
                I.putExtra("ItemType","Found");
                startActivity(I);
            }
        });
        /*
            Listener for LogOff button
         */
        btnLogOff.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Log.d("Button pressed","Found Button");
                stopService(new Intent(MainActivity.this, MyService.class));
                Intent I = new Intent(MainActivity.this, LogInActivity.class);
                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(I);
            }
        });
    }



    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {

        Log.d("InitialzieComponments","hola");

        user = (User) getIntent().getSerializableExtra("User");

        btnChats = (ImageButton) findViewById(R.id.btn_chats);
        btnArchive = (ImageButton) findViewById(R.id.btn_archive);
        btnOptions = (ImageButton) findViewById(R.id.btn_options);
        btnLogOff = (ImageButton) findViewById(R.id.btn_logoff);

        btnILost = (Button) findViewById(R.id.btn_lost);
        btnIFound = (Button) findViewById(R.id.btn_found);
        txtWelcome = (TextView) findViewById(R.id.txt_welcome);




        try {
            txtWelcome.setText("Welcome, " + user.getStrUserName());
        }
        catch(Exception e)
        {

        }

    }
    private void launchNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notificación de Prueba");
        mBuilder.setContentText("Contenido");
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker("Notificación de Prueba");
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET);

/*
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);


        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
       NotificationCompat.Action actionA =
                new NotificationCompat.Action(0, "Botón 1", resultPendingIntent);
        mBuilder.addAction(actionA);

*/
        //Si comento lo de arriba, no viajo a la pantalla.

        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

        unregisterReceiver(myReceiver);
        //stopService(intentService);

    }


    private class ReceptorServicio extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            if(!notificationsRead)
            {
                launchNotification();

            }

        }
    }


}
