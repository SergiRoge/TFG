package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import Classes.Item;
import Classes.User;
import Controller.Controller;

import static Auxiliar.Auxiliar.showMessageError;
import static Auxiliar.Constants.INTERRUPTION_EXCEPTION;
import static Auxiliar.Constants.IO_EXCEPTION;

public class MainActivity extends AppCompatActivity {


    ImageButton btnChats;
    ImageButton btnArchive;
    ImageButton btnOptions;
    Button btnILost;
    Button btnIFound;
    TextView txtWelcome;
    Controller controller;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        Log.d("OnCreate","1");
        initializeComponents();
        Log.d("OnCreate","2");
        initializeListeners();
        Log.d("OnCreate","3");


        //TODO Crear demonio que estará eternamente comprobando

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Item item = (Item) intent.getSerializableExtra("Item");

        if (item != null)
        {
            try
            {
                int a = item.save();
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

    }



    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        Log.d("InitialzieComponments","hola");
        controller = new Controller();

        user = (User) getIntent().getSerializableExtra("User");

        btnChats = (ImageButton) findViewById(R.id.btn_chats);
        btnArchive = (ImageButton) findViewById(R.id.btn_archive);
        btnOptions = (ImageButton) findViewById(R.id.btn_options);
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
}
