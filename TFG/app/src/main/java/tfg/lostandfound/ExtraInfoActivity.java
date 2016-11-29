package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import Classes.Item;

public class ExtraInfoActivity extends AppCompatActivity {

    /**
     * All components needed
     */
    Button btnSave;
    EditText txtExtraInfo;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_info);

        initializeComponents();
        initializeListeners();

        item = (Item) getIntent().getSerializableExtra("Item");

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
                item.setStrDescription(txtExtraInfo.getText().toString());
                I.putExtra("Item", (Serializable) item);
                I.putExtra("FROM"," ");

                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishAffinity();
                startActivity(I);
            }
        });
    }
}
