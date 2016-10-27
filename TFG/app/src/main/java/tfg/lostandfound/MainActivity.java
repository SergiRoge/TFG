package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import Classes.Item;
import Classes.User;
import Controller.Controller;

public class MainActivity extends AppCompatActivity {


    ImageButton btnChats;
    ImageButton btnArchive;
    ImageButton btnOptions;
    Button btnILost;
    Button btnIFound;
    TextView txtWelcome;
    Controller controller;
    User user;

    Item item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        initializeComponents();
        initializeListeners();

        //TODO Crear demonio que estará eternamente comprobando

    }
    @Override
    protected void onResume()
    {
        super.onResume();

        item = (Item) getIntent().getSerializableExtra("Item");
        if(item != null)
        {
            //Aquí tenemos el item, dependiendo si es Lost o si es Found haremos una cosa u otra


            controller.saveItem(item);
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
        controller = new Controller();

        user = (User) getIntent().getSerializableExtra("User");

        btnChats = (ImageButton) findViewById(R.id.btn_chats);
        btnArchive = (ImageButton) findViewById(R.id.btn_archive);
        btnOptions = (ImageButton) findViewById(R.id.btn_options);
        btnILost = (Button) findViewById(R.id.btn_lost);
        btnIFound = (Button) findViewById(R.id.btn_found);
        txtWelcome = (TextView) findViewById(R.id.txt_welcome);
        txtWelcome.setText("Welcome, " + user.getStrUserName());

    }
}
