package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LostItemActivity extends AppCompatActivity {


    EditText txtLost;
    EditText txtColor;
    EditText txtBrand;
    EditText txtMaterial;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_item);


        txtLost = (EditText) findViewById(R.id.txt_ItemType);
        txtColor = (EditText) findViewById(R.id.txt_ItemColor);
        txtBrand = (EditText) findViewById(R.id.txt_ItemBrand);
        txtMaterial = (EditText) findViewById(R.id.txt_ItemMaterial);
        btnNext = (Button) findViewById(R.id.btn_Next);

        /*
            Listener for Next button
         */
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Coords Activity
                Intent I = new Intent(LostItemActivity.this, CoordsActivity.class);
                I.putExtra("Activity", "LostItem");
                startActivity(I);
            }
        });

    }
}
