package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExtraInfoActivity extends AppCompatActivity {

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_info);



        btnSave = (Button) findViewById(R.id.btn_save);
        /*
            Listener for save button
         */
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent I = new Intent(ExtraInfoActivity.this, MainActivity.class);
                startActivity(I);
            }
        });
    }
}
