package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FoundItemActivity extends AppCompatActivity {

    EditText txtLost;
    EditText txtColor;
    EditText txtBrand;
    EditText txtMaterial;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_item);


        btnNext = (Button) findViewById(R.id.btn_Next);
        /*
            Listener for Next button
         */
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch Coords Activity
                Intent I = new Intent(FoundItemActivity.this, CoordsActivity.class);
                I.putExtra("Activity", "FoundItem");
                startActivity(I);
            }
        });

    }
}
