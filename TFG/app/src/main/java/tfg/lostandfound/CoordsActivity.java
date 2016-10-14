package tfg.lostandfound;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CoordsActivity extends AppCompatActivity {


    Button btnnew1;
    Button btnnew2;
    Button btnnew3;
    Button btnNext;
    String strFromActivity = "FoundItem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coords);
        final Bundle bundle = getIntent().getExtras();

        String getExtraActivityFrom = bundle.getString("Activity");

        Log.d("Pepe","pepe");
        Log.d("activity",getExtraActivityFrom);
        if(getExtraActivityFrom.equals("LostItem")) {
            strFromActivity = "LostItem";
        }

        Button btnnew1;
        Button btnnew2;
        Button btnnew3;
        Button btnNext;

        btnnew1 = (Button) findViewById(R.id.btn_New1);
        btnnew2 = (Button) findViewById(R.id.btn_New2);
        btnnew3 = (Button) findViewById(R.id.btn_New3);
        btnNext = (Button) findViewById(R.id.btn_next);

        /*
            Listener for New 1 button
         */
        btnnew1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //Intent I = new Intent(CoordsActivity.this);
                //startActivity(I);
            }
        });

        /*
            Listener for New 2 button
         */
        btnnew2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //Intent I = new Intent(CoordsActivity.this, ChatsActivity.class);
                //startActivity(I);
            }
        });

        /*
            Listener for New 3 button
         */
        btnnew3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                //Intent I = new Intent(CoordsActivity.this, ChatsActivity.class);
                //startActivity(I);
            }
        });

        /*
            Listener for Next button leading the user to ExtraInfoActivity
         */
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity


                if(strFromActivity.equals("LostItem"))
                {
                    Intent I = new Intent(CoordsActivity.this, ExtraInfoActivity.class);
                    startActivity(I);
                }
                else if(strFromActivity.equals("FoundItem"))
                {
                    Intent I = new Intent(CoordsActivity.this, MainActivity.class);
                    startActivity(I);
                }

            }
        });

    }
}
