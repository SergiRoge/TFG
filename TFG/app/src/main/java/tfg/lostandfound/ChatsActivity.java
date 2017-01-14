package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.ChatAdapter;
import Auxiliar.ItemAdapter;
import Classes.ChatViewList;
import Classes.Item;
import Classes.ItemViewList;
import Classes.User;


import Chat.Chat;

/**
 * Class ChatsActivity
 *
 * The java class related to activity_chats.xml
 *
 * It manages all the behaviour of the widgets on the screen.
 *
 */
public class ChatsActivity extends AppCompatActivity {


    Button btn_back;
    ListView lstview_chats;
    ArrayList<Chat> arraylst_chats;

    private ListView listView1;

    ArrayList<TextView> arraylst_txtview_data_1;

    User user;

    /**
     * Method onDestroy
     */
    protected void onDestroy()
    {
        Log.d("CHATS On ", " Destroy");
        super.onDestroy();
    }


    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        fillViewList();

    }
    /**
     * Override method onResume
     *
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        fillViewList();

    }
    /**
     * Override method onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        /**
         * Calling the methods that initialize the components and the listeners
         */
        initializeComponents();
        initializeListeners();
        fillViewList();


    }
    /**
     * Method that fills the list of items in the archive ViewList.
     *
     */
    private void fillViewList()
    {

        User user = (User) getIntent().getSerializableExtra("User");

        int arrayLength = user.lst_chats.size();
        Log.d("user.lst_chats ", "-> " + arrayLength);

        //If the user has any chat with other user
        if(arrayLength > 0)
        {
            //we create the array that will show the data in the list view

            ChatViewList chat_data[] = new ChatViewList[arrayLength];


            Item item;
            for(int iterator = 0; iterator < arrayLength; iterator++)
            {
                Log.d("chat con usuario ", "-> " + user.lst_chats.get(iterator).getUser().getStrUserName());
                int intIcon = 0;

                //we add each row with the text shown in each row
                chat_data[iterator] = new ChatViewList("chat con el usuario " + user.lst_chats.get(iterator).getUser().getStrUserName());

            }
            //We create the new adapter with the layout and the data to be shown
            ChatAdapter adapter = new ChatAdapter(this, R.layout.listview_item_row, chat_data);
            listView1 = (ListView)findViewById(R.id.lst_chats);

            //And set the previous created adapter
            listView1.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No tienes ningun chat", Toast.LENGTH_SHORT).show();
        }

            int a = 1;

    }
    /**
     *  Method that initialize all the listeners related to the components
     */
    private void initializeListeners()
    {
         /*
            Listener for Back button, when pressed, it returns to MainActivity
         */
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent I = new Intent(ChatsActivity.this, MainActivity.class);
                I.putExtra("FROM","ChatActivity");
                finish();
                startActivity(I);
            }
        });
        /*
            Listener for the list view, when pressed, it leads the user to the chat with the other user
         */
        lstview_chats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                //Object object = adapter.getItemAtPosition(position);
                //ChatViewList itvieli = (ChatViewList)object;
                //User user = itvieli.getTheOtherUser();

                Intent I = new Intent(ChatsActivity.this, ChatActivity.class);
                //I.putExtra("User", (Serializable) user);
                finish();
                ChatsActivity.this.startActivityForResult(I,1);
            }
        });

    }

    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        lstview_chats = (ListView) findViewById(R.id.lst_chats);
        arraylst_chats = new ArrayList<Chat>();
        arraylst_txtview_data_1 = new ArrayList<>();

    }








}
