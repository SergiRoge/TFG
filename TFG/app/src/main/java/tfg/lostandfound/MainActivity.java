package tfg.lostandfound;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Chat.Chat;
import Classes.Item;
import Classes.User;
import Connection.SQLObject;
import Controller.ServiceController;

import static Auxiliar.Auxiliar.showMessageError;
import static Auxiliar.Constants.INTERRUPTION_EXCEPTION;
import static Auxiliar.Constants.IO_EXCEPTION;
import static Auxiliar.Constants.URL_MATCHING_RESULT;
import static tfg.lostandfound.R.drawable.chat_warning;

public class MainActivity extends AppCompatActivity {


    ImageButton btnChats;
    ImageButton btnArchive;
    ImageButton btnOptions;
    ImageButton btnLogOff;
    Button btnILost;
    Button btnIFound;
    TextView txtWelcome;
    User user;

    MatchingService matchingService;
    IntentFilter matchingFilter;
    MatchingReceiver matchingReceiver;


    ChatService chatService;
    IntentFilter chatFilter;
    ChatReceiver chatReceiver;

    boolean notificationsRead = false;


    ServiceController serviceController;


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        try
        {

            unregisterReceiver(matchingReceiver);
            unregisterReceiver(chatReceiver);
        }
        catch(Exception e)
        {

        }

    }

    @Override
    protected  void onResume()
    {
        Log.d("--------->","onResume use : " + user);
        super.onResume();



    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("--------->","onCreate");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeComponents();
        initializeListeners();
        initializeServicesAndReceivers();


    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        String strFrom = intent.getExtras().getString("FROM");




        if(strFrom.equals("ChatActivity"))
        {
            //btnChats.setBackgroundResource(chat_warning);


        }
        //Si venimos de la pantalla ItemMatchingActivity



        else if(strFrom.equals("ItemMatchingActivity"))
        {
            String strResult = intent.getExtras().getString("Result");
            String rowID = intent.getExtras().getString("RowID");



            String content = "";
            content += "result="+strResult+"&rowid="+rowID;
            SQLObject sqlObject = new SQLObject();
            try
            {
                String strReturn  = sqlObject.ExecuteQuery(URL_MATCHING_RESULT,content);

                Log.d("strReturn : ",">->-> " + strReturn);
                //Si el resultado es si, creamos nuevo chat con el usuario que perdio el objeto
                if(strResult.equals("YES"))
                {
                    JSONObject jsonObject = new JSONObject(strReturn);

                    Log.d("jsonObject" ," lista chats " + jsonObject.toString());
                    String Email = jsonObject.getString("Email");
                    String UserName = jsonObject.getString("UserName");

                    Chat chat = new Chat(UserName, Email);

                    user.saveChat(chat);


                }
                //Si es que no, no hacemos nada
                else if(strResult.equals("NO"))
                {
                    //TODO Añadir a la tNonMatchingItems
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else
        {
            Item item = (Item) intent.getSerializableExtra("Item");

            if (item != null)
            {
                try
                {
                    Log.d("user en onnewintent " ,"-- " + user);
                    int a = item.save(user);

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


        txtWelcome.setText("Bienvenido " + user.getStrUserName());

    }

    public void initializeServicesAndReceivers()
    {

        matchingService = new MatchingService(user);
        matchingFilter = new IntentFilter("DATA");
        matchingReceiver = new MatchingReceiver();
        registerReceiver(matchingReceiver, matchingFilter);
        user = matchingService.getUser();
        Intent intentService = new Intent(MainActivity.this, matchingService.getClass());
        intentService.putExtra("User", (Serializable) user);
        startService(intentService);



        chatService = new ChatService(user);
        chatFilter = new IntentFilter("CHATS");
        chatReceiver = new ChatReceiver();
        registerReceiver(chatReceiver, chatFilter);
        Intent chatIntentService = new Intent(MainActivity.this, chatService.getClass());
        chatIntentService.putExtra("User", (Serializable) user);
        startService(chatIntentService);


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
                Log.d("antes de chats" ," lista chats " + user.lst_chats.size());
                I.putExtra("User", (Serializable) user);
                //finish();
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
                //finish();
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
                //finish();
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


                //Intent I = new Intent(MainActivity.this,NewCoordActivity.class);
                //startActivityForResult(I,1);

                Intent I = new Intent(MainActivity.this, RegisterItem.class);
                I.putExtra("ItemType","Lost");
                //finish();
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

                Intent I = new Intent(MainActivity.this, RegisterItem.class);
                I.putExtra("ItemType","Found");
               // finish();
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

                stopService(new Intent(MainActivity.this, MatchingService.class));
                stopService(new Intent(MainActivity.this, ChatService.class));
                unregisterReceiver(matchingReceiver);
                unregisterReceiver(chatReceiver);


                Intent I = new Intent(MainActivity.this, LogInActivity.class);
                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                user = null;

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

    private void LaunchChatNotification(Intent intent) throws IOException, InterruptedException {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Alguien quizá ha encontrado algo tuyo");
        //mBuilder.setContentText("Contenido");
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker("Item_found_notification");
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET);


        Intent resultIntent = new Intent(this, ChatsActivity.class);

        Log.d("notif chats : ","-->" + user.lst_chats.size());

        resultIntent.putExtra("User", (Serializable) user);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);


        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationCompat.Action actionA =
                new NotificationCompat.Action(0, "Botón 1", resultPendingIntent);
        mBuilder.addAction(actionA);


        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());



        btnChats.setBackgroundResource(chat_warning);



        //unregisterReceiver(chatReceiver);
    }

    private void launchMatchingNotification(Intent intent) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Quizá has encontrado el objeto de alguien ");
        //mBuilder.setContentText("Contenido");
        mBuilder.setAutoCancel(true);
        mBuilder.setTicker("Item_found_notification");
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET);


        Intent resultIntent = new Intent(this, ItemMatchingActivity.class);


        resultIntent.putExtra("User", (Serializable) user);
        resultIntent.putExtra("IDItemLost", ""+intent.getExtras().getString("IDItemLost"));
        resultIntent.putExtra("IDItemFound", ""+intent.getExtras().getString("IDItemFound"));
        resultIntent.putExtra("Description", ""+intent.getExtras().getString("Description"));
        resultIntent.putExtra("Type", ""+intent.getExtras().getString("Type"));
        resultIntent.putExtra("Material", ""+intent.getExtras().getString("Material"));
        resultIntent.putExtra("Color", ""+intent.getExtras().getString("Color"));
        resultIntent.putExtra("RowID", ""+intent.getExtras().getString("RowID"));





        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);


        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
       NotificationCompat.Action actionA =
                new NotificationCompat.Action(0, "Botón 1", resultPendingIntent);
        mBuilder.addAction(actionA);


        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());



        unregisterReceiver(matchingReceiver);
    }


    private class MatchingReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent intent) {
            if(!notificationsRead)
            {
                launchMatchingNotification(intent);

            }

        }
    }
    private class ChatReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent intent) {
            if(!notificationsRead)
            {

                try {
                    LaunchChatNotification(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
