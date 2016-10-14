package tfg.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {


    Button btnRegister;
    Button btnLogin;
    EditText txtUser;
    EditText txtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtUser = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);

        /*
            Listener for Register button
         */
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Register User Activity
                Intent I = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(I);
            }
        });
        /*
            Listener for Login button
         */
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                //Launch Main Activity
                Intent I = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(I);



            }
        });

    }

}
