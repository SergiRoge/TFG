package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import Classes.Item;
import Classes.User;
import Connection.SQLObject;

import static Auxiliar.Constants.URL_CHECK_FOUND_ITEMS;

public class ItemMatchingActivity extends AppCompatActivity {


    Button btnOK;
    Button btnNOOK;
    TextView txtItemData;
    User user;

    String RowID;

    String strItemDescription;
    String strType;
    String strColor;
    String strMaterial;

    int IDItemLost;
    int IDItemFound;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_matching);

        user = (User) getIntent().getSerializableExtra("user");
        IDItemLost =  Integer.parseInt(getIntent().getExtras().getString("IDItemLost"));
        IDItemFound = Integer.parseInt(getIntent().getExtras().getString("IDItemFound"));
        strItemDescription = getIntent().getExtras().getString("Description");
        strType = getIntent().getExtras().getString("Type");
        strColor = getIntent().getExtras().getString("Color");
        strMaterial = getIntent().getExtras().getString("Material");
        RowID = getIntent().getExtras().getString("RowID");



        initializeComponents();
        initializeListeners();
    }


    private void fillItemData()
    {
        String data = "";

        data = "Has encontrado un/a " + strType ;
        if(!strColor.equals(""))
        {
            data+= " de color " + strColor;
        }
        if(!strMaterial.equals(""))
        {
            data += " de " + strMaterial;
        }
        if(!strItemDescription.equals(""))
        {
            data += " cuyo usuario que lo perdio lo describió como " + strItemDescription;
        }

        txtItemData.setText(data);



    }


    private void initializeComponents()
    {
        btnOK =  (Button) findViewById(R.id.btn_si);
        btnNOOK =  (Button) findViewById(R.id.btn_no);
        txtItemData = (TextView) findViewById(R.id.txt_item_data);





        fillItemData();

    }


    private void initializeListeners()
    {
        btnOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent I = new Intent(ItemMatchingActivity.this, MainActivity.class);
                I.putExtra("FROM","ItemMatchingActivity");
                I.putExtra("RowID", RowID);
                I.putExtra("Result", "YES");

                startActivity(I);
                finish();
            }
        });

        btnNOOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent I = new Intent(ItemMatchingActivity.this, MainActivity.class);
                I.putExtra("FROM","ItemMatchingActivity");
                I.putExtra("RowID", RowID);
                I.putExtra("Result", "NO");

                startActivity(I);
                finish();
            }
        });



    }
}
