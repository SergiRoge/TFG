package tfg.lostandfound;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Auxiliar.ItemAdapter;
import Classes.Item;
import Classes.ItemViewList;
import Controller.Controller;

import static Auxiliar.Auxiliar.*;

public class ArchiveActivity extends AppCompatActivity {

    Button btn_back;
    ListView lstview_items;
    Controller controller;
    ArrayList<Item> arraylst_items;

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

    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        btn_back = (Button) findViewById(R.id.btn_back);
        lstview_items = (ListView) findViewById(R.id.lst_items);
        controller = new Controller();
        arraylst_items = new ArrayList<Item>();
        arraylst_txtview_data_1 = new ArrayList<>();
    }

    /**
     *  Method that initialize all the listeners related to the components
     */
    public void initializeListeners()
    {
        /*
            Listener for Back button
         */
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch Register User Activity
                Intent I = new Intent(ArchiveActivity.this, MainActivity.class);
                startActivity(I);
            }
        });
    }

    /**
     * Method that fills the list of items in the archive ViewList, both lost items and found items
     * are mixed,
     *
     */
    public void fillViewList()
    {

        ItemViewList[] item_data = controller.getListItems();
        formatDataToShow();
        ItemAdapter adapter = new ItemAdapter(this,
                R.layout.listview_item_row, item_data);


        listView1 = (ListView)findViewById(R.id.lst_items);
        listView1.setAdapter(adapter);




    }

}
