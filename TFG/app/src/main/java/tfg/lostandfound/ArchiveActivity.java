package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ArchiveActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);


        btn_back = (Button) findViewById(R.id.btn_save);

        /*
            Listener for Save button
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
}
