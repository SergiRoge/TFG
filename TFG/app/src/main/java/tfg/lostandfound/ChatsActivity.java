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
public class ChatsActivity extends AppCompatActivity {


    Button btn_back;
    ListView lstview_chats;
    ArrayList<Chat> arraylst_chats;

    private ListView listView1;

    ArrayList<TextView> arraylst_txtview_data_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        initializeComponents();
        initializeListeners();
        fillViewList();


        /*
            Listener for Save button
         */

    }

    private void fillViewList()
    {
        User user = (User) getIntent().getSerializableExtra("User");
        Log.d("user.lst_chats ", "-> " + user.lst_chats);
        int arrayLength = user.lst_chats.size();
        if(arrayLength > 0)
        {
            ChatViewList chat_data[] = new ChatViewList[arrayLength];


            Item item;
            for(int iterator = 0; iterator < arrayLength; iterator++)
            {

                int intIcon = 0;
                //RELLENAR
                chat_data[iterator] = new ChatViewList("chat con el usuario " + user.lst_chats.get(iterator).getUser().getStrUserName());

            }

            ChatAdapter adapter = new ChatAdapter(this, R.layout.listview_item_row, chat_data);
            listView1 = (ListView)findViewById(R.id.lst_chats);
            listView1.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No tienes ningun chat", Toast.LENGTH_SHORT).show();
        }


    }

    private void initializeListeners()
    {
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent I = new Intent(ChatsActivity.this, MainActivity.class);
                I.putExtra("FROM"," ");

                startActivity(I);
            }
        });

        lstview_chats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                //Object object = adapter.getItemAtPosition(position);
                //ChatViewList itvieli = (ChatViewList)object;
                //User user = itvieli.getTheOtherUser();

                Intent I = new Intent(ChatsActivity.this, ChatActivity.class);
                //I.putExtra("User", (Serializable) user);

                ChatsActivity.this.startActivityForResult(I,1);
            }
        });

    }


    public void initializeComponents()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        lstview_chats = (ListView) findViewById(R.id.lst_chats);
        arraylst_chats = new ArrayList<Chat>();
        arraylst_txtview_data_1 = new ArrayList<>();

    }








}
