package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Classes.Item;
import Classes.ItemViewList;

public class ItemViewActivity extends AppCompatActivity {

    TextView txtviewItemType;
    TextView txtviewItemColor;
    TextView txtviewItemMaterial;
    TextView txtviewItemBrand;
    TextView txtviewWhen;
    TextView txtTittle;

    Button btnBack;
    Button btnDelete;

    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);


        item = (Item) getIntent().getSerializableExtra("Item");

        initializeComponents();
        initializeListeners();

    }



    /**
     *  Method that initialize all components that exist in the activity
     */
    public void initializeComponents()
    {
        txtTittle = (TextView) findViewById(R.id.txt_title);
        txtviewItemType = (TextView) findViewById(R.id.txt_ItemType);
        txtviewItemColor = (TextView) findViewById(R.id.txt_ItemColor);
        txtviewItemMaterial = (TextView) findViewById(R.id.txt_ItemMaterial);
        txtviewItemBrand = (TextView) findViewById(R.id.txt_ItemBrand);
        txtviewWhen = (TextView) findViewById(R.id.txt_ItemWhen);


        txtTittle.setText(item.getStrFoundLost());
        txtviewItemType.setText(item.getStrItemType());
        txtviewItemColor.setText(item.getStrItemColor());
        txtviewItemMaterial.setText(item.getStrItemMaterial());
        txtviewItemBrand.setText(item.getStrItemBrand());
        //txtviewWhen.setText();

        btnBack = (Button) findViewById(R.id.btn_back);
        btnDelete = (Button) findViewById(R.id.btn_delete);



    }

    /**
     *  Method that initialize all the listeners related to the components
     */
    public void initializeListeners()
    {
        /**
         * Listener for Register button
         */
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent returnIntent = new Intent();
                setResult(1,returnIntent);
                finish();
            }
        });

        /**
         * Listener for Register button
         */
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //Intent I = new Intent(LogInActivity.this, RegisterActivity.class);
                //startActivity(I);
                Intent returnIntent = new Intent();
                setResult(2,returnIntent);
                finish();
            }
        });


    }
}
