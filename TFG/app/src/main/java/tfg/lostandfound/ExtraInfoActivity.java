package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExtraInfoActivity extends AppCompatActivity {

    Button btnSave;
    EditText txtExtraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_info);

        initializeComponents();
        initializeListeners();



    }

    /**
     *  Method that initialize all components that exist in the activity
     */
    private void initializeComponents()
    {
        txtExtraInfo = (EditText) findViewById(R.id.txt_extraInformation);
        btnSave = (Button) findViewById(R.id.btn_save);
    }
    /**
     *  Method that initialize all the listeners related to the components
     */
    private void initializeListeners()
    {
        /*
            Listener for save button
        */
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent I = new Intent(ExtraInfoActivity.this, MainActivity.class);
                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(I);
            }
        });
    }
}
