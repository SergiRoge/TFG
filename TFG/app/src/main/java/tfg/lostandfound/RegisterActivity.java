package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    Button btnSave;
    EditText txtUsername;
    EditText txtPassword;
    EditText txtEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();



        btnSave = (Button) findViewById(R.id.btn_save);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtEmail = (EditText) findViewById(R.id.txt_email);

        String strTxtUsername = txtUsername.getText().toString();
        String strTxtPassword = txtPassword.getText().toString();
        String strTxtEmail = txtEmail.getText().toString();

        /*
            Listener for Save button
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







                //Launch Register User Activity
                Intent I = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(I);
            }
        });


    }
}
