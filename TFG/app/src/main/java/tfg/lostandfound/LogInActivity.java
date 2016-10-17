package tfg.lostandfound;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Connection.Connection;

import static Auxiliar.Auxiliar.showMessageError;


public class LogInActivity extends AppCompatActivity {


    Button btnRegister;
    Button btnLogin;

    Connection connection;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);

        connection = new Connection();


        /*
            Listener for Login button
         */
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                EditText txtEmail = (EditText) findViewById(R.id.txt_email);
                EditText txtPassword = (EditText) findViewById(R.id.txt_password);

                String strTxtEmail = txtEmail.getText().toString();
                String strTxtPassword = txtPassword.getText().toString();


                int intError = checkUser(strTxtEmail, strTxtPassword);

                if(intError == 0)
                {
                    Intent I = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(I);
                }
                else
                {
                    showMessageError(intError);

                }




            }
        });


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
    }


    private int checkUser(String pstrTxtUser, String pstrTxtPassword)
    {
        //TODO Logica de comprobacion de usuario
       return 0;
    }
}
