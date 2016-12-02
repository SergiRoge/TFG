package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.ItemAdapter;
import Classes.Item;
import Classes.ItemViewList;
import Classes.User;

public class ChatsActivity extends AppCompatActivity {


    ListView lstview_chats;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        initializeComponents();
        //initializeListeners();
        //fillChatsList();

        btn_back = (Button) findViewById(R.id.btn_save);

        /*
            Listener for Save button
         */
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
    }


    public void initializeComponents()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        lstview_chats = (ListView) findViewById(R.id.lst_chats);
        //arraylst_items = new ArrayList<Item>();
        //arraylst_txtview_data_1 = new ArrayList<>();
    }


/*
        Button btn_back;
    ListView lstview_items;
    ArrayList<Item> arraylst_items;

    User user;

    private ListView listView1;

    ArrayList<TextView> arraylst_txtview_data_1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);


        initializeComponents();
        initializeListeners();
        fillViewList();


    }

    private void formatDataToShow() {

    }


    public void initializeComponents()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        lstview_items = (ListView) findViewById(R.id.lst_items);
        arraylst_items = new ArrayList<Item>();
        arraylst_txtview_data_1 = new ArrayList<>();
    }


       Method that initialize all the listeners related to the components

    public void initializeListeners() {

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch Register User Activity
                Intent I = new Intent(ArchiveActivity.this, MainActivity.class);
                I.putExtra("FROM"," ");
                startActivity(I);
            }
        });

        lstview_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                Object object = adapter.getItemAtPosition(position);
                ItemViewList itvieli = (ItemViewList)object;
                Item item = itvieli.getItem();

                Intent I = new Intent(ArchiveActivity.this, ItemViewActivity.class);
                I.putExtra("Item", (Serializable) item);

                ArchiveActivity.this.startActivityForResult(I,1);
            }
        });
    }



    public void fillChatsList()
    {

        user = (User) getIntent().getSerializableExtra("User");
        int arrayLength = user.getListOfItems().size();
        ItemViewList item_data[] = new ItemViewList[arrayLength];


        Item item;
        for(int iterator = 0; iterator < arrayLength; iterator++)
        {
            item = user.getListOfItems().get(iterator);

            int intIcon;
            if(item.getStrItemBrand().equals("Lost"))
            {
                intIcon = R.drawable.icon_lost;
            }
            else
            {
                intIcon = R.drawable.icon_found;
            }

            item_data[iterator] = new ItemViewList(intIcon, item.getStrFoundLost() + " - " + item.getStrItemType() + ", " + item.getStrItemColor() + ", " + item.getStrItemMaterial(), item);

        }


        formatDataToShow();
        ItemAdapter adapter = new ItemAdapter(this, R.layout.listview_item_row, item_data);


        listView1 = (ListView)findViewById(R.id.lst_items);
        listView1.setAdapter(adapter);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("requestCode","requestCode : " + requestCode);

        if(resultCode == 1){
            Log.d("OK","OK");

        }
        if (resultCode == 2) {
            Log.d("RESULT_CANCELED","RESULT_CANCELED");
            //TODO borrar el item
        }


    }


*/





}
