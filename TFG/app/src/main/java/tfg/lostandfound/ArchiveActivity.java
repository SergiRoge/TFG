package tfg.lostandfound;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import Auxiliar.ItemAdapter;
import Classes.Item;
import Classes.ItemViewList;
import Classes.User;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Constants.OK;


/**
 * Class ArchiveActivity
 *
 * The java class related to archive_activity.xml
 *
 * It manages all the behaviour of the widgets on the screen.
 *
 */
public class ArchiveActivity extends AppCompatActivity {

    Button btn_back;
    ListView lstview_items;
    ArrayList<Item> arraylst_items;

    User user;

    private ListView listView1;

    ArrayList<TextView> arraylst_txtview_data_1;


    /**
     * Method onDestroy
     */
    protected void onDestroy()
    {
        Log.d("ARCHIVE On ", " Destroy");
        super.onDestroy();
    }

    /**
     * Override method onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);


        /**
         * Calling the methods that initialize the components and the listeners
         */
        initializeComponents();
        initializeListeners();
        fillViewList();


    }



    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        lstview_items = (ListView) findViewById(R.id.lst_items);
        arraylst_items = new ArrayList<Item>();
        arraylst_txtview_data_1 = new ArrayList<>();
    }

    /**
     *  Method that initialize all the listeners related to the components
     */
    public void initializeListeners() {
        /*
            Listener for Back button, when pressed, it returns to MainActivity
         */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch Register User Activity
                Intent I = new Intent(ArchiveActivity.this, MainActivity.class);
                I.putExtra("FROM"," ");
                finish();
                startActivity(I);
            }
        });

        /*
            Listener for the list view, when pressed, it leads the user to the item view activity
         */
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

    /**
     * Method that fills the list of items in the archive ViewList.
     *
     */
    public void fillViewList() {

        user = (User) getIntent().getSerializableExtra("User");
        int arrayLength = user.getListOfItems().size();
        Log.d("Usuario " + user.getStrUserName() + " : " + user.getListOfItems().size(), " items");

        //If the user has registered any items
        if(arrayLength > 0)
        {
            //we create the array that will show the data in the list view
            ItemViewList item_data[] = new ItemViewList[arrayLength];


            Item item;
            for (int iterator = 0; iterator < arrayLength; iterator++)
            {
                item = user.getListOfItems().get(iterator);

                int intIcon;
                //This should set the icon depending by the status of the item
                //but it does not work
                if (item.getStrFoundLost().equals("Lost"))
                {
                    intIcon = R.drawable.icon_lost;
                }
                else
                {
                    intIcon = R.drawable.icon_found;
                }

                //we add each row with the icon, and the text shown in each row
                item_data[iterator] = new ItemViewList(intIcon, item.getStrFoundLost() + " - " + item.getStrItemType() + ", " + item.getStrItemColor() + ", " + item.getStrItemMaterial(), item);

            }


            //We create the new adapter with the layout and the data to be shown
            ItemAdapter adapter = new ItemAdapter(this, R.layout.listview_item_row, item_data);

            //And set the previous created adapter
            listView1 = (ListView) findViewById(R.id.lst_items);
            listView1.setAdapter(adapter);
        }
        else
        {
            //If there is no items, we show a little message that notifies that to the user
            Toast.makeText(getApplicationContext(), "No tienes ningun chat", Toast.LENGTH_SHORT).show();
        }


    }


    //TODO elmininar esto
    /**
     *  The callback called when coming from the NewCoordActivity.
     *
     * @param requestCode   The code received from the NewCoordActivity.
     * @param resultCode    The code received from the NewCoordActivity.
     * @param data          The data to be retreived.
     *//*
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


    }*/
}
